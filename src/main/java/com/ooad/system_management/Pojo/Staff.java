package com.ooad.system_management.Pojo;

public class Staff {
    //数据成员
    private String staffid;
    private String account;
    private String password;
    private String sta_name;
    private String  sex;
    private String  college;
    private String  department;
    private String  nativeplace;
    private String  address;
    private String  phone;
    private String email;
    private String  otherInformation;
    //构造函数
    public Staff(){

    }
    public Staff(String staffid, String sta_name, String sex, String college, String department, String nativeplace, String address, String phone, String email, String otherInformation) {
        this.staffid = staffid;
        this.sta_name = sta_name;
        this.sex = sex;
        this.college = college;
        this.department = department;
        this.nativeplace = nativeplace;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.otherInformation = otherInformation;
    }

    //get and set


    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
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

    public String getSta_name() {
        return sta_name;
    }

    public void setSta_name(String sta_name) {
        this.sta_name = sta_name;
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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                "staffID='" + staffid + '\'' +
                ", name='" + sta_name + '\'' +
                ", sex='" + sex + '\'' +
                ", college='" + college + '\'' +
                ", department='" + department + '\'' +
                ", nativeplace='" + nativeplace + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", Email='" + email + '\'' +
                ", otherInformation='" + otherInformation + '\'' +
                '}';
    }
}
