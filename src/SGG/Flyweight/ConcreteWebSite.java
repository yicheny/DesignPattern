package SGG.Flyweight;

public class ConcreteWebSite extends WebSite{
    //type是共享数据，内部状态
    private String type = "";

    public ConcreteWebSite(String type){
        this.type = type;
    }


    @Override
    //User是非共享数据，是外部状态
    public void use(User user) {
        System.out.println("网站的发布形式是：" + type + "，网站的使用者是" + user.getName());
    }
}
