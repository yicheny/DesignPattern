package HFDP.Decorator.Code;

public class DarkRoast extends Beverage{
    public DarkRoast(){
        description = "焦炒咖啡";
    }

    @Override
    public double cost() {
        return 2;
    }
}
