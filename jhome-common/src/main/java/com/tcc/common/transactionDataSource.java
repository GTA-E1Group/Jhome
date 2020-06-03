package com.tcc.common;

import lombok.Getter;
import lombok.Setter;



/*
   * <property name="driverClass" value="#{tccjdbc['jdbc.driverClassName']}"/>
       <property name="jdbcUrl" value="#{tccjdbc['tcc.jdbc.url']}"/>
       <property name="user" value="#{tccjdbc['jdbc.username']}"/>
       <property name="password" value="#{tccjdbc['jdbc.password']}"/>
       <property name="initialPoolSize" value="#{tccjdbc['c3p0.initialPoolSize']}"/>
       <property name="minPoolSize" value="#{tccjdbc['c3p0.minPoolSize']}"/>
       <property name="maxPoolSize" value="#{tccjdbc['c3p0.maxPoolSize']}"/>
       <property name="acquireIncrement" value="#{tccjdbc['c3p0.acquireIncrement']}"/>
       <property name="maxIdleTime" value="#{tccjdbc['c3p0.maxIdleTime']}"/>
       <property name="checkoutTimeout" value="#{tccjdbc['c3p0.checkoutTimeout']}"/>
   *
   *
   * */

@Getter
@Setter
public class transactionDataSource {
    private  String jdbcUrl;
    private  String user;
    private  String password;
    private  String initialPoolSize;
    private  String minPoolSize;
    private  String maxPoolSize;
    private  String acquireIncrement;
    private  String maxIdleTime;
    private  String checkoutTimeout;

}
