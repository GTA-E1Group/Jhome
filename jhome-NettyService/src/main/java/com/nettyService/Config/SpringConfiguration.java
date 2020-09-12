package com.nettyService.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : Daxv
 * @date : 12:14 2020/5/17 0017
        */

@Configuration
@ComponentScan("com.nettyService.*")//扫描注解
@Import(RpcConfiguration.class)
public class SpringConfiguration {

    @Autowired
    public ConfigInfo configInfo;
    /**
     * 分布式锁框架
     *
     * @return
     */
    //@Bean
    //@Conditional(RedissonConditional.class)
    public RedissonClient redissonClient() {
        String redisUrl= String.format(configInfo.getRedisHost()+":%s",configInfo.getRedisPort());
        org.redisson.config.Config config = new org.redisson.config.Config();
        System.out.println(String.format("Redis地址：%s", redisUrl));
        config.useSingleServer().setAddress(redisUrl).setDatabase(0);
        //添加主从配置
        //config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        return  Redisson.create(config);
    }

}
