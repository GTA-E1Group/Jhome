package com.daxu.common.ToolKit;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.HashMap;

public class SimpleMap  extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = -5809782578272943999L;

    public Object toBean(Class<?> clazz){
        try {
            Object obj = clazz.newInstance();


            BeanUtils.populate(obj,this);





            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
