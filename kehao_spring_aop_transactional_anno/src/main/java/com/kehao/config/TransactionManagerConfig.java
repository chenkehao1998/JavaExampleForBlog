package com.kehao.config;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


public class TransactionManagerConfig {

    @Bean
    public DataSourceTransactionManager createDataSourceTransactionManager(DataSource dataSource){
        System.out.println("DataSourceTransactionManager");
        DataSourceTransactionManager manager = new DataSourceTransactionManager();
        manager.setDataSource(dataSource);
        return manager;
    }

}
