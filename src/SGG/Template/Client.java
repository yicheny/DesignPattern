package SGG.Template;

public class Client {
    public static void main(String[] args) {
        System.out.println("===开始制作花生豆浆===");
        PeanutSoyaMilk peanutSoyaMilk = new PeanutSoyaMilk();
        peanutSoyaMilk.make();

        System.out.println("===开始制作红豆豆浆===");
        RedBeanSoyaMilk redBeanSoyaMilk = new RedBeanSoyaMilk();
        redBeanSoyaMilk.make();

        System.out.println("===开始制作纯豆浆");
        PureSoyaMike pureSoyaMike = new PureSoyaMike();
        pureSoyaMike.make();
    }
}
