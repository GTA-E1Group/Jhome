package com.sysAnnotation.common.jhomeScan;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import({sysScannerRegistrar.class})
public @interface sysScan {
    String[] value();
}
