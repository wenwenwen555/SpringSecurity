package com.kun.controller;

import com.kun.entry.User;
import com.kun.service.UserService;
import com.kun.util.Result;
import jakarta.annotation.Resource;
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
    public Result register() {
        System.out.println("------进入REGISTER------");
        return Result.build(null, 200, "成功进入register");
    }

    @GetMapping("/list")
    // @PreAuthorize("hasRole('ADMIN') and
    // authentication.name=='admin'")//注释使此方法只能让权限是ADMIN的角色来访问:鉴权()添加其他条件
    // @PreAuthorize("hasAnyAuthority('USER_ADD')")//用户权限使USER_ADD的用户才能访问
    public List<User> getList() {
        return userService.list();
    }

    @PostMapping("/add")
    // @PreAuthorize("hasRole('USER')")//注释使此方法只能让权限是USER的角色来访问:鉴权
    public void add(@RequestBody User user) {
        userService.saveUserDetails(user);
    }

    
    @DeleteMapping("/delete/id")
    public Result delete(@RequestParam Integer id) {
        // 对传入id进行非空和字符串非空校验
        if (id == null) {
            return Result.build("fail", 400,"传入的id不能为空");
        }else{
            boolean Res = userService.deleteById(id);
            if (Res == true) {
                return Result.build("success", 200,"删除成功");
            } else {
                return Result.build("fail", 400,"删除失败");
            }
        }
    }

    // 修改用户信息
    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        // 对传入的user进行非空和字符串非空校验
        if (user.getId() == null) {
            return Result.build("fail", 400,"传入的user不能为空");
        }else{
            boolean Res = userService.updateById(user);
            if (Res == true) {
                return Result.build("success", 200,"修改成功");
            } else {
                return Result.build("fail", 400,"修改失败");
            }
        }
    }
}
