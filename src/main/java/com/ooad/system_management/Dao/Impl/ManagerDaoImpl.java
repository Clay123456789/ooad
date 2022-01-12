package com.ooad.system_management.Dao.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ooad.system_management.Dao.IManagerDao;
import com.ooad.system_management.Pojo.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ManagerDaoImpl implements IManagerDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加一行数据：
     * 直接添加到mysql数据库,将缓存中数据所在表删除（为了保证selectAll获取数据一致）
     */
    @Override
    public boolean insertManager(Manager manager) {
        int result= jdbcTemplate.update("insert into manager(managerid,account,password,phone,email) values(?,?,?,?,?)",
                manager.getManagerid(),manager.getAccount(),manager.getPassword(),manager.getPhone(),manager.getEmail());
        if(result>0){
            // 判断是否缓存存在
            String key = "manager_list";
            Boolean hasKey = redisTemplate.hasKey(key);
            // 缓存存在，进行删除
            if (hasKey) {
                redisTemplate.delete(key);
            }
            return true;
        }
        else{
            return false;
        }
    }
    /**
     * 删除一行数据：
     * 依据主键staffid
     * 先删除mysql数据库，再将缓存的数据删除即可
     */
    @Override
    public boolean deleteManager(String managerid) {
        int result = jdbcTemplate.update("delete from manager where managerid = ?", managerid);
        if (result != 0) {
            // 判断是否缓存存在
            String key = "manager_" + managerid;
            Boolean hasKey = redisTemplate.hasKey(key);
            // 缓存存在，进行删除
            if (hasKey) {
                redisTemplate.delete(key);
            }
            return true;
        } else {
            return false;
        }
    }
    /**
     * 修改一行数据：
     * 先修改mysql数据库，再将缓存的数据删除即可，不直接更新缓存，效率太低。
     */
    @Override
    public boolean updateManager(Manager manager) {
        //返回影响行数，为1表示修改成功
        int result = jdbcTemplate.update("update manager set account=?,password=?,phone=?,email=? where managerid=?",
                manager.getAccount(),manager.getPassword(),manager.getPhone(),manager.getEmail(),manager.getManagerid());
        if(result > 0){
            // 判断是否缓存存在
            String key = "manager_" + manager.getManagerid();
            Boolean hasKey = redisTemplate.hasKey(key);
            // 缓存存在，进行删除
            if (hasKey) {
                redisTemplate.delete(key);
            }
            return true;
        }
        return false;
    }

    @Override
    public Manager getManagerByManagerid(String managerid) {
        // 从缓存中 取出信息
        String key = "manager_" + managerid;
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String str = (String) operations.get(key);
            return new Gson().fromJson(str, Manager.class);
        }
        //缓存中不存在
        RowMapper<Manager> rowMapper = new BeanPropertyRowMapper<Manager>(Manager.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from manager where managerid = ?",rowMapper,managerid);
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Manager s=(Manager)object;
        // 插入缓存中
        String str = new Gson().toJson(s);
        operations.set(key, str,60*10, TimeUnit.SECONDS);//向redis里存入数据,设置缓存时间为10min
        return s;
    }

    @Override
    public Manager getManagerByEmail(String email) {
        // 从缓存中 取出信息
        String key = "manager_" + email;
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String str = (String) operations.get(key);
            return new Gson().fromJson(str, Manager.class);
        }
        //缓存中不存在
        RowMapper<Manager> rowMapper = new BeanPropertyRowMapper<Manager>(Manager.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from manager where email = ?",rowMapper,email);
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Manager s=(Manager)object;
        // 插入缓存中
        String str = new Gson().toJson(s);
        operations.set(key, str,60*10, TimeUnit.SECONDS);//向redis里存入数据,设置缓存时间为10min
        return s;
    }

    @Override
    public Manager getManagerByAccount(String account) {
        // 从缓存中 取出信息
        String key = "manager_" + account;
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String str = (String) operations.get(key);
            return new Gson().fromJson(str, Manager.class);
        }
        //缓存中不存在
        RowMapper<Manager> rowMapper = new BeanPropertyRowMapper<Manager>(Manager.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from manager where account = ?",rowMapper,account);
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Manager s=(Manager)object;
        // 插入缓存中
        String str = new Gson().toJson(s);
        operations.set(key, str,60*10, TimeUnit.SECONDS);//向redis里存入数据,设置缓存时间为10min
        return s;
    }

    @Override
    public List<Manager> getAllManager() {
        String key = "manager_list";
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String redisList = (String) operations.get(key);

            Type type = new TypeToken<List<Manager>>() {}.getType();
            List<Manager> list =  new Gson().fromJson(redisList,type);
            return list;
        }
        //缓存中不存在
        RowMapper<Manager> rowMapper = new BeanPropertyRowMapper<Manager>(Manager.class);
        List<Manager> list = jdbcTemplate.query("select * from manager order by managerid DESC ",rowMapper);
        String toJson = new Gson().toJson(list);
        // 加到缓存中
        operations.set(key, toJson, 10*60, TimeUnit.SECONDS);
        return list;
    }
}
