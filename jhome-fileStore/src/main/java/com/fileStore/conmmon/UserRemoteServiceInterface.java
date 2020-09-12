package com.fileStore.conmmon;

import com.shiro.common.session.RemoteBaseInterface;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "LUX-ACCOUNT", fallback = ServiceBFeignClientFallback.class)
public interface UserRemoteServiceInterface extends RemoteBaseInterface
{

}
