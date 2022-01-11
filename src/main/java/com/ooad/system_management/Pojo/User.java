package com.ooad.system_management.Pojo;

public class User {
    //涉及用户的信息
    private String  studentid;  //学号
    private String staffid; //职工号
    private String account;//账号
    private String paasword;//密码
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
    private String department;
    private String  otherInformation;
    private String code;//验证码
    private String touxiang;//头像

    //更新后的信息
    private String newPassword;
    private String newName;
    private String newSex;
    private String newGrade;
    private String newCollege;
    private String newClass;
    private String newTutorName;
    private String newDormitory;
    private String newNativeplace;
    private String newAddress;
    private String newPhone;
    private String newEmail;
    private String newDepartment;
    private String newOtherInformation;
    private String newTouxiang;

    public User(){

    }

    public String getStudentid() {
        return studentid;
    }

    public String getStaffid() {
        return staffid;
    }

    public String getAccount() {
        return account;
    }

    public String getPaasword() {
        return paasword;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getGrade() {
        return grade;
    }

    public String getCollege() {
        return college;
    }

    public String getClass_() {
        return class_;
    }

    public String getTutor_name() {
        return tutor_name;
    }

    public String getDormitory() {
        return dormitory;
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
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public String getCode() {
        return code;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPaasword(String paasword) {
        this.paasword = paasword;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public void setTutor_name(String tutor_name) {
        this.tutor_name = tutor_name;
    }

    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
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
        this.email = email;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    /**
     * 将表单中的对象转化为数据库中存储的用户对象（剔除表单中的code）
     */
    //转换为学生
    public static Student toStudent(User user){
        Student student=new Student();
        student.setStudentid(user.getStudentid());
        student.setName(user.getName());
        student.setSex(user.getSex());
        student.setGrade(user.getGrade());
        student.setCollege(user.getCollege());
        student.setClass_(user.getClass_());
        student.setTutor_name(user.getTutor_name());
        student.setNativeplace(user.getNativeplace());
        student.setAddress(user.getAddress());
        student.setPhone(user.getPhone());
        student.setEmail(user.getEmail());
        student.setOtherInformation(user.getOtherInformation());
        return student;
    }
    //转换为职工
    public static Staff toStaff(User user){
        Staff staff=new Staff();
        staff.setStaffID(user.getStaffid());
        staff.setName(user.getName());
        staff.setSex(user.getSex());
        staff.setCollege(user.getCollege());
        staff.setDepartment(user.getDepartment());
        staff.setNativeplace(user.getNativeplace());
        staff.setAddress(user.getAddress());
        staff.setPhone(user.getPhone());
        staff.setEmail(user.getEmail());
        staff.setOtherInformation((user.getOtherInformation()));
        return staff;
    }
    //转换为管理员
    public static Manager toManager(User user){
        Manager manager=new Manager();
        manager.setAccount(user.getAccount());
        manager.setEmail(user.getEmail());
        manager.setPassword(user.getPaasword());
        manager.setPhone(user.getPhone());
        return manager;
    }
}
