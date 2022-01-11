package com.ooad.system_management.Dao.Impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ooad.system_management.Dao.IStudentDao;
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
public class StudentDaoImpl implements IStudentDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 添加一行数据：
     * 直接添加到mysql数据库,将缓存中数据所在表删除（为了保证selectAll获取数据一致）
     */
    @Override
    public boolean insertStudent(Student student) {

        int result= jdbcTemplate.update("insert into student(studentid,stu_name,sex,grade,college,class,tutor_name,dormitory,nativeplace,address,phone,email,otherinformation) values(?,?,?,?,?,?,?,?,?,?,?,?,?)",
                student.getStudentid(),student.getName(),student.getSex(),student.getGrade(),student.getCollege(),student.getClass_(),student.getTutor_name(),student.getDormitory()
        ,student.getNativeplace(),student.getAddress(),student.getPhone(),student.getEmail(),student.getOtherInformation());
        if(result>0){
            // 判断是否缓存存在
            String key = "student_list";
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
     * 依据主键studentid
     * 先删除mysql数据库，再将缓存的数据删除即可
     */
    @Override
    public boolean deleteStudent(String studentid) {
        int result = jdbcTemplate.update("delete from station where sid = ?", studentid);
        if (result != 0) {
            // 判断是否缓存存在
            String key = "student_" + studentid;
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
    public boolean updateStudent(Student student) {
        //返回影响行数，为1表示修改成功
        int result = jdbcTemplate.update("update student set stu_name=?,sex=?,grade=?,college=?,class=?,tutor_name=?,dormitory=?,nativeplace=?,address=?,phone=?,email=?,otherinformation=? where studentid=?"
                ,student.getName(),student.getSex(),student.getGrade(),student.getCollege(),student.getClass_(),student.getTutor_name(),student.getDormitory()
                ,student.getNativeplace(),student.getAddress(),student.getPhone(),student.getEmail(),student.getOtherInformation(),student.getStudentid());
        if(result > 0){
            // 判断是否缓存存在
            String key = "student_" + student.getStudentid();
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
    public Student getUserByStudentid(String studentid) {
        // 从缓存中 取出信息
        String key = "station_" + studentid;
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String str = (String) operations.get(key);
            return new Gson().fromJson(str, Student.class);
        }
        //缓存中不存在
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from station where studentid = ?",rowMapper,studentid);
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Student s=(Student)object;
        // 插入缓存中
        String str = new Gson().toJson(s);
        operations.set(key, str,60*10, TimeUnit.SECONDS);//向redis里存入数据,设置缓存时间为10min


        return s;
    }

    @Override
    public Student getUserByEmail(String email) {
        // 从缓存中 取出信息
        String key = "station_" + email;
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String str = (String) operations.get(key);
            return new Gson().fromJson(str, Student.class);
        }
        //缓存中不存在
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from station where email = ?",rowMapper,email);
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Student s=(Student)object;
        //插入缓存
        String str = new Gson().toJson(s);
        operations.set(key, str,60*10, TimeUnit.SECONDS);//向redis里存入数据,设置缓存时间为10min

        return s;
    }

    @Override
    public Student getUserByUsername(String username) {
        // 从缓存中 取出信息
        String key = "station_" + username;
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String str = (String) operations.get(key);
            return new Gson().fromJson(str, Student.class);
        }
        //缓存中不存在
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
        Object object = null;
        try {
            object = jdbcTemplate.queryForObject("select * from station where stu_name = ?",rowMapper,username);
        } catch (EmptyResultDataAccessException e1) {
            //查询结果为空，返回null
            return null;
        }
        Student s=(Student)object;
        //插入缓存
        String str = new Gson().toJson(s);
        operations.set(key, str,60*10, TimeUnit.SECONDS);//向redis里存入数据,设置缓存时间为10min

        return s;
    }

    @Override
    public List<Student> getAllStudents() {
        String key = "student_list";
        Boolean hasKey = redisTemplate.hasKey(key);
        ValueOperations operations = redisTemplate.opsForValue();
        //缓存中存在
        if (hasKey) {
            String redisList = (String) operations.get(key);

            Type type = new TypeToken<List<Student>>() {}.getType();
            List<Student> list =  new Gson().fromJson(redisList,type);
            return list;
        }
        //缓存中不存在
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<Student>(Student.class);
        List<Student> list = jdbcTemplate.query("select * from student order by studentid DESC ",rowMapper);
        String toJson = new Gson().toJson(list);
        // 加到缓存中
        operations.set(key, toJson, 10*60, TimeUnit.SECONDS);
        return list;
    }
}
