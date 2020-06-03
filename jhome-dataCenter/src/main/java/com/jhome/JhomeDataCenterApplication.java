package com.jhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
//@EnableFeignClients(basePackageClasses = {com.jhome.common.shiro.realm.UserRemoteServiceInterface.class})
@EnableFeignClients
public class JhomeDataCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(JhomeDataCenterApplication.class, args);
    }
}
