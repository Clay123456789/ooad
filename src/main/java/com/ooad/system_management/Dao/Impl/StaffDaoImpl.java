package com.ooad.system_management.Dao.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ooad.system_management.Dao.IStaffDao;
import com.ooad.system_management.Pojo.Staff;
import com.ooad.system_management.Pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Repository
public class StaffDaoImpl implements IStaffDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加一行数据：
     * 直接添加到mysql数据库,将缓存中数据所在表删除（为了保证selectAll获取数据一致）
     */
    @Override
    public boolean insertStaff(Staff staff) {
        int result= jdbcTemplate.update("insert into staff(staffid,account,password,sta_name,sex,college,department,nativeplace,address,phone,email,otherinformation) values(?,?,?,?,?,?,?,?,?,?,?,?)",
                staff.getStaffid(),staff.getAccount(),staff.getPassword(),staff.getSta_name(),staff.getSex(),staff.getCollege(),staff.getDepartment()
                ,staff.getNativeplace(),staff.getAddress(),staff.getPhone(),staff.getEmail(),staff.getOtherInformation());
        if(result>0){
            // 判断是否缓存存在
            String key = "staff_list";
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
    public boolean deleteStaff(String staffid) {
        int result = jdbcTemplate.update("delete from staff where staffid = ?", staffid);
        if (result != 0) {
            // 判断是否缓存存在
            String key = "staff_" + staffid;
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
    public boolean updateStaff(Staff staff) {
        //返回影响行数，为1表示修改成功
        int result = jdbcTemplate.update("update staff set account=?,password=?,sta_name=?,sex=?,college=?,department=?,nativeplace=?,address=?,phone=?,email=?,otherinformation=? where staffid=?"
                ,staff.getAccount(),staff.getPassword(),staff.getSta_name(),staff.getSex(),staff.getCollege(),staff.getDepartment()
                ,staff.getNativeplace(),staff.getAddress(),staff.getPhone(),staff.getEmail(),staff.getOtherInformation(),staff.getStaffid());
        if(result > 0){
            // 判断是否缓存存在
            String key = "student_" + staff.getStaffid();
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
    public Staff getStaffByStaffid(String staffid) {
        // 从缓存中 取出信息
        String key = "staff_" + staffid;
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String str = (String) operations.get(key);
            return new Gson().fromJson(str, Staff.class);
        }
        //缓存中不存在
        RowMapper<Staff> rowMapper = new BeanPropertyRowMapper<Staff>(Staff.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from staff where staffid = ?",rowMapper,staffid);
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Staff s=(Staff)object;
        // 插入缓存中
        String str = new Gson().toJson(s);
        operations.set(key, str,60*10, TimeUnit.SECONDS);//向redis里存入数据,设置缓存时间为10min
        return s;
    }

    @Override
    public Staff getStaffByEmail(String email) {
        // 从缓存中 取出信息
        String key = "staff_" + email;
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String str = (String) operations.get(key);
            return new Gson().fromJson(str, Staff.class);
        }
        //缓存中不存在
        RowMapper<Staff> rowMapper = new BeanPropertyRowMapper<Staff>(Staff.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from staff where email = ?",rowMapper,email);
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Staff s=(Staff)object;
        // 插入缓存中
        String str = new Gson().toJson(s);
        operations.set(key, str,60*10, TimeUnit.SECONDS);//向redis里存入数据,设置缓存时间为10min
        return s;
    }

    @Override
    public Staff getStaffByAccount(String account) {
        // 从缓存中 取出信息
        String key = "staff_" + account;
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String str = (String) operations.get(key);
            return new Gson().fromJson(str, Staff.class);
        }
        //缓存中不存在
        RowMapper<Staff> rowMapper = new BeanPropertyRowMapper<Staff>(Staff.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from staff where account = ?",rowMapper,account);
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Staff s=(Staff)object;
        // 插入缓存中
        String str = new Gson().toJson(s);
        operations.set(key, str,60*10, TimeUnit.SECONDS);//向redis里存入数据,设置缓存时间为10min
        return s;
    }


    @Override
    public List<Staff> getAllStaff() {
        String key = "staff_list";
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String redisList = (String) operations.get(key);
            Type type = new TypeToken<List<Staff>>() {}.getType();
            List<Staff> list =  new Gson().fromJson(redisList,type);
            return list;
        }
        //缓存中不存在
        RowMapper<Staff> rowMapper = new BeanPropertyRowMapper<Staff>(Staff.class);
        List<Staff> list = jdbcTemplate.query("select * from staff order by staffid DESC ",rowMapper);
        String toJson = new Gson().toJson(list);
        // 加到缓存中
        operations.set(key, toJson, 10*60, TimeUnit.SECONDS);
        return list;
    }
}
