package com.ooad.system_management.Dao;

import com.ooad.system_management.Pojo.Systemlog;

import java.util.List;

public interface ISystemlogDao {
    //系统日志仅提供查询
    Systemlog searchLogByID(String logid);
    Systemlog searchLogByTime(String time);
    Systemlog searchLogByOperator(String operator);
    List<Systemlog> getAllLog();

}
