package com.jhome.common.shiro.realm;

import com.daxu.common.Bus.RequestResult;
import com.daxu.common.Bus.ResponResult;
import com.domain.common.PermissionContext;
import com.jhome.modules.config.FeignConfig;
import com.shiro.common.client.RemoteBaseInterface;
import com.shiro.common.session.ShiroSession;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "jhome-account", configuration = FeignConfig.class) //这里的name对应调用服务的spring.applicatoin.name
public interface UserRemoteServiceInterface extends RemoteBaseInterface {

}
