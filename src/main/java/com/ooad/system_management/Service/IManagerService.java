package com.ooad.system_management.Service;

import com.ooad.system_management.Pojo.Manager;

import java.util.List;

public interface IManagerService {

    //增删改查方法
    boolean insertManager(Manager manager);
    boolean deleteManager(String managerid);
    boolean updateManager(Manager manager);
    Manager getManagerByManagerid(String managerid);
    Manager getManagerByEmail(String email);
    Manager getManagerByAccount(String account);
    List<Manager> getAllManager();

    //登陆判断方法
    boolean judgeManagerByEmail(Manager manager);
    boolean judgeManagerByAccount(Manager manager)
}
