package SGG.Proxy.DynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyFactory {
    private final Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    /*
     1. ClassLoader loader 指定当前目标对象使用的类加载器，获取加载器的方法是固定的
     2. Class<?>[] interfaces 目标对象实现的接口类型，使用泛型的方式确认类型
     3. InvocationHandler h 事件处理，执行目标对象的方法时，会触发事件处理器的方法
     * */
    public Object getProxyInstance() {
        ClassLoader loader = target.getClass().getClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        InvocationHandler h = (proxy, method, args) -> {
            System.out.println("JDK代理开始~~");
            return method.invoke(target,args);
        };
        return Proxy.newProxyInstance(loader,interfaces, h);
    }
}
