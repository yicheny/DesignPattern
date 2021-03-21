package SGG.Facade;

//立体声
public class Stereo {
    private static final Stereo instance = new Stereo();

    public static Stereo getInstance(){
        return instance;
    }

    public void on(){
        System.out.println(" Stereo on ");
    }

    public void off(){
        System.out.println(" Stereo off ");
    }

    public void up(){
        System.out.println(" Stereo 声音上升 ");
    }

    public void down(){
        System.out.println(" Stereo 声音下降 ");
    }
}
