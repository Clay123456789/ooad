package com.ooad.system_management.Service;

import com.ooad.system_management.Pojo.Student;

import java.util.List;

public interface  IStudentService {
    //增删改查方法
    boolean insertStudent(Student student);
    boolean deleteStudent(String studentid);
    boolean updateStudent(Student user);
    Student getStudentByStudentid(String studentid);
    Student getStudentByEmail(String email);
    Student getStudentByAccount(String account);
    List<Student> getAllStudents();

    //登陆判断方法
    boolean judgeStudentByEmail(Student student);
    boolean judgeStudentByAccount(Student student);
}
