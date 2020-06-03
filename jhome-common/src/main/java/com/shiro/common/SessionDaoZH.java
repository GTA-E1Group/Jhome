package com.shiro.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;


import java.util.Collection;

public class SessionDaoZH {
    /**
     * 【排除基本类型 序列化】
     * 集中序列化对象避免微服务架构缺失：
     * 本地对象造成系统雪崩
     * **/
    public static Session SerializedBeanToString(Session session)
    {
        Collection<Object> objectLists=  session.getAttributeKeys();
        objectLists.forEach(c->{
            String key=(String)c;
            /*排除是shiro写入的*/
            if(!key.contains("org")&&!key.contains("apache")&&!key.contains("shiro")&&!key.contains("subject")&&!key.contains("support")&&!key.contains("DefaultSubjectContext"))
            {
                /*排除是java基本类型*/
                if(!session.getAttribute(c).getClass().isPrimitive())
                {
                    session.setAttribute(key, JSON.toJSON(session.getAttribute(c)));
                }

            }
        });
        return session;
    }

    /**
     * 【反序列化系统内置对象】
     * @param session
     * @return
     */
    public static Session SerializedStringToAttributeBean(Session session)
    {
        Collection<Object> objectLists=  session.getAttributeKeys();
        objectLists.forEach(c->{
            String key=(String)c;
            if(key.contains("DefaultSubjectContext_PRINCIPALS_SESSION_KEY"))
            {
                String  json= ((JSONObject)session.getAttribute(key)).toJSONString();
                SimplePrincipalCollection simplePrincipalCollection=JSON.parseObject(json,SimplePrincipalCollection.class);
                session.setAttribute(key,simplePrincipalCollection);
            }
        });
        return session;
    }
//    /**
//     * 【不是基本类型全部序列化 序列化】
//     * @param session
//     * @return
//     */
//    public static  Session SerializedBeanToStringByRemoteService(Session session)
//    {
//        Collection<Object> objectLists=  session.getAttributeKeys();
//        objectLists.forEach(c->{
//            String key=(String)c;
//            /*是shiro写入的*/
//            if(key.contains("org")||key.contains("apache")||key.contains("shiro")||key.contains("subject")||key.contains("support")||key.contains("DefaultSubjectContext"))
//            {
//                /*排除是java基本类型*/
//                if(!session.getAttribute(c).getClass().isPrimitive())
//                {
//                    session.setAttribute(key, JSON.toJSON(session.getAttribute(c)));
//                }
//            }
//        });
//        return session;
//    }

}
