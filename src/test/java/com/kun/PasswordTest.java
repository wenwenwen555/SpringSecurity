package com.kun;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;


@SpringBootTest
public class PasswordTest {

    @Resource
    PasswordEncoder passwordEncoder;
    @Test
    public void test1(){
        String encode = passwordEncoder.encode("1234");
        System.out.println(encode);
        boolean matches = passwordEncoder.matches("1234", "$2a$10$tkHyZNweoBHPUsEh20XLAuG8jMNPyPah8VCmmj/CfSp1sPTLSqDWa");
        System.out.println(matches);
    }

    @Test
    void testPassword() {

        // 工作因子，默认值是10，最小值是4，最大值是31，值越大运算速度越慢
        PasswordEncoder encoder = new BCryptPasswordEncoder(4);
        //明文："password"
        //密文：result，即使明文密码相同，每次生成的密文也不一致
        String result = encoder.encode("password");
        System.out.println(result);

        //密码校验
        Assert.isTrue(encoder.matches("password", result), "密码不一致");
    }
}
