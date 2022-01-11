package com.ooad.system_management.Service;

import com.ooad.system_management.Pojo.Systemlog;

import java.util.List;

public interface ISystemlogService {
    Systemlog searchLogByID(String logid);
    Systemlog searchLogByTime(String time);
    Systemlog searchLogByOperator(String operator);
    List<Systemlog> getAllLog();
}
