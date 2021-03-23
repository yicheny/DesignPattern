package SGG.Proxy.DynamicProxy;

public class Client {
    public static void main(String[] args) {
        ITeacherDao target = new TeacherDao();
        ITeacherDao proxyInstance = (ITeacherDao) new ProxyFactory(target).getProxyInstance();

        //内存中动态生成了代理对象 ProxyInstance=class com.sun.proxy.$Proxy0
        //代理对象会以$开头
        System.out.println("ProxyInstance=" + proxyInstance.getClass());
        proxyInstance.teach();
    }
}
