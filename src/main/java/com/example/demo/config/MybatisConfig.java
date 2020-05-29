package com.example.demo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@MapperScan("com.example.demo.dao.mapper")
@Configuration
public class MybatisConfig {
    @Bean
    public String myInterceptor(SqlSessionFactory sqlSessionFactory) {
        sqlSessionFactory.getConfiguration().addInterceptor(new MyInterceptor());
//        sqlSessionFactory.getConfiguration().addInterceptor(executorInterceptor);
//        sqlSessionFactory.getConfiguration().addInterceptor(new ParamInterceptor());
//        sqlSessionFactory.getConfiguration().addInterceptor(new ResultInterceptor());
        return "interceptor";
    }

}