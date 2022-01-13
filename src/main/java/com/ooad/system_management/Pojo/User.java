package com.ooad.system_management.Pojo;

public class User {
    //涉及用户的信息
    private String  studentid;  //学号
    private String staffid; //职工号
    private String managerid;//管理员号
    private String account;//账号
    private String password;//密码
    private String  stu_name;
    private String sta_name;
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
    private int whichpeople;  //标识哪一个

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

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }

    public String getManagerid() {
        return managerid;
    }

    public void setManagerid(String managerid) {
        this.managerid = managerid;
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

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getOtherInformation() {
        return otherInformation;
    }

    public void setOtherInformation(String otherInformation) {
        this.otherInformation = otherInformation;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public int getWhichpeople() {
        return whichpeople;
    }

    public void setWhichpeople(int whichpeople) {
        this.whichpeople = whichpeople;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    /**
     * 将表单中的对象转化为数据库中存储的用户对象（剔除表单中的code）
     */
    //转换为学生
    public static Student toStudent(User user){
        Student student=new Student();
        student.setAccount(user.getAccount());
        student.setPassword(user.getPassword());
        student.setStudentid(user.getStudentid());
        student.setStu_name(user.getStu_name());
        student.setSex(user.getSex());
        student.setGrade(user.getGrade());
        student.setCollege(user.getCollege());
        student.setClass_(user.getClass_());
        student.setTutor_name(user.getTutor_name());
        student.setDormitory(user.getDormitory());
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
        staff.setAccount(user.getAccount());
        staff.setPassword(user.getPassword());
        staff.setStaffid(user.getStaffid());
        staff.setSta_name(user.getSta_name());
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
        manager.setManagerid(user.getManagerid());
        manager.setAccount(user.getAccount());
        manager.setEmail(user.getEmail());
        manager.setPassword(user.getPassword());
        manager.setPhone(user.getPhone());
        return manager;
    }

    @Override
    public String toString() {
        return "User{" +
                "studentid='" + studentid + '\'' +
                ", staffid='" + staffid + '\'' +
                ", managerid='" + managerid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", sta_name='" + sta_name + '\'' +
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
                ", department='" + department + '\'' +
                ", otherInformation='" + otherInformation + '\'' +
                ", code='" + code + '\'' +
                ", touxiang='" + touxiang + '\'' +
                ", whichpeople=" + whichpeople +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
