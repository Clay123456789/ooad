package com.ooad.system_management.Dao.Impl;

import com.google.gson.Gson;
import com.ooad.system_management.Dao.ISystemlogDao;
import com.ooad.system_management.Pojo.Systemlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SystemlogDaoImpl implements ISystemlogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    /*
    Systemlog searchLogByID(String logid);
    Systemlog searchLogByTime(String time);
    Systemlog searchLogByOperator(String operator);
    List<Systemlog> getAllLog();
     */
    @Override
    public Systemlog searchLogByID(String logid){
        RowMapper<Systemlog> rowMapper = new BeanPropertyRowMapper<Systemlog>(Systemlog.class);
        Object object = null;
        try{
            object=jdbcTemplate.queryForObject("select * from systemlog where logid = ?",rowMapper,logid);
        }catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Systemlog sl=(Systemlog) object;
        return sl;
    }

    @Override
    public Systemlog searchLogByTime(String time){
        RowMapper<Systemlog> rowMapper = new BeanPropertyRowMapper<Systemlog>(Systemlog.class);
        Object object = null;
        try{
            object=jdbcTemplate.queryForObject("select * from systemlog where time = ?",rowMapper,time);
        }catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Systemlog sl=(Systemlog) object;
        return sl;
    }
    @Override
    public Systemlog searchLogByOperator(String operator){
        RowMapper<Systemlog> rowMapper = new BeanPropertyRowMapper<Systemlog>(Systemlog.class);
        Object object = null;
        try{
            object=jdbcTemplate.queryForObject("select * from systemlog where operator = ?",rowMapper,operator);
        }catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Systemlog sl=(Systemlog) object;
        return sl;
    }
    @Override
    public List<Systemlog> getAllLog(){
        RowMapper<Systemlog> rowMapper = new BeanPropertyRowMapper<Systemlog>(Systemlog.class);
        List<Systemlog> list = jdbcTemplate.query("select * from systemlog order by logid ASC ",rowMapper);
        String toJson = new Gson().toJson(list);
        return list;
    }
}
