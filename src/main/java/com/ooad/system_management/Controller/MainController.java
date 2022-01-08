package com.ooad.system_management.Controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;


@EnableAutoConfiguration
@RestController
public class MainController {

    @CrossOrigin
    @RequestMapping("/hello")
    public String hello(String username){
        return "Hello,"+username+"!";
    }

}
