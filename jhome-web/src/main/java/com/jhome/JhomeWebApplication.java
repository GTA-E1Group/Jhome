package com.jhome;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})//忽略对数据源 的配置
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
/*@EnableHystrix*/
//@EnableDiscoveryClient
//@ComponentScan({"com.jhome.autoconfiguration"})
//@MapperScan("")
public class JhomeWebApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(JhomeWebApplication.class).bannerMode(Banner.Mode.OFF).run(args);

    }


}
