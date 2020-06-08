package com.jhome.autoconfiguration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
@Setter
@Getter
public class SysConfigurationPropertiesBean extends  SysProperties  {
    public QueueConfig queueconfig;//队列配置
    public String adminPath;//后台路径
    public String frontPath;//前台路径
    public String redissonUrl;
    public DataSourceConfig datasourceconfig;//自定义数据库配置
    /**
     * 队列服务
     */
    @Getter
    @Setter
    public static class QueueConfig {
        public String hostName;
        public String userName;
        public String passWord;
        public String virtualHost;

        @Override
        public String toString() {
            return "QueueConfig{" +
                    "hostName='" + hostName + '\'' +
                    ", userName='" + userName + '\'' +
                    ", passWord='" + passWord + '\'' +
                    ", virtualHost='" + virtualHost + '\'' +
                    '}';
        }
    }

    /**
     * 自定义数据源
     */
    @Getter
    @Setter
    public static class DataSourceConfig
    {
        public String userName;
        public String passWord;
        public String url;
        public String drivrerClassName;
        public String type;

    }

}