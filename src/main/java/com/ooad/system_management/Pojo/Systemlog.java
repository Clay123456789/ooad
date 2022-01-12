package com.ooad.system_management.Pojo;

public class Systemlog {
    //数据成员
    private String logid;
    public  String time;
    public  String operator;
    public  String operation;
    public  String result;
    //构造函数

    public Systemlog() {
    }

    //get and set
    public String getLogid() {
        return logid;
    }

    public String getTime() {
        return time;
    }

    public String getOperator() {
        return operator;
    }

    public String getOperation() {
        return operation;
    }

    public String getResult() {
        return result;
    }

    public void setLogid(String logid) {
        this.logid = logid;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Systemlog{" +
                "logid='" + logid + '\'' +
                ", time='" + time + '\'' +
                ", operator='" + operator + '\'' +
                ", operation='" + operation + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
