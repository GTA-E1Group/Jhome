package com.shiro.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
    /**
     * 当前用户在request中的名字
     *
     * @return
     */
    String value() default Constant.CURRENT_USER;

}
