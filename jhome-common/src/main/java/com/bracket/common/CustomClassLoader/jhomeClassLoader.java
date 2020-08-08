package com.bracket.common.CustomClassLoader;

import lombok.SneakyThrows;

import java.io.*;

/**
 * 自定义类加载器
 * jhomeClassLoader loadClass=new jhomeClassLoader("daxu","d:/temp/");
 * Class<?> c=loadclass.loadClass("demo");
 * c.newInstance();
 * </>
 */
public class jhomeClassLoader extends ClassLoader {
    private String className;//加载类的名称
    private String path;//加载类的路径

    /**
     * 使用父类加载器
     * @param className
     * @param path
     */
    public jhomeClassLoader(String className,String path)
    {
        super();//父类加载器
        this.className=className;
        this.path=path;
    }

    /**
     * 显示指定父类加载器
     * @param classLoader
     * @param className
     * @param path
     */
    public jhomeClassLoader(ClassLoader classLoader, String className,String path)
    {
        super(classLoader);//父类加载器
        this.className=className;
        this.path=path;
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data=readClassFileToArray(name);
        return this.defineClass(name,data,0,data.length);
    }

    /**
     * 获取class 中的字节数据
     * @param name
     * @return
     */
    private byte[] readClassFileToArray(String name) throws IOException {
        InputStream is=null;
        byte[] ruestData=null;
        ByteArrayOutputStream os=new ByteArrayOutputStream();
        try {
            String classPath=this.path+name.replaceAll("\\.","/");
            InputStream file=new FileInputStream(classPath);
            int temp=0;
            while ((temp=is.read())!=-1)
            {
                os.write(temp);
            }
            ruestData=os.toByteArray();

        }catch (Exception ex)
        {
        }
        finally {
            is.close();
            os.close();
        }
        return  ruestData;

    }

    @Override
    public String toString() {
        return super.toString();
    }
}
