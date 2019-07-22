package com.qdu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


/**
 * Created by Alpha-LGC on 2019/1/21.
 */
@SpringBootApplication(scanBasePackages = "com.qdu")
@MapperScan(basePackages = "classpath:com.qdu.mapper")
@ComponentScan(basePackages = "com.qdu")
@EnableAspectJAutoProxy(proxyTargetClass = true,exposeProxy = true)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
