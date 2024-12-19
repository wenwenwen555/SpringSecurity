package com.kun.config;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;
import java.util.HashMap;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity//开启security的自定义配置,SpringBoot项目中不需要
@EnableMethodSecurity//开启基于方法的授权
public class WebSecurityConfig {
    //默认配置
    //开启授权保护

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //authorizeRequests()：开启授权保护
        //anyRequest()：对所有请求开启授权保护
        //authenticated()：已认证请求会自动被授权

        http.authorizeHttpRequests(
                        authorize -> authorize
                                //具有USER_LIST权限的用户可以访问/user/list
                                .requestMatchers("/user/list").hasAuthority("USER_LIST")
                                //具有USER_ADD权限的用户可以访问/user/add
                                .requestMatchers("/user/add").hasAuthority("USER_ADD")
                                //根据角色授权
                                .requestMatchers("/user/**").hasRole("ADMIN")
                                //对所有请求开启授权保护
                                .anyRequest()
                                //已认证的请求会被自动授权
                                .authenticated()
                );

        http.formLogin((form) -> {
            form
                    .loginPage("/login").permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .failureUrl("/login?error")
                    .failureHandler(new MyAuthenticationFailureHandler())//认证失败时的处理
                    .successHandler(new MyAuthenticationSuccessHandler());//认证成功时的处理
        });
        //注销成功时的处理
        http.logout(logout -> {
            logout.logoutSuccessHandler(new MyLogoutSuccessHandler()); //注销成功时的处理
        });

        //错误处理
        http.exceptionHandling(exception -> {
            exception.authenticationEntryPoint(new MyAuthenticationEntryPoint());//请求未认证的接口
            exception.accessDeniedHandler(new MyAccessDeniedHandler());//请求的权限管理
//            //使用匿名内部类实现
//            exception.accessDeniedHandler(new AccessDeniedHandler() {
//                @Override
//                public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//                    //获取错误信息
//                    //String localizedMessage = authException.getLocalizedMessage();
//
//                    //创建结果对象
//                    HashMap result = new HashMap();
//                    result.put("code", -1);
//                    result.put("message", "没有使用权限");
//
//                    //转换成json字符串
//                    String json = JSON.toJSONString(result);
//
//                    //返回响应
//                    response.setContentType("application/json;charset=UTF-8");
//                    response.getWriter().println(json);
//                }
//            });
//            //使用lambda表达式
//            exception.accessDeniedHandler((request,response,accessDeniedException)->{
//                    HashMap result = new HashMap();
//                    result.put("code", -1);
//                    result.put("message", "没有使用权限");
//
//                    //转换成json字符串
//                    String json = JSON.toJSONString(result);
//
//                    //返回响应
//                    response.setContentType("application/json;charset=UTF-8");
//                    response.getWriter().println(json);
//            });
        });

        //会话管理
        http.sessionManagement(session -> {
            session
                    .maximumSessions(1)
                    .expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });

        //跨域
        http.cors(withDefaults());

        //关闭csrf攻击防御
        http.csrf((csrf) -> csrf.disable());

        return http.build();
    }


//        http
//                .authorizeRequests(authorize -> authorize.anyRequest().authenticated())
//                .formLogin(withDefaults())//表单授权方式
//                .httpBasic(withDefaults());//基本授权方式
//
//        return http.build();
//    }

////修改默认存在内存内的账号密码
//    @Bean
//    public UserDetailsService userDetailsService() {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser( //此行设置断点可以查看创建的user对象
//                User
//                        .withDefaultPasswordEncoder()
//                        .username("huan") //自定义用户名
//                        .password("password") //自定义密码
//                        .roles("USER") //自定义角色
//                        .build()
//        );
//        return manager;
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        DBUserDetailsManager manager = new DBUserDetailsManager();
//        return manager;
//    }
}
