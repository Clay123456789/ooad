package com.ooad.system_management.Service.Impl;


import com.ooad.system_management.Pojo.Manager;
import com.ooad.system_management.Pojo.Staff;
import com.ooad.system_management.Pojo.Student;
import com.ooad.system_management.Pojo.User;
import com.ooad.system_management.Service.IEMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Service
public class EMailServiceImpl implements IEMailService {
    @Autowired
    private JavaMailSender mailSender;//一定要用@Autowired
    @Autowired
    private StudentServiceImpl studentService;
    @Autowired
    private  StaffServiceImpl staffService;
    @Autowired
    private ManagerServiceImpl managerService;
    @Autowired
    private HttpSession httpSession;
    //application.properties中已配置的值
    @Value("${spring.mail.username}")
    private String from;
    private int whichpeople=-1;
    /**
     * 给前端输入的邮箱，发送验证码
     */
    public boolean sendRegistEmail(String email, HttpSession session,int whichpeople) {

        //该邮箱已经注册
        switch (whichpeople){
            case 0:
                if(studentService.getStudentByEmail(email)!=null){
                    return false;
                }
                break;
            case 1:
                if(staffService.getStaffByEmail(email)!=null){
                    return false;
                }
                break;
            case 2:
                if(managerService.getManagerByEmail(email)!=null){
                    return false;
                }
                break;
            default:
                return false;
        }
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setSubject("验证码邮件");//主题
            //生成随机数
            String code = randomCode();
            this.httpSession=session;
            //将随机数放置到session中
            httpSession.setAttribute("email",email);
            httpSession.setAttribute("code",code);

            mailMessage.setText("您收到的验证码是："+code);//内容

            mailMessage.setTo(email);//发给谁

            mailMessage.setFrom(from);//你自己的邮箱

            mailSender.send(mailMessage);//发送
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 随机生成6位数的验证码
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    /**
     * 检验验证码是否一致
     */
    public boolean registered(User user){
        System.out.println(user);
        //获取session中的验证信息
        String email = (String) httpSession.getAttribute("email");
        String code = (String) httpSession.getAttribute("code");

        //获取表单中的提交的验证信息
        String voCode = user.getCode();

        //如果email数据为空，或者不一致，注册失败
        if (email == null || email.isEmpty()||!email.equals(user.getEmail())){
            //return "error,请重新注册";
            return false;
        }else if (!code.equals(voCode)){
            //return "error,请重新注册";
            return false;
        }
        //将邮箱作为默认用户名
        //user.setAccount(user.getEmail());
        //将数据写入数据库
        switch (user.getWhichpeople()){
            case 0:
                studentService.insertStudent(user.toStudent(user));
                break;
            case 1:
                staffService.insertStaff(user.toStaff(user));
                break;
            case 2:
                managerService.insertManager(user.toManager(user));
                break;
            default:

                break;
        }
        //跳转成功页面
        return true;
    }

    @Override
    public boolean findPassword_sendEmail(String email) {
        if(studentService.getStudentByEmail(email)!=null||staffService.getStaffByEmail(email)!=null||managerService.getManagerByEmail(email)!=null){
            try {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setSubject("找回密码邮件");//主题
                String password=null;
                //保存密码
                if(studentService.getStudentByEmail(email)!=null){
                    password=studentService.getStudentByEmail(email).getPassword();
                }
                else if(staffService.getStaffByEmail(email)!=null){
                    password=staffService.getStaffByEmail(email).getPassword();
                }
                else {
                    password=managerService.getManagerByEmail(email).getPassword();
                }

                mailMessage.setText("您的密码是："+ password);

                mailMessage.setTo(email);//发给谁

                mailMessage.setFrom(from);//服务器邮箱

                mailSender.send(mailMessage);//发送
                return  true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
        }
        else

            return false;
    }

    @Override
    public boolean changePassword(User user) {
        String email=user.getEmail();
        if(studentService.getStudentByEmail(email)!=null||staffService.getStaffByEmail(email)!=null||managerService.getManagerByEmail(email)!=null) {
            String password=user.getPassword();
            String newPassword=user.getNewPassword();
            String comPassword=null;
            if(studentService.getStudentByEmail(email)!=null){
                Student student=studentService.getStudentByEmail(email);
                comPassword=student.getPassword();
                System.out.println("之前的密码"+comPassword);
                if(!comPassword.equals(password)) return false;
                else{
                    student.setPassword(newPassword);
                    studentService.updateStudent(student);
                }
            }
            else if(staffService.getStaffByEmail(email)!=null){
                Staff staff=staffService.getStaffByEmail(email);
                comPassword=staff.getPassword();
                if(!comPassword.equals(password)) return false;
                else{
                    staff.setPassword(newPassword);
                    staffService.updateStaff(staff);
                }
            }
            else {
                Manager manager=managerService.getManagerByEmail(email);
                comPassword=manager.getPassword();

                if(!comPassword.equals(password)) return false;
                else{
                    manager.setPassword(newPassword);
                    managerService.updateManager(manager);
                }
            }
            try{
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setSubject("修改密码邮件");//主题
                //保存密码-
                mailMessage.setText("您的账号已修改了密码，请确认是本人所为，注意账号安全");

                mailMessage.setTo(email);//发给谁

                mailMessage.setFrom(from);//服务器邮箱

                mailSender.send(mailMessage);//发送
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }



}
