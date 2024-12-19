package com.kun.controller;

import com.kun.entry.User;
import com.kun.service.UserService;
import com.kun.util.Result;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    public UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/register")
    public Result register(){
        System.out.println("------进入REGISTER------");
        return Result.build(null,200,"成功进入register");
    }

    @GetMapping("/list")
    //@PreAuthorize("hasRole('ADMIN') and authentication.name=='admin'")//注释使此方法只能让权限是ADMIN的角色来访问:鉴权()添加其他条件
    //@PreAuthorize("hasAnyAuthority('USER_ADD')")//用户权限使USER_ADD的用户才能访问
    public List<User> getList(){
        return userService.list();
    }

    @PostMapping("/add")
    //@PreAuthorize("hasRole('USER')")//注释使此方法只能让权限是USER的角色来访问:鉴权
    public void add(@RequestBody User user){
        userService.saveUserDetails(user);
    }
}
