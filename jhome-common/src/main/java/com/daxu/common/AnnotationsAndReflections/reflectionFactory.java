package com.daxu.common.AnnotationsAndReflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class reflectionFactory {
    public Object getMyClass(String className, Class AnnotationClass) {
        Object obj = null;
        try {
            //根据类全名得到该类
            Class clazz = Class.forName(className);
            //获得该类中无参的构造方法
            Constructor con1 = clazz.getConstructor();
            //创建该类的实例,该类中一定要有无参的构造函数，否则这个语句不能执行
            Field[] fields = clazz.getDeclaredFields();
            //遍历属性
            obj = con1.newInstance();
            //获得该类中所有的属性
            for (int i = 0; i < fields.length; i++) {
                //得到属性值得名字
                String fieldName = fields[i].getName();
                //截取属性值得第一字母并且改成大写
                String firstLetter = fieldName.substring(0, 1).toUpperCase();
                //截取其他的字母
                String otherLetter = fieldName.substring(1);
                //利用StringBulider拼串
                StringBuilder sb = new StringBuilder("set");
                sb.append(firstLetter);
                sb.append(otherLetter);
                //得到属性值得类型
                Class fieldClass = fields[i].getType();
                //得到指定的方法
                Method setMethod = clazz.getMethod(sb.toString(), fieldClass);
                //判断该类的构造方法上是否有注解
                boolean flag = con1.isAnnotationPresent(AnnotationClass);
                System.out.println(flag);
                if (flag) {
                    //得到构造方法上的注解
                    Annotation annotation = con1.getAnnotation(AnnotationClass);
                    //得到注解的类
                    Class aClass = annotation.getClass();
                    //获得注解的方法
                    Method aMethod = aClass.getMethod("value");
                    //得到方法中的值
                    String[] values = (String[]) aMethod.invoke(annotation);
                    //将从注解中读取的值赋值转化成加注解类中属性值得类型
                    Constructor con = fieldClass.getConstructor(String.class);
                    //将值赋值给set方法
                    setMethod.invoke(obj, con.newInstance(values[i]));

                }

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return obj;
    }
}
