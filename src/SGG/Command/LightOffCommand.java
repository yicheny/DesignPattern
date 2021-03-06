package SGG.Command;

public class LightOffCommand implements Command{
    LightReceiver light;

    public LightOffCommand(LightReceiver light){
        super();
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        light.on();
    }
}
