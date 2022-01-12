package com.ooad.system_management.Service.Impl;

import com.ooad.system_management.Dao.IManagerDao;
import com.ooad.system_management.Dao.Impl.ManagerDaoImpl;
import com.ooad.system_management.Pojo.Manager;
import com.ooad.system_management.Service.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class ManagerServiceImpl implements IManagerService {
    @Autowired
    private ManagerDaoImpl managerDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public boolean insertManager(Manager manager) {
        return managerDao.insertManager(manager);
    }

    @Override
    public boolean deleteManager(String managerid) {
        return managerDao.deleteManager(managerid);
    }

    @Override
    public boolean updateManager(Manager manager) {
        return managerDao.updateManager(manager);
    }

    @Override
    public Manager getManagerByManagerid(String managerid) {
        return managerDao.getManagerByManagerid(managerid);
    }

    @Override
    public Manager getManagerByEmail(String email) {
        return managerDao.getManagerByEmail(email);
    }

    @Override
    public Manager getManagerByAccount(String account) {
        return managerDao.getManagerByAccount(account);
    }

    @Override
    public List<Manager> getAllManager() {
        return managerDao.getAllManager();
    }

    @Override
    public boolean judgeManagerByEmail(Manager manager) {
        RowMapper<Manager> rowMapper = new BeanPropertyRowMapper<Manager>(Manager.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from manager where email = ?",rowMapper,manager.getEmail());
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回false
            return false;
        }
        Manager ma=(Manager) object;
        //System.out.println(sta.toString());
        //System.out.println(staff.toString());
        if(!ma.getPassword().equals(manager.getPassword())){
            return false;
        }
        return true;
    }

    @Override
    public boolean judgeManagerByAccount(Manager manager) {
        RowMapper<Manager> rowMapper = new BeanPropertyRowMapper<Manager>(Manager.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from manager where account = ?",rowMapper,manager.getAccount());
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回false
            return false;
        }
        Manager ma=(Manager) object;
        //System.out.println(sta.toString());
        //System.out.println(staff.toString());
        if(!ma.getPassword().equals(manager.getPassword())){
            return false;
        }
        return true;
    }
}
