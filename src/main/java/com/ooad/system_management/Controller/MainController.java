package com.ooad.system_management.Controller;

import com.ooad.system_management.Dao.Impl.ResultFactory;
import com.ooad.system_management.Pojo.Result;
import com.ooad.system_management.Pojo.Student;
import com.ooad.system_management.Service.Impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@EnableAutoConfiguration
@RestController
public class MainController {
    @Autowired
    private StudentServiceImpl studentService;

    @CrossOrigin
    @RequestMapping("/hello")
    public String hello(String username){
        return "Hello,"+username+"!";
    }

    /*
     * 请求方式：post
     * 功能：获取学生信息
     * 路径 /student/getStudent
     * 传参(json):studentid/name/email
     * 返回值(json--Result) code,message,data(User)一个完整的Student类实例
     */
    @CrossOrigin
    @PostMapping(value ="/student/getStudent")
    @ResponseBody
    public Result getStudent(@Valid @RequestBody Student student){
        if(student.getStudentid()!=null){
            return  ResultFactory.buildSuccessResult(studentService.getUserByStudentid(student.getStudentid()));
        }else if(student.getName()!=null){
            return ResultFactory.buildSuccessResult(studentService.getUserByUsername(student.getName()));
        }
        else if(student.getEmail()!=null){
            return ResultFactory.buildSuccessResult(studentService.getUserByEmail(student.getEmail()));
        }
        return ResultFactory.buildFailResult("获取学生信息失败!");
    }









}
