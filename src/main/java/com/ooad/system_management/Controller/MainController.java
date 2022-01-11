package com.ooad.system_management.Controller;

import com.alibaba.fastjson.JSON;
import com.ooad.system_management.Dao.Impl.ResultFactory;
import com.ooad.system_management.Pojo.Result;
import com.ooad.system_management.Pojo.Student;
import com.ooad.system_management.Pojo.Systemlog;
import com.ooad.system_management.Service.Impl.StudentServiceImpl;
import com.ooad.system_management.Service.Impl.SystemlogServiceImpl;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@EnableAutoConfiguration
@RestController
public class MainController {
    @Autowired
    private StudentServiceImpl studentService;
    private SystemlogServiceImpl systemlogService;
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


    /*
     * 请求方式：post
     * 功能：获取系统日志
     * 路径 /systemlog/getSystemlog
     * 传参(json):logid/operator/time
     * 返回值(json--Result) code,message,data(Systemlog)一个完整的日志类实例
     */
    @CrossOrigin
    @PostMapping(value ="/systemlog/getSystemlog")
    @ResponseBody
    public Result getSystemlog(@Valid @RequestBody Systemlog systemlog){
        if(systemlog.getLogid()!=null){

            return  ResultFactory.buildSuccessResult(systemlogService.searchLogByID(systemlog.getLogid()));
        }else if(systemlog.getOperator()!=null){
            return ResultFactory.buildSuccessResult(systemlogService.searchLogByTime(systemlog.getTime()));
        }
        else if(systemlog.getTime()!=null){
            return ResultFactory.buildSuccessResult(systemlogService.searchLogByOperator(systemlog.getOperator()));
        }
        return ResultFactory.buildFailResult("获取系统日志失败!");
    }

    /*
     * 请求方式：post
     * 功能：获取所有的系统日志
     * 路径 /systemlog/getSystemlog
     * 传参(json):code(0代表所有)
     * 返回值(json) String（用Result封装json中会有转义符干扰，故此接口直接返回结果）
     */
    @CrossOrigin
    @PostMapping(value ="/systemlog/getAllSystemlog")
    public Result getAllSystemlog (int code) throws Exception{
        List<Systemlog> list=null;
        if(code==0){
            list=systemlogService.getAllLog();
        }
        if(list==null){
            return ResultFactory.buildFailResult("获取全部系统日志失败");
        }else{
            String data= StringEscapeUtils.unescapeJavaScript(JSON.toJSONString(list));
            System.out.println(data);
            return ResultFactory.buildSuccessResult(data);
        }
    }



}
