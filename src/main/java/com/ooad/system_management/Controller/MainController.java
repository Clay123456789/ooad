package com.ooad.system_management.Controller;

import com.alibaba.fastjson.JSON;
import com.ooad.system_management.Dao.Impl.ResultFactory;
import com.ooad.system_management.Pojo.Result;
import com.ooad.system_management.Pojo.Student;
import com.ooad.system_management.Pojo.Systemlog;
import com.ooad.system_management.Pojo.User;
import com.ooad.system_management.Service.Impl.EMailServiceImpl;
import com.ooad.system_management.Service.Impl.StudentServiceImpl;
import com.ooad.system_management.Service.Impl.SystemlogServiceImpl;
import com.ooad.system_management.Utils.JWTUtil;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@EnableAutoConfiguration
@RestController
public class MainController {
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private SystemlogServiceImpl systemlogService;
    @Autowired
    private EMailServiceImpl eMailService;
    @CrossOrigin
    @RequestMapping("/hello")
    public String hello(String username){
        return "Hello,"+username+"!";
    }

     /*
     * 请求方式：post
     * 功能：登陆
     * 路径 /user/login
     * 传参(json) account,password,whichpeople
     * 返回值(json--Result) code,message,data(str)
     */
     @CrossOrigin
     @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
     @ResponseBody
     public Result login(@Valid @RequestBody User user, BindingResult bindingResult) {
         if (user.getAccount().equals("")||user.getPassword().equals("")) {
             String message = String.format("账号或密码不能为空！");
             return ResultFactory.buildFailResult(message);
         }
         if (bindingResult.hasErrors()) {
             String message = String.format("登陆失败，详细信息[%s]。", bindingResult.getFieldError().getDefaultMessage());
             return ResultFactory.buildFailResult(message);
         }
         String uid=null;
         switch (user.getWhichpeople()){
             //学生
             case 0:
                 Student student=user.toStudent(user);
                 //System.out.println(user.toString());
                 if (!studentService.judgeStudentByAccount(student)) {
                     //用户名不存在，判断是否为Emial登录用户
                     student.setEmail(student.getAccount());
                     if (!studentService.judgeStudentByEmail(student)) {
                         String message = String.format("登陆失败，账号/密码信息不正确。");
                         return ResultFactory.buildFailResult(message);
                     }
                     Student stu=studentService.getUserByEmail(student.getEmail());
                     uid=stu.getStudentid();
                 }
                 Student stu=studentService.getUserByAccount(student.getAccount());
                 uid=stu.getStudentid();

                 break;
             case 1:

                 break;
             case 2:

                 break;
             default:
                 String message = String.format("用户类型错误。");
                 return ResultFactory.buildFailResult(message);
         }

         //已注册
         Map<String, String> map = new HashMap<>(); //用来存放payload信息
         map.put("uid",uid);
         // 生成token令牌

         String token = JWTUtil.generateToken(map);
         System.out.println(JWTUtil.getTokenInfo(token));
         return ResultFactory.buildSuccessResult(token);
     }
    /*
     * 请求方式：post
     * 功能：发送注册邮箱
     * 路径 /user/sendRegistEmail
     * 传参(json) email,whichpeople(人员类别)
     * 返回值(json--Result) code,message,data(str)
     * */
    @CrossOrigin
    @PostMapping(value = "/user/sendRegistEmail")
    @ResponseBody
    public Result sendRegistEmail(@Valid @RequestBody User user , HttpSession httpSession) {
        /*
         * 使用HttpSession在服务器与浏览器建立对话，以验证邮箱验证码
         * */
        if (!eMailService.sendRegistEmail(user.getEmail(), httpSession,user.getWhichpeople())) {
            return ResultFactory.buildFailResult("发送失败！邮箱已注册或不可用");
        }
        return ResultFactory.buildSuccessResult("已发送验证码至邮箱！");
    }
    /*
     * 请求方式：post
     * 功能：注册新用户
     * 路径 /user/regist
     * 学生类型：传参(json) code,whichpeople,studentid,account,password,stu_name,sex,grade,college,class_,tutor_name,dormitory,nativeplace,address,phone,email,otherinformation
     *
     * 返回值(json--Result) code,message,data(str)
     * */
    @CrossOrigin
    @PostMapping(value = "/user/regist")
    @ResponseBody
    public Result regist(@Valid @RequestBody User user) {

        if (!eMailService.registered(user)) {
            return ResultFactory.buildFailResult("注册失败！验证码不一致");
        }
        return ResultFactory.buildSuccessResult("注册成功！");
    }
    /*
     * 请求方式：post
     * 功能：获取学生信息
     * 路径 /student/getStudent
     * 传参(json):studentid/name/email
     * 返回值(json--Result) code,message,data(Student)一个完整的Student类实例
     */
    @CrossOrigin
    @PostMapping(value ="/student/getStudent")
    @ResponseBody
    public Result getStudent(@Valid @RequestBody Student student){
        if(student.getStudentid()!=null){
            return  ResultFactory.buildSuccessResult(studentService.getUserByStudentid(student.getStudentid()));
        }else if(student.getAccount()!=null){
            return ResultFactory.buildSuccessResult(studentService.getUserByAccount(student.getAccount()));
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
