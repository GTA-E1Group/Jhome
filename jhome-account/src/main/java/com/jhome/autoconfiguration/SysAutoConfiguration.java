package com.jhome.autoconfiguration;

import com.alibaba.druid.pool.DruidDataSource;
import com.daxu.common.BatchExcel.ExcelteEngine;
import com.daxu.common.Bus.PushTypeBase;
import com.daxu.common.Cache.MemcachedManager;
import com.daxu.common.Http.HttpClient;
import com.daxu.common.Queue.Bus;
import com.daxu.common.Queue.Config;
import com.daxu.common.WebSocket.WebSocket;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 系统组件
 */
@Configuration(
        proxyBeanMethods = true
)
@MapperScan({"com.jhome.modules.sys.dao"})
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
        System.out.println(String.format("Redis地址：%s", spiro.getRedissonUrl().toString()));
        config.useSingleServer().setAddress(spiro.getRedissonUrl().toString()).setDatabase(0);
        //添加主从配置
        //config.useMasterSlaveServers().setMasterAddress("").setPassword("").addSlaveAddress(new String[]{"",""});
        return Redisson.create(config);
    }

    /***
     * spring 整个 my
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setPassword(spiro.getDatasourceconfig().getPassWord());
        dataSource.setUsername(spiro.getDatasourceconfig().getUserName());
        dataSource.setUrl(spiro.getDatasourceconfig().getUrl());
        dataSource.setDbType(spiro.getDatasourceconfig().getType());
        dataSource.setDriverClassName(spiro.getDatasourceconfig().getDrivrerClassName());
        return dataSource;
    }

    @Bean
    @ConditionalOnMissingBean(value = {SqlSessionFactory.class})
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(this.dataSource());
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置spring 事务
     *
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager(dataSource);
        return dataSourceTransactionManager;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        return propertySourcesPlaceholderConfigurer;
    }


}
