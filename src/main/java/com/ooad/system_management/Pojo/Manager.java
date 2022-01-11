package com.ooad.system_management.Pojo;

public class Manager {
    //数据成员
    private String account;//账号
    private String password;//密码
    private String phone;//手机号
    private String Email;//邮箱

    //构造函数
    public Manager(){

    }
    public Manager(String account, String password, String phone, String email) {
        this.account = account;
        this.password = password;
        this.phone = phone;
        Email = email;
    }
    //set 和 get函数


    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "manager{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
