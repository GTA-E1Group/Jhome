package com.service.config;

import com.bracket.common.Queue.Bus;
import com.bracket.common.Queue.Config;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : Daxv
 * @date : 12:14 2020/5/17 0017
 */

@Configuration
@ComponentScan(value = {"com.service.*"})//扫描注解
@Import(RpcConfiguration.class)
public class SpringConfiguration {

    @Autowired
    public ConfigInfo configInfo;

    //@Bean
    //@Conditional(RedissonConditional.class)
    public RedissonClient redissonClient() {
        String redisUrl = String.format(configInfo.getRedisHost() + ":%s", configInfo.getRedisPort());
        org.redisson.config.Config config = new org.redisson.config.Config();
        System.out.println(String.format("Redis地址：%s", redisUrl));
        config.useSingleServer().setAddress(redisUrl).setDatabase(0);
        //添加主从配置
        //config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        return Redisson.create(config);
    }

    /**
     * Bus 消息队列框架 自定义
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean({Bus.class})
    public Bus CreateMqQueue() {
        Config config = new Config();
        try {
            config.setHostName(configInfo.getHostName());
            config.setPassWord(configInfo.getPassWord());
            config.setUserName(configInfo.getUserName());
            config.setVirtualHost(configInfo.getVirtualHost());
        } catch (Exception ex) {
            System.out.println(String.format("错误错误错误错误错误 %s", ex.getMessage()));
        }
        return new Bus(config);
    }
}
