package com.ooad.system_management.Pojo;

public class Staff {
    //数据成员
    private String  staffID;
    private String  name;
    private String  sex;
    private String  college;
    private String  department;
    private String  nativeplace;
    private String  address;
    private String  phone;
    private String  Email;
    private String  otherInformation;
    //构造函数

    public Staff(String staffID, String name, String sex, String college, String department, String nativeplace, String address, String phone, String email, String otherInformation) {
        this.staffID = staffID;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.department = department;
        this.nativeplace = nativeplace;
        this.address = address;
        this.phone = phone;
        Email = email;
        this.otherInformation = otherInformation;
    }

    //get and set

    public String getStaffID() {
        return staffID;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getCollege() {
        return college;
    }

    public String getDepartment() {
        return department;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffID='" + staffID + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", college='" + college + '\'' +
                ", department='" + department + '\'' +
                ", nativeplace='" + nativeplace + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", Email='" + Email + '\'' +
                ", otherInformation='" + otherInformation + '\'' +
                '}';
    }
}
