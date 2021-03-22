package SGG.Flyweight;

public class Client {
    public static void main(String[] args) {
        WebSiteFactory factory = new WebSiteFactory();
        WebSite webSite1 = factory.getWebSiteCategory("新闻");
        webSite1.use(new User("小明"));
        WebSite webSite2 = factory.getWebSiteCategory("新闻");
        webSite2.use(new User("小王"));
        WebSite webSite3 = factory.getWebSiteCategory("博客");
        webSite3.use(new User("小李"));
        WebSite webSite4 = factory.getWebSiteCategory("博客");
        webSite4.use(new User("小刚"));

        System.out.println("当前网站数量为：" + factory.getWebSiteCount());
    }
}
