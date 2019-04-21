package com.hubin.configs.datasource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.hubin.utils.enums.DBTypeEnum;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 *
 * @author hubin
 * @title: 数据源配置
 * @description:
 * @date: 2019/4/15 10:04
 */
@Configuration
//@EnableTransactionManagement
public class DefineDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.druid.jxc")
    public DataSource jxcDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.finance")
    public DataSource financeDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "defineDataSource")
    public DataSource defineDataSource() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DBTypeEnum.jxcDataSource.getValue(), jxcDataSource());
        dataSourceMap.put(DBTypeEnum.financeDatasource.getValue(), financeDataSource());

        return new DefineDataSourceHolder(jxcDataSource(), dataSourceMap);
    }


    /**
     * 事务配置,考虑多数据源情况下
     * @return
     */
    /**
     * 配置事务管理器
     */

//    默认事务
//    @Bean
//    public PlatformTransactionManager txManager(DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }

}
