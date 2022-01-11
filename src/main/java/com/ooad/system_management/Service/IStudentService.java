package com.ooad.system_management.Service;

import com.ooad.system_management.Pojo.Student;

import java.util.List;

public interface  IStudentService {
    //增删改查方法
    boolean insertStudent(Student student);
    boolean deleteStudent(String studentid);
    boolean updateStudent(Student user);

    boolean judgeStudentByEmail(Student student);
    boolean judgeStudentByAccount(Student student);

    Student getUserByStudentid(String studentid);
    Student getUserByEmail(String email);
    Student getUserByAccount(String username);
    List<Student> getAllStudents();
}
