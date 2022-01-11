package com.ooad.system_management.Pojo;

public class Staff {
    //数据成员
    private String  staffID;
    private String account;
    private String password;
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
    public Staff(){

    }
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

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getOtherInformation() {
        return otherInformation;
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
