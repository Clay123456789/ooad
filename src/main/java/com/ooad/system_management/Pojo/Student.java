package com.ooad.system_management.Pojo;

public class Student {
    private String  studentid;
    private String  name;
    private String  sex;
    private String  grade;
    private String  college;
    private String  class_;
    private String  tutor_name;
    private String  dormitory;
    private String  nativeplace;
    private String  address;
    private String  phone;
    private String  email;
    private String  otherInformation;
    //构造函数
    public Student() {
    }

    public Student(String studentid, String name, String sex, String grade, String college, String class_, String tutor_name, String dormitory, String nativeplace, String address, String phone, String email, String otherInformation) {
        this.studentid = studentid;
        this.name = name;
        this.sex = sex;
        this.grade = grade;
        this.college = college;
        this.class_ = class_;
        this.tutor_name = tutor_name;
        this.dormitory = dormitory;
        this.nativeplace = nativeplace;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.otherInformation = otherInformation;
    }
    //get and set
    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }

    public String getDormitory() {
        return dormitory;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
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
        return "Student{" +
                "studentid='" + studentid + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", grade='" + grade + '\'' +
                ", college='" + college + '\'' +
                ", class_='" + class_ + '\'' +
                ", tutor_name='" + tutor_name + '\'' +
                ", dormitory='" + dormitory + '\'' +
                ", nativeplace='" + nativeplace + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", otherInformation='" + otherInformation + '\'' +
                '}';
    }
}
