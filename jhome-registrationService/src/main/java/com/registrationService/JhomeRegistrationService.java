package com.registrationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer //启动EurekaServer
@SpringBootApplication
public class JhomeRegistrationService
{
    public static void main( String[] args )
    {
        System.out.println("jhome");
        SpringApplication.run(JhomeRegistrationService.class, args);
    }
}


