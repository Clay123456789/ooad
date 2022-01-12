package com.ooad.system_management.Service.Impl;

import com.ooad.system_management.Dao.IStaffDao;
import com.ooad.system_management.Dao.Impl.StaffDaoImpl;
import com.ooad.system_management.Pojo.Staff;
import com.ooad.system_management.Service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements IStaffService {
    @Autowired
    private StaffDaoImpl staffDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean insertStaff(Staff staff) {
        return staffDao.insertStaff(staff);
    }

    @Override
    public boolean deleteStaff(String staffid) {
        return staffDao.deleteStaff(staffid);
    }

    @Override
    public boolean updateStaff(Staff staff) {
        return staffDao.updateStaff(staff);
    }

    @Override
    public Staff getStaffByStaffid(String staffid) {
        return staffDao.getStaffByStaffid(staffid);
    }

    @Override
    public Staff getStaffByEmail(String email) {
        return staffDao.getStaffByAccount(email);
    }

    @Override
    public Staff getStaffByAccount(String account) {
        return staffDao.getStaffByAccount(account);
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffDao.getAllStaff();
    }

    @Override
    public boolean judgeStaffByEmail(Staff staff) {
        RowMapper<Staff> rowMapper = new BeanPropertyRowMapper<Staff>(Staff.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from staff where email = ?",rowMapper,staff.getEmail());
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回false
            return false;
        }
        Staff sta=(Staff)object;
        //System.out.println(sta.toString());
        //System.out.println(staff.toString());
        if(!sta.getPassword().equals(staff.getPassword())){
            return false;
        }
        return true;
    }

    @Override
    public boolean judgeStaffByAccount(Staff staff) {
        RowMapper<Staff> rowMapper = new BeanPropertyRowMapper<Staff>(Staff.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from staff where account = ?",rowMapper,staff.getAccount());
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回false
            return false;
        }
        Staff sta=(Staff)object;
        //System.out.println(sta.toString());
        //System.out.println(staff.toString());
        if(!sta.getPassword().equals(staff.getPassword())){
            return false;
        }
        return true;
    }
}
