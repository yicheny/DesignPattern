package SGG.Facade;

//屏幕
public class Screen {
    private static final Screen instance = new Screen();

    public static Screen getInstance(){
        return instance;
    }

    public void up(){
        System.out.println(" Screen 上升 ");
    }

    public void down(){
        System.out.println(" Screen 下降 ");
    }
}
