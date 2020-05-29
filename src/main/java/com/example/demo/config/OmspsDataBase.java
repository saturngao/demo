//package com.example.demo.config;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//@Configuration
//@MapperScan(basePackages = OmspsDataBase.PACKAGE, sqlSessionFactoryRef = "omspsSqlSessionFactory")
//public class OmspsDataBase extends DataSource {
//    static final String PACKAGE = "com.haier.oms.openapi.dao.omsps";
//
//    @Value("${mybatis.type-aliases-package}")
//    private String typePackage;
//
//    @Value("${mybatis.mapperLocations.omsps}")
//    private String mapperLocation;
//
//    @ConfigurationProperties("spring.datasource.druid.omsps")
//    @Bean(name="omspsDataSource")
//    public javax.sql.DataSource dataSource(){
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "omspsTransactionManager")
//    public DataSourceTransactionManager omspsTransactionManager() {
//        return new DataSourceTransactionManager(dataSource());
//    }
//
//    @Bean(name = "omspsSqlSessionFactory")
//    public SqlSessionFactory omspsSqlSessionFactory(@Qualifier("omspsDataSource") javax.sql.DataSource omspsDataSource)
//            throws Exception {
//        return sqlSessionFactory(omspsDataSource, mapperLocation, typePackage);
//    }
//}