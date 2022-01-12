package com.ooad.system_management.Service.Impl;

import com.ooad.system_management.Dao.Impl.SystemlogDaoImpl;
import com.ooad.system_management.Pojo.Systemlog;
import com.ooad.system_management.Service.ISystemlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemlogServiceImpl implements ISystemlogService {
    @Autowired
    private SystemlogDaoImpl systemlogDao;

    @Override
    public Systemlog searchLogByID(String logid){return systemlogDao.searchLogByID(logid);}

    @Override
    public Systemlog searchLogByTime(String time){return systemlogDao.searchLogByTime(time);}

    @Override
    public Systemlog searchLogByOperator(String operator){return systemlogDao.searchLogByID(operator);}

    @Override
    public  List<Systemlog> getAllLog(){return systemlogDao.getAllLog();}
}
