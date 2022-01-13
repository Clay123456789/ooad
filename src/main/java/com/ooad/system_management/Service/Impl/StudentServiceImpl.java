package com.ooad.system_management.Service.Impl;

import com.ooad.system_management.Dao.Impl.StudentDaoImpl;
import com.ooad.system_management.Pojo.Student;
import com.ooad.system_management.Service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    //增删改查
    @Autowired
    private StudentDaoImpl studentDao;

    @Override
    public boolean insertStudent(Student student){return studentDao.insertStudent(student);}
    @Override
    public boolean deleteStudent(String studentid){return studentDao.deleteStudent(studentid);}
    @Override
    public boolean updateStudent(Student user){return studentDao.updateStudent(user);}

    @Override
    public Student getStudentByStudentid(String studentid){return studentDao.getStudentByStudentid(studentid);}
    @Override
    public Student getStudentByEmail(String email){return studentDao.getStudentByEmail(email);}
    @Override
    public Student getStudentByAccount(String account){return studentDao.getStudentByAccount(account);}
    @Override
    public List<Student> getAllStudents(){return studentDao.getAllStudents();}

    //登陆判断
    @Override
    public boolean judgeStudentByEmail(Student student) {
        return studentDao.judgeStudentByEmail(student);
    }

    @Override
    public boolean judgeStudentByAccount(Student student) {
        return studentDao.judgeStudentByAccount(student);
    }



}
