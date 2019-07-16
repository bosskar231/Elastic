package com.auxo;



import com.auxo.model.Employee;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Main  {
    private static final String HOST = "localhost";
    private static final int PORT_ONE = 9200;
    private static final int PORT_TWO = 9201;
    private static final String SCHEME = "http";

    private static RestHighLevelClient restHighLevelClient;
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static final String INDEX = "employeedata";
    private static final String TYPE = "employee";
    private static synchronized RestHighLevelClient makeConnection() {

        if(restHighLevelClient == null) {
            restHighLevelClient = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost(HOST, PORT_ONE, SCHEME),
                            new HttpHost(HOST, PORT_TWO, SCHEME)));
            System.out.println("Connnection done");
        }

        return restHighLevelClient;
    }
    private static Employee updateEmployeeById(String id, Employee employee){
        UpdateRequest updateRequest = new UpdateRequest(INDEX, TYPE, id).fetchSource(true);
        try {
            String personJson = objectMapper.writeValueAsString(employee);
            updateRequest.doc(personJson, XContentType.JSON);
            UpdateResponse updateResponse = restHighLevelClient.update(updateRequest);
            return objectMapper.convertValue(updateResponse.getGetResult().sourceAsMap(), Employee.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Unable to update ");
        return null;
    }

    private static void deleteEmployeeById(String id) {
        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, id);
        try {
            DeleteResponse deleteResponse = restHighLevelClient.delete(deleteRequest);
        } catch (Exception e){
            System.out.println("error in deleting...");
           e.printStackTrace();
        }
    }
    private static synchronized void closeConnection() throws IOException {
        restHighLevelClient.close();
        restHighLevelClient = null;
    }
    private static Employee insertEmployee(Employee employee){

        employee.setEid("10928");

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("eid", employee.getEid());
        dataMap.put("name", employee.getName());
        dataMap.put("age",employee.getAge());
        dataMap.put("place",employee.getPlace());
        dataMap.put("dob",employee.getDob());
        dataMap.put("phone",employee.getPhone());
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, employee.getEid())
                .source(dataMap);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("Employee Inserted ");
        return employee;
    }

    private static Employee getEmployeeById(String id){
        GetRequest getPersonRequest = new GetRequest(INDEX, TYPE,id);
        GetResponse getResponse = null;
        try {
            getResponse = restHighLevelClient.get(getPersonRequest);
        } catch (Exception e){
            e.printStackTrace();
        }
        return getResponse != null ? objectMapper.convertValue(getResponse.getSourceAsMap(), Employee.class) : null;
    }
    public static void main(String[] args) throws Exception {
        try {
            makeConnection();
            Employee employee = new Employee();
            while (true) {
                System.out.println("Enter the values for operations");
                System.out.println("1.Insert\n2.display\n3.update\n4.delete\n5.exit");
                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {


                    case 1:
                        System.out.println("the employee fiels are \n1.id\n2.name\n3.age\n4.place\n5.dob\n6.phone");
                        System.out.println("fill the fields");
                        System.out.println("enter name ");
                        String name = scanner.nextLine();
                        System.out.println("enter age");
                        String age = scanner.nextLine();
                        System.out.println("enter place");
                        String place = scanner.nextLine();
                        System.out.println("enter the dob");
                        String dob = scanner.nextLine();
                        System.out.println("enter the phone");
                        String phone = scanner.nextLine();
                        System.out.println("object creation in progress.....");
                        Thread.sleep(2000);
                        employee.setName(name);
                        employee.setAge(age);
                        employee.setPlace(place);
                        employee.setDob(dob);
                        employee.setPhone(phone);
                        System.out.println("object created");
                        employee = insertEmployee(employee);
                        System.out.println("inserted employee " + employee.toString());

                        break;
                    case 2:
                        System.out.println("enter the id to search");
                        String id = scanner.nextLine();
                        employee = getEmployeeById(id);
                        if (employee == null) {
                            System.out.println("no matching");
                        } else {
                            System.out.println(employee.toString());
                        }

                        break;
                    case 3:
                        System.out.println("enter the id  ");
                        String ids = scanner.nextLine();
                        System.out.println("select the field to update");
                        System.out.println("1.name\n2.age\n3.place\n4.dob\n5.phone");

                        int value = scanner.nextInt();
                        scanner.nextLine();
                        switch (value) {
                            case 1:
                                System.out.println("enter the name");
                                employee.setName(scanner.nextLine());
                                employee = updateEmployeeById(employee.getEid(), employee);

                                break;
                            case 2:
                                System.out.println("enter the age");
                                employee.setAge(scanner.nextLine());
                                employee = updateEmployeeById(employee.getEid(), employee);
                                break;
                            case 3:
                                System.out.println("enter the place");
                                employee.setPlace(scanner.nextLine());
                                employee = updateEmployeeById(employee.getEid(), employee);
                                break;
                            case 4:
                                System.out.println("enter the dob");
                                employee.setDob(scanner.nextLine());
                                employee = updateEmployeeById(employee.getEid(), employee);
                                break;
                            case 5:
                                System.out.println("enter the phone");
                                employee.setPhone(scanner.nextLine());
                                employee = updateEmployeeById(employee.getEid(), employee);
                                break;

                        }


                        break;
                    case 4:
                        System.out.println("enter the id to delete");
                        String idss = scanner.nextLine();
                        deleteEmployeeById(idss);
                        break;
                    case 5:
                        System.exit(0);
                        break;
                }

//
//            Employee employee = new Employee();
//            employee.setName("Baskar");
//            employee = insertEmployee(employee);
//            Employee personFromDB = getEmployeeById(employee.getEid());
//            System.out.println("the result " + personFromDB);
//            System.out.println("Changing name to Baskar s...");
//            employee.setName("Baskar s");
//            updateEmployeeById(employee.getEid(), employee);
//            System.out.println("Person updated  --> " + employee);
//
//            System.out.println("Deleting baskar s...");
//            deleteEmployeeById(personFromDB.getEid());
//            System.out.println("Person Deleted");


            }
        }
        finally{
            closeConnection();
        }
    }


}
