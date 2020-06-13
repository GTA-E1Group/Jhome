package com.jhome.autoconfiguration;

import com.alibaba.druid.pool.DruidDataSource;
import com.daxu.common.BatchExcel.ExcelteEngine;
import com.daxu.common.Bus.PushTypeBase;
import com.daxu.common.Cache.MemcachedManager;
import com.daxu.common.Http.HttpClient;
import com.daxu.common.Queue.Bus;
import com.daxu.common.Queue.Config;
import com.daxu.common.WebSocket.WebSocket;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 系统组件
 */
@Configuration(
        proxyBeanMethods = true
)

@PropertySource(value = "classpath:application.yml", ignoreResourceNotFound = true)
@EnableTransactionManagement
//@EnableConfigurationProperties({SysConfigurationPropertiesBean.class})
public class SysAutoConfiguration {
//        private SysConfigurationPropertiesBean spiro;
//    public SysAutoConfiguration(SysConfigurationPropertiesBean properties) {
//        this.spiro = properties;
//    }

    @Autowired
    private SysConfigurationPropertiesBean spiro;

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
            config.setHostName(spiro.getQueueconfig().getHostName());
            config.setPassWord(spiro.getQueueconfig().getPassWord());
            config.setUserName(spiro.getQueueconfig().getUserName());
            config.setVirtualHost(spiro.getQueueconfig().getVirtualHost());
        } catch (Exception ex) {
            System.out.println(String.format("错误错误错误错误错误 %s", ex.getMessage()));
        }
        return new Bus(config);
    }

    /**
     * HttpClient http轻轻框架 自定义
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean({HttpClient.class})
    public HttpClient CreateHttpClient() {
        return new HttpClient();
    }

    @Bean
    @ConditionalOnMissingBean({WebSocket.class})
    public void CreatewebSocket() {
    }

    /**
     * ExcelteEngine exl导入引擎框架 自定义
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean({ExcelteEngine.class})
    public void CreateBatchExcel() {
    }

    /**
     * MemcachedManager 框架 自定义
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean({MemcachedManager.class})
    public MemcachedManager CreateMenacedManage() {
        return new MemcachedManager();
    }


    /**
     * StringRedisTemplate
     *
     * @return
     */
    @Bean("StringRedisTemplate")
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate redisTemplate = new StringRedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 分布式锁框架
     *
     * @return
     */
    @Bean
    //@DependsOn("StringRedisTemplate")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redissonClient() {
        org.redisson.config.Config config = new org.redisson.config.Config();
        //System.out.println(String.format("Redis地址：%s", spiro.getRedissonUrl().toString()));
        config.useSingleServer()
                .setAddress(String.format("redis://%s:%s", spiro.getRedissConfig().getHost(), spiro.getRedissConfig().getPort()))
                //.setPassword(spiro.getRedissConfig().password)
                .setDatabase(spiro.getRedissConfig().getDatabase());
        //config.useSingleServer().setAddress(spiro.getRedissonUrl().toString()).setDatabase(0);
        //添加主从配置
        //config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        return Redisson.create(config);
    }

    /**
     * 分页
     *
     * @return
     */
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        //设置为true时，将会RowBoundls 第一个参数offset当成pageNum页码使用
        properties.setProperty("offsetAsPageNum", "true");
        //设置为true时，使用RowBounds分页会进行count查询
        properties.setProperty("rowBoundsWithCount", "true");
        //启用合理化查询，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页
        //未开启时如果pageNum<1或者pageNum>pages会返回空数据
        properties.setProperty("reasonable", "true");
        //配置mysql数据库方言
        properties.setProperty("dialect", "myssql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    @Bean
    @LoadBalanced
    @ConditionalOnMissingBean(RestTemplate.class)
    public RestTemplate restTemplate() {
        return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
    }


}
