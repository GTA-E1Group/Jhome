package com.jhome.autoconfiguration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(
        prefix = "jhome.sysproperties"
)
public abstract class SysProperties {

}
