package com.kun;

import com.kun.entry.User;
import com.kun.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMybatisPlus {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestMy(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
