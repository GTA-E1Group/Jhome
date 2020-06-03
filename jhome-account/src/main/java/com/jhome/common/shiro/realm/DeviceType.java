package com.jhome.common.shiro.realm;

/**
 * 设备类型
 * @author : Daxv
 * @date : 11:03 2020/5/12 0012
 */
public enum DeviceType
{
    PC("Pc"), APP("App"),CAS("Cas"), THIRDPATH("ThirdPath");
    private String type;
    DeviceType(String type) {
        this.type = type;
    }
    @Override
    public String toString() {
        return this.type;
    }
}
