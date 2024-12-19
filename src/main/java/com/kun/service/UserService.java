package com.kun.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.kun.entry.User;

/**
* @author WenHua
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-11-28 10:35:18
*/
public interface UserService extends IService<User> {

    void saveUserDetails(User user);
}
