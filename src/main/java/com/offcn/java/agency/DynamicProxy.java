package com.offcn.java.agency;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
动态代理
利用JDK API，动态地在内存中构建代理对象，从而实现对目标对象的代理功能
又被称为jdk代理或接口代理

静态代理和动态代理的主要区别在
静态代理在编译时就已经实现编译完成后代理类是一个实际的class文件
动态代理是在运行时动态生成的，即编译完成之后没有实际的class文件
而是在运行时动态生成类字节码，并加载到JVM中

动态代理不需要实现接口，但是要求对象必须实现接口，否则无法动态代理
 */
public class DynamicProxy {
    public static void main(String[] args) {
        IUserDao2 target3 = new UserDao2();
        // 输出目标对象的信息
        System.out.println(target3.getClass());
        IUserDao2 proxy = (IUserDao2)new ProxyFactory(target3).getProxyInstance();
        System.out.println(proxy.getClass());
        proxy.save();
    }
}

interface IUserDao2{
    public void save();
}


class UserDao2 implements IUserDao2{

    public void save() {
        System.out.println("保存数据");
    }
}

// 动态代理类
class ProxyFactory{
    private Object target1; // 维护一个目标对象
    public ProxyFactory(Object target1){
        this.target1 = target1;
    }

    // 为目标对象生成代理对象
    public  Object getProxyInstance(){
        return Proxy.newProxyInstance(target1.getClass().getClassLoader(), target1.getClass().getInterfaces(),
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("开启事务");
                        Object returnValue = method.invoke(target1, args);
                        System.out.println("提交事务");
                        return null ;
                    }
                });
    }
}
