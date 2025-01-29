package com.kun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kun.config.DBUserDetailsManager;
import com.kun.entry.User;
import com.kun.mapper.UserMapper;
import com.kun.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    private DBUserDetailsManager dbUserDetailsManager;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void saveUserDetails(User user) {
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder()// 默认密码加密方案
                .username(user.getUsername()) // 自定义用户名
                .password(user.getPassword()) // 自定义密码
                // .roles("USER") //自定义角色
                .build();

        dbUserDetailsManager.createUser(userDetails);
    }

    @Override
    public boolean deleteById(Integer id) {
        int deleteById = userMapper.deleteById(id);
        if (deleteById == 1) {
            return true;
        } else {
            return false;
        }
    }
}
