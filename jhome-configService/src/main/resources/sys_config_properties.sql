DROP TABLE IF EXISTS `sys_config_properties`;
CREATE TABLE `sys_config_properties`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `paramName` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '参数名',
  `key` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '键',
  `value` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '值',
  `application` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '应用名称',
  `isEnable` int(11) DEFAULT 0 COMMENT '是否启用 0:启用 1:禁用',
  `profile` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '环境',
  `label` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '分支',
  `createTime` datetime(0) DEFAULT NULL COMMENT '创建时间',
  `createBy` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime(0) DEFAULT NULL COMMENT '修改时间',
  `updateBy` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '修复人',
  `isPublicComponent` int(11) DEFAULT 0 COMMENT '是否是公共组件 0不是 1是',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
-- 	***************************************************************系统标准产品，简化部署，分部署始化数据*********************************************************
 INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (1, '数据库配置-地址', 'spring.datasource.url', 'jdbc:mysql://10.1.241.152:3306/gta_uc?serverTimezone=UTC&useSSL=false', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (2, '数据库配置-用户名', 'spring.datasource.username', 'root', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (3, '数据库配置-密码', 'spring.datasource.password', 'root152', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (4, 'redis配置-地址', 'spring.redis.host', '10.1.136.38', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (5, 'redis配置-默认数据库', 'spring.redis.database', '0', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (6, 'redis配置-端口', 'spring.redis.port', '6379', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (7, 'redis配置-密码', 'spring.redis.password', '6666', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (8, '注册中心配置-地址', 'eureka.client.serviceUrl.defaultZone', 'http://gtanihao:123456@10.1.136.38:9100/zlbzxt/eureka/', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (9, '注册中心配置-当前服务部署地址', 'eureka.instance.ipAddress', '10.1.136.38', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (10, '任务链tomcat-端口号', 'server.port', '8300', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (11, '单点登陆配置-CAS服务器', 'lux.sysproperties.casConfig.serverUrl', 'http://zhxy.edu.gtafe.com', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (12, '单点登陆配置-项目地址', 'lux.sysproperties.casConfig.projectUrl', 'http://10.1.20.58:8100/zlbzxt/account', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (13, '单点登陆配置-前端部署地址', 'lux.sysproperties.casConfig.redirectUrl', 'http://10.1.136.38:9101/#/sslogin', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (14, '单点登陆配置-是否启用CAS认证', 'lux.sysproperties.casConfig.isEnable', 'false', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (15, '系统退出-配置', 'lux.sysproperties.callbackUrl', 'http://10.1.136.38:9101/#/login', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (16, '消息中心配置-部署地址', 'lux.sysproperties.AgreementServiceConfig.messageCenterUrl', '10.1.136.38', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (17, '消息中心配置-部署地址-端口号', 'lux.sysproperties.AgreementServiceConfig.port', '8899', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (18, '消息中心配置-部署地址-消息长度', 'lux.sysproperties.AgreementServiceConfig.maxLength', '600', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (19, 'redis配置-地址', 'spring.redis.host', '10.1.136.38', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (20, 'redis配置-默认数据库', 'spring.redis.database', '0', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (21, 'redis配置-端口', 'spring.redis.port', '6379', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (22, 'redis配置-密码', 'spring.redis.password', '6666', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (23, '注册中心配置-地址', 'eureka.client.serviceUrl.defaultZone', 'http://gtanihao:123456@10.1.136.38:9100/zlbzxt/eureka/', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (24, '注册中心配置-当前服务部署地址', 'eureka.instance.ipAddress', '10.1.136.38', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (25, 'UC认证配置-部署地址', 'uc-apiconfig.url', 'http://10.1.135.40:5020', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (26, 'UC认证配置-加密秘钥', 'uc-apiconfig.signature', 'ky6xbsOp1UotNvz3H7fI', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (27, 'UC认证配置-产品ID', 'uc-apiconfig.appId', 'OhtZG', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (28, '账户服务tomcat-端口号', 'server.port', '8300', 'Lux-account', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (29, '数据库配置master-地址', 'spring.datasource.dynamic.datasource.master.url', 'jdbc:mysql://10.1.241.152:3306/gta_ebd?serverTimezone=UTC&useSSL=false', 'bigscreen', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (30, '数据库配置master-用户名', 'spring.datasource.dynamic.datasource.master.root', 'root', 'bigscreen', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (31, '数据库配置master-密码', 'spring.datasource.dynamic.datasource.master.password', 'root152', 'bigscreen', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (32, '数据库配置master-地址', 'spring.datasource.dynamic.datasource.datacentr.url', 'jdbc:mysql://10.1.241.152:3306/gta_ebd?serverTimezone=UTC&useSSL=false', 'bigscreen', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (33, '数据库配置master-用户名', 'spring.datasource.dynamic.datasource.datacentr.root', 'root', 'bigscreen', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (34, '数据库配置master-密码', 'spring.datasource.dynamic.datasource.datacentr.password', 'root152', 'bigscreen', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (35, 'redis配置-地址', 'spring.redis.host', '10.1.136.38', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (36, 'redis配置-默认数据库', 'spring.redis.database', '0', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (37, 'redis配置-端口', 'spring.redis.port', '6379', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (38, 'redis配置-密码', 'spring.redis.password', '6666', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (39, '注册中心配置-地址', 'eureka.client.serviceUrl.defaultZone', 'http://gtanihao:123456@10.1.136.38:9100/zlbzxt/eureka/', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (40, '注册中心配置-当前服务部署地址', 'eureka.instance.ipAddress', '10.1.136.38', 'Lux-taskchain', 0, 'dev', 'master', NULL, NULL, NULL, '', 1);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (41, '大屏tomcat-端口号', 'server.port', '6410', 'bigscreen', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);
INSERT INTO `sys_config_properties`(`id`, `paramName`, `key`, `value`, `application`, `isEnable`, `profile`, `label`, `createTime`, `createBy`, `updateTime`, `updateBy`, `isPublicComponent`) VALUES (42, '授权码', 'authorizationCode', '00000000000', 'Lux-ConfigService', 0, 'dev', 'master', NULL, NULL, NULL, '', 0);






truncate sys_config_properties;

SELECT * from sys_config_properties;

UPDATE   sys_config_properties set isPublicComponent=1 ,isEnable=0 where   paramName like '%redis%' or  paramName like '%注册中心%' or  paramName like '%消息中心%'or  paramName like '%系统退出-配置%';

SELECT * from sys_config_properties;

-- 公共组件
SELECT * from sys_config_properties where  isPublicComponent=1;
GROUP BY paramName;
 -- 其他组件
SELECT * from sys_config_properties where  isPublicComponent=0  and application='Lux-taskchain';
SELECT * from sys_config_properties where  isPublicComponent=0  and application='Lux-account';
SELECT * from sys_config_properties where  isPublicComponent=0  and application='bigscreen';

