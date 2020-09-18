package com.service.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author : Daxv
 * @date : 15:01 2020/5/17 0017
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Import(SpringConfiguration.class)
public @interface EnableSpring {
}
