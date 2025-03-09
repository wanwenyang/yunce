package net.xdclass;

import net.xdclass.util.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 **/
@SpringBootApplication
@MapperScan("net.xdclass.mapper")
@EnableTransactionManagement
@EnableFeignClients
@EnableDiscoveryClient
@EnableAsync
public class EngineApplication {
    public static void main(String[] args) {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(EngineApplication.class, args);
        SpringContextHolder.setApplicationContext(applicationContext);
    }
}
