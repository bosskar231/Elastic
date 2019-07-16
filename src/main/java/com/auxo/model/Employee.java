package com.auxo.model;

public class Employee {

    private String eid;
    private String name;
    private String  age;
    private String place;
    private String dob;
    private String phone;

    public Employee()
    {

    }

    @Override
    public String toString() {
        return "EmployeeApplication{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", place='" + place + '\'' +
                ", dob='" + dob + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Employee(String eid, String name,String age,String place,String dob,String phone) {
        this.eid = eid;
        this.name = name;
        this.age = age;
        this.place = place;
        this.dob = dob;
        this.phone = phone;

    }

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

    public String  getAge() {
        return age;
    }

    public void setAge(String  age) {
        this.age = age;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }




}
