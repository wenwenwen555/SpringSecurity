//package com.kun;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.impl.crypto.JwtSigner;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Date;
//import java.util.UUID;
//
//@SpringBootTest
//public class JwtTest {
//    @Test
//    public void JwtCreate(){
//        JwtBuilder jwtBuilder = Jwts.builder()
//                //随机生成ID
//                .setId(UUID.randomUUID().toString())
//                //设置用户
//                .setIssuer("ww")
//                //设置创建时间
//                .setExpiration(new Date())
//                //加密算法和密钥
//                .signWith(SignatureAlgorithm.HS256, "abcd");
//
//        String compact = jwtBuilder.compact();
//        System.out.println(compact);
//        //eyJhbGciOiJIUzI1NiJ9
//        // .eyJqdGkiOiIxNmM4MTM0OS02MmM4LTRmNjEtOWU2Mi0wYTI5NzRlNWEzMDMiLCJpc3MiOiJ3dyIsImV4cCI6MTczMjY4MTA4MH0
//        // .2vTkxXcYP2_8KGwxAoa_DEF9avSAX75y0ctbms4D9OI
//
//        //解析
//        Jwts.parser().parse("abcd");
//
//    }
//}
