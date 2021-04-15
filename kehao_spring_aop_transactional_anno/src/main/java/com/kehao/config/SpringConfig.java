package com.kehao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan("com.kehao")
@Import({JDBCConfig.class,MybatisConfig.class,TransactionManagerConfig.class})
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfig {
}
