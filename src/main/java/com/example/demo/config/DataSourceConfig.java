package com.example.demo.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE,sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {

    /**
     * dao 包
     */
    static final String PACKAGE = "com.haier.zjapp.scs.feeds.server.dao";

//    @Value("${mybatis.type-aliases-package}")
//    private String typePackage;

    /**
     * mapper路径
     */
    @Value("${mybatis.mapper-locations}")
    private String mapperLocations;

    @Resource
    private MybatisProperties mybatisProperties;

    /**
     * 数据源
     */
    @Bean("dataSource")
    public DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 事务管理
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * session工厂
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocations));
        sessionFactory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());
        return sessionFactory.getObject();
    }

}
