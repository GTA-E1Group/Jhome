package com.shiro.common.token;

import java.util.ArrayList;
import java.util.List;

/**
 * 设备类型
 *
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public enum DeviceType {
    PC("Pc"), APP("App"), CAS("Cas"), THIRDPATH("ThirdPath");
    private String type;

    DeviceType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return this.type;
    }

    /**
     * 枚举转换LIst
     * @return
     */
    public static List toList() {
        List list = new ArrayList();
        for (DeviceType deviceType : DeviceType.values()) {
            list.add(deviceType.type);
        }
        return list;
    }

    /**
     * 是否是单点登录类型
     * @param type
     * @return
     */
    public static boolean IsSinglepointLoginContainType(String type)
    {
        List list=toList();
        return list.contains(type);
    }
}
