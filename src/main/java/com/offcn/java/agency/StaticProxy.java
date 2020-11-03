package com.offcn.java.agency;

/**
 * java实现静态代理，静态代理需要代理对象和目标对象实现一样的接口
 * 优点：可以在不修改目标对象的前提下扩展目标对象
 * 缺点：1.冗余，由于代理对象要实现与目标对象一致的接口，会产生过多的代理类
 * 2.不易维护，一旦接口增加方法，目标对象与代理对象都要进行修改
 */
public class StaticProxy {
    public static void main(String[] args) {
        // 目标对象
        IuserDao target  = new UserDao();
        UserDaoProxy userDaoProxy = new UserDaoProxy(target);
        userDaoProxy.save();
    }
}

interface IuserDao{
    public void save();
}

class UserDao implements IuserDao{

    public void save() {
        System.out.println("保存数据");
    }
}

// 代理类
class UserDaoProxy implements IuserDao{

    private IuserDao target;
    public UserDaoProxy(IuserDao target){
        this.target = target;
    }

    public void save() {
        System.out.println("开启事务");
        target.save();
        System.out.println("关闭事务");
    }
}


