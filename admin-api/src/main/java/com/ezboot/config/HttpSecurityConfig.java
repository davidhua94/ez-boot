package com.ezboot.config;

/**
 * SpringSecurity配置
 * 先配置一个用户名为admin和wang的用户，密码均为123
 * @author scoundrel
 * 2019/9/27 21:34
 */

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class HttpSecurityConfig {
        //extends WebSecurityConfigurerAdapter{

    // 设置一个ADMIN角色名
    private static final String ADMIN = "ADMIN";

    // 设置一个USER角色名
    private static final String USER = "USER";

    /**
     * 默认的密码校验规则
     * @return
     */
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

    /**
     * 用户和角色相关配置
     * @param auth
     * @throws Exception
     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        // 设置一个默认密码，后续从数据库中获取
//        String password = passwordEncoder().encode("123");
//
//        auth.inMemoryAuthentication()
//                .withUser("admin").password(password).roles(ADMIN, USER)
//                .and()
//                .withUser("wang").password(password).roles(USER);
//    }

    /**
     * 访问admin路径需具备ADMIN权限
     * 访问user路径需具备USER权限
     * @param http
     * @throws Exception
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/admin/**")
//                .hasRole("ADMIN")
//                .antMatchers("/user/**")
//                .access("hasAnyRole('ADMIN', 'USER')")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/login")
//                .permitAll()
//                .and()
//                .csrf()
//                .disable();
//    }

}