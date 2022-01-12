package com.ooad.system_management.Dao;

import com.ooad.system_management.Pojo.Staff;

import java.util.List;

public interface IStaffDao {
    //增删改查方法
    boolean insertStaff(Staff staff);
    boolean deleteStaff(String staffid);
    boolean updateStaff(Staff staff);
    Staff getStaffByStaffid(String staffid);
    Staff getStaffByEmail(String email);
    Staff getStaffByAccount(String account);
    List<Staff> getAllStaff();
}
