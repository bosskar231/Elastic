package com.auxo.model;

public class Employee {

    private String eid;
    private String name;
//    private int age;
//    private String place;
//    private String dob;
//    private String phone;
//    private int deptid;
//    public Employee()
//    {
//
//    }

    @Override
    public String toString() {
        return "EmployeeApplication{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
//                ", age=" + age +
//                ", place='" + place + '\'' +
//                ", dob='" + dob + '\'' +
//                ", phone='" + phone + '\'' +
//                ", deptid=" + deptid +
                '}';
    }
//
//    public Employee(String eid, String name) {
//        this.eid = eid;
//        this.name = name;
////        this.age = age;
////        this.place = place;
////        this.dob = dob;
////        this.phone = phone;
////        this.deptid = deptid;
//    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public String getPlace() {
//        return place;
//    }
//
//    public void setPlace(String place) {
//        this.place = place;
//    }
//
//    public String getDob() {
//        return dob;
//    }
//
//    public void setDob(String dob) {
//        this.dob = dob;
//    }
//
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public int getDeptid() {
//        return deptid;
//    }
//
//    public void setDeptid(int deptid) {
//        this.deptid = deptid;
//    }



}
