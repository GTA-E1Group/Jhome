package com.jhome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer //启动EurekaServer
@SpringBootApplication
public class App 
{

    public static void main( String[] args )
    {
        System.out.println("jhome-one");
        SpringApplication.run(App.class, args);
    }
}


