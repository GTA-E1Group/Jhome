package com.shiro.common.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class IObjectMapper extends ObjectMapper
{
    public IObjectMapper() {
        super();
        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(MapperFeature.USE_ANNOTATIONS, false);
        this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        SimpleModule simpleModule = new SimpleModule();
        //将json数据中Long类型 序列化为 String 类型  解决前端js精度问题
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        registerModule(simpleModule);
    }
}
