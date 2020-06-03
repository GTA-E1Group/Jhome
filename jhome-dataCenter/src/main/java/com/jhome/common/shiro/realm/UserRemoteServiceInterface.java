package com.jhome.common.shiro.realm;

import com.jhome.modules.config.FeignConfig;
import com.shiro.common.client.RemoteBaseInterface;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "jhome-account", configuration = FeignConfig.class) //这里的name对应调用服务的spring.applicatoin.name
public interface UserRemoteServiceInterface extends RemoteBaseInterface {

}
