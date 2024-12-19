package com.kun.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kun.entry.User;
import com.kun.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
//仅需要创建一个UserDetails对象,使用@Companent注解,省略Config内创建DBUserDetailsManager对象
@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {
    
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        } else {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            //RBAC:基于角色的用户访问:将用户的权限分配和管理与角色相关联,让不同的角色拥有不同的权限
            authorities.add(()->"USER_LIST");
            authorities.add(()->"USER_ADD");
            //组装userDetails对象
            return new org.springframework.security.core.userdetails.User(

                    user.getUsername(),
                    user.getPassword(),
                    user.getEnabled(),
                    true, //用户账号是否过期
                    true, //用户凭证是否过期
                    true, //用户是否未被锁定
                    authorities); //权限列表

//            //添加角色权限
//             return org.springframework.security.core.userdetails.User
//                    .withUsername(user.getUsername())
//                    .password(user.getPassword())
//                    .disabled(!user.getEnabled())//是否没有权限
//                    .credentialsExpired(false)//是否过期
//                    .accountLocked(false)//用户是否被锁定
//                    .roles("ADMIN")//添加成员角色权限
//                    .authorities("USER_LIST","USER_ADD")//添加用户访问权限,会覆盖前者角色权限,二者不能同时使用
//                    .build();
        }
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        return null;
    }

    @Override
    public void createUser(UserDetails userDetails) {
        //实现向数据库中插入对应的用户信息
        User user = new User();
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setEnabled(true);
        userMapper.insert(user);
    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }
}