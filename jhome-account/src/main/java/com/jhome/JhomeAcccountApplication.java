package com.jhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//忽略对数据源 的配置
@EnableEurekaClient
@EnableDiscoveryClient
//@ComponentScan({"com.jhome.autoconfiguration"})
//@MapperScan("")
//@sysScan("com.jhome.modules.sys.web")
public class JhomeAcccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(JhomeAcccountApplication.class, args);
    }
}
