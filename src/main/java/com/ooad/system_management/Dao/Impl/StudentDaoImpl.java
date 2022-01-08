package com.ooad.system_management.Dao.Impl;

import com.ooad.system_management.Dao.IStudentDao;
import com.ooad.system_management.Pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return false;
    }

    @Override
    public boolean deleteStudent(String studentid) {
        return false;
    }

    @Override
    public boolean updateStudent(Student user) {
        return false;
    }

    @Override
    public Student getUserByStudentid(String studentid) {
        return null;
    }

    @Override
    public Student getUserByEmail(String email) {
        return null;
    }

    @Override
    public Student getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }
}
