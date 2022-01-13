package com.ooad.system_management.Controller;

import com.alibaba.fastjson.JSON;
import com.ooad.system_management.Dao.Impl.ResultFactory;
import com.ooad.system_management.Pojo.*;
import com.ooad.system_management.Service.Impl.*;
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
    private StaffServiceImpl staffService;
    @Autowired
    private ManagerServiceImpl managerService;
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
         System.out.println(user.getWhichpeople());
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
                     Student stu=studentService.getStudentByEmail(student.getEmail());
                     uid=stu.getStudentid();
                     break;
                 }
                 Student stu=studentService.getStudentByAccount(student.getAccount());
                 uid=stu.getStudentid();

                 break;
             case 1:
                 Staff staff=user.toStaff(user);
                 System.out.println(user.toString());
                 if (!staffService.judgeStaffByAccount(staff)) {
                     //用户名不存在，判断是否为Emial登录用户
                     staff.setEmail(staff.getAccount());
                     if (!staffService.judgeStaffByEmail(staff)) {
                         String message = String.format("登陆失败，账号/密码信息不正确。");
                         return ResultFactory.buildFailResult(message);
                     }
                     Staff sta=staffService.getStaffByEmail(staff.getEmail());
                     uid=sta.getStaffid();
                     break;
                 }
                 Staff sta=staffService.getStaffByAccount(staff.getAccount());
                 uid=sta.getStaffid();
                 break;
             case 2:
                 Manager manager=user.toManager(user);
                 System.out.println(user.toString());
                 if (!managerService.judgeManagerByAccount(manager)) {
                     //用户名不存在，判断是否为Emial登录用户
                     manager.setEmail(manager.getAccount());
                     if (!managerService.judgeManagerByEmail(manager)) {
                         String message = String.format("登陆失败，账号/密码信息不正确。");
                         return ResultFactory.buildFailResult(message);
                     }
                     Manager ma=managerService.getManagerByEmail(manager.getEmail());
                     uid=ma.getManagerid();
                     break;
                 }
                 Manager ma=managerService.getManagerByAccount(manager.getAccount());
                 uid=ma.getManagerid();
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
     * 传参(json) email,whichpeople
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
     * 职工类型： 传参(json) code,whichpeople,staffid,account,password,sta_name,sex,college,department,nativeplace,address,phone,email,otherinformation
     * 管理员类型： 传参(json) code,whichpeople,managerid,account,password,phone,email
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
     * 功能：获取用户信息
     * 路径 /user/getUser
     * 传参(json):whichpeple,studentid/staffid/managerid/account/email
     * 返回值(json--Result) code,message,data(Student/Staff/Manager)一个完整的Student/Staff/Manager类实例
     * 类的具体字段见 Src/Pojo/..
     */
    @CrossOrigin
    @PostMapping(value ="/user/getUser")
    @ResponseBody
    public Result getUser(@Valid @RequestBody User user){
        switch (user.getWhichpeople()){
            case 0:
                Student student=user.toStudent(user);
                if(student.getStudentid()!=null){
                    return  ResultFactory.buildSuccessResult(studentService.getStudentByStudentid(student.getStudentid()));
                }else if(student.getAccount()!=null){
                    return ResultFactory.buildSuccessResult(studentService.getStudentByAccount(student.getAccount()));
                }
                else if(student.getEmail()!=null){
                    return ResultFactory.buildSuccessResult(studentService.getStudentByEmail(student.getEmail()));
                }
                break;
            case 1:
                Staff staff=user.toStaff(user);
                if(staff.getStaffid()!=null){
                    return  ResultFactory.buildSuccessResult(staffService.getStaffByStaffid(staff.getStaffid()));
                }else if(staff.getAccount()!=null){
                    return ResultFactory.buildSuccessResult(staffService.getStaffByAccount(staff.getAccount()));
                }
                else if(staff.getEmail()!=null){
                    return ResultFactory.buildSuccessResult(staffService.getStaffByEmail(staff.getEmail()));
                }
                break;
            case 2:
                Manager manager=user.toManager(user);
                if(manager.getManagerid()!=null){
                    return  ResultFactory.buildSuccessResult(managerService.getManagerByManagerid(manager.getManagerid()));
                }else if(manager.getAccount()!=null){
                    return ResultFactory.buildSuccessResult(managerService.getManagerByAccount(manager.getAccount()));
                }
                else if(manager.getEmail()!=null){
                    return ResultFactory.buildSuccessResult(managerService.getManagerByEmail(manager.getEmail()));
                }
                break;
            default:
                break;
        }
        return ResultFactory.buildFailResult("获取用户信息失败!");
    }
    /*
     * 请求方式：post
     * 功能：删除用户信息
     * 路径 /user/deleteUser
     * 传参(json) whichpeople,studentid/staffid/managerid
     * 返回值 (json--Result) code,message,data(str)
     * */
    @CrossOrigin
    @PostMapping(value ="/user/deleteUser")
    @ResponseBody
    public Result deleteLightedStation(@Valid @RequestBody User user){
        switch (user.getWhichpeople()){
            case 0:
                Student student=user.toStudent(user);
                if(!studentService.deleteStudent(student.getStudentid())){
                    return ResultFactory.buildFailResult("删除用户信息失败");
                }
                break;
            case 1:
                Staff staff=user.toStaff(user);
                if(!staffService.deleteStaff(staff.getStaffid())){
                    return ResultFactory.buildFailResult("删除用户信息失败");
                }
                break;
            case 2:
                Manager manager=user.toManager(user);
                if(!managerService.deleteManager(manager.getManagerid())){
                    return ResultFactory.buildFailResult("删除用户信息失败");
                }

                break;
            default:
                break;
        }
        return ResultFactory.buildSuccessResult("已成功删除用户信息！");
    }

    /*
     * 请求方式：post
     * 功能：修改用户信息
     * 路径 /user/updateUser
     * 学生类型：传参(json) whichpeople,studentid,account,password,stu_name,sex,grade,college,class_,tutor_name,dormitory,nativeplace,address,phone,email,otherinformation
     * 职工类型： 传参(json) whichpeople,staffid,account,password,sta_name,sex,college,department,nativeplace,address,phone,email,otherinformation
     * 管理员类型： 传参(json) whichpeople,managerid,account,password,phone,email
     * 返回值(json--Result) code,message,data(Str)
     * */
    @CrossOrigin
    @PostMapping(value ="/user/updateUser")
    @ResponseBody
    public Result updateUser(@Valid @RequestBody User user){
        switch (user.getWhichpeople()){
            case 0:
                Student student=user.toStudent(user);
                if(!studentService.updateStudent(student)){
                    return ResultFactory.buildFailResult("修改用户信息失败");
                }
                break;
            case 1:
                Staff staff=user.toStaff(user);
                if(!staffService.updateStaff(staff)){
                    return ResultFactory.buildFailResult("修改用户信息失败");
                }
                break;
            case 2:
                Manager manager=user.toManager(user);
                if(!managerService.updateManager(manager)){
                    return ResultFactory.buildFailResult("修改用户信息失败");
                }
                break;
            default:
                break;
        }
        return ResultFactory.buildSuccessResult("已成功修改用户信息！");
    }

    /*
     * 请求方式：post
     * 功能：增添用户信息
     * 路径 /user/insertUser
     * 学生类型：传参(json) whichpeople,studentid,account,password,stu_name,sex,grade,college,class_,tutor_name,dormitory,nativeplace,address,phone,email,otherinformation
     * 职工类型： 传参(json) whichpeople,staffid,account,password,sta_name,sex,college,department,nativeplace,address,phone,email,otherinformation
     * 管理员类型： 传参(json) whichpeople,managerid,account,password,phone,email
     * 返回值(json--Result) code,message,data(Str)
     * */
    @CrossOrigin
    @PostMapping(value ="/user/insertUser")
    @ResponseBody
    public Result insertUser(@Valid @RequestBody User user){
        switch (user.getWhichpeople()){
            case 0:
                Student student=user.toStudent(user);
                if(!studentService.insertStudent(student)){
                    return ResultFactory.buildFailResult("新增用户信息失败");
                }
                break;
            case 1:
                Staff staff=user.toStaff(user);
                if(!staffService.insertStaff(staff)){
                    return ResultFactory.buildFailResult("新增用户信息失败");
                }
                break;
            case 2:
                Manager manager=user.toManager(user);
                if(!managerService.insertManager(manager)){
                    return ResultFactory.buildFailResult("新增用户信息失败");
                }
                break;
            default:
                break;
        }
        return ResultFactory.buildSuccessResult("已成功新增用户信息！");
    }

    /*
     * 请求方式：post
     * 功能：找回密码
     * 路径 /user/findPassword
     * 传参(json) email
     * 返回值(json--Result) code,message,data(str)
     * */
    @CrossOrigin
    @PostMapping(value = "/user/findPassword")
    @ResponseBody
    public Result findPassWord(@Valid @RequestBody User user){
        if(!eMailService.findPassword_sendEmail(user.getEmail())){
            return ResultFactory.buildFailResult("此邮箱非您注册时使用的邮箱,找回失败！");
        }
        return ResultFactory.buildSuccessResult("找回成功,密码已发送至您的邮箱！");
    }

    /*
     * 请求方式：post
     * 功能：修改用户密码
     * 路径 /user/changePassword
     * 传参(json) email,password,newPassword
     * 返回值(json--Result) code,message,data(Str)
     * */
    @CrossOrigin
    @PostMapping(value = "/user/changePassword")
    @ResponseBody
    public Result changePassword(@Valid @RequestBody User user){
        if(!eMailService.changePassword(user)){
            return ResultFactory.buildFailResult("信息有误,修改失败！");
        }
        return ResultFactory.buildSuccessResult("修改密码成功！");
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
