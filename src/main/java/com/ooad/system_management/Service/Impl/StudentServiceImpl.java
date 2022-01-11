package com.ooad.system_management.Service.Impl;

import com.ooad.system_management.Dao.Impl.StudentDaoImpl;
import com.ooad.system_management.Pojo.Student;
import com.ooad.system_management.Service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentDaoImpl studentDao;

    @Override
    public boolean insertStudent(Student student){return studentDao.insertStudent(student);}
    @Override
    public boolean deleteStudent(String studentid){return studentDao.deleteStudent(studentid);}
    @Override
    public boolean updateStudent(Student user){return studentDao.updateStudent(user);}
    @Override
    public Student getUserByStudentid(String studentid){return studentDao.getUserByStudentid(studentid);}
    @Override
    public Student getUserByEmail(String email){return studentDao.getUserByStudentid(email);}
    @Override
    public Student getUserByUsername(String username){return studentDao.getUserByStudentid(username);}
    @Override
    public List<Student> getAllStudents(){return studentDao.getAllStudents();}
}
