package HFDP.Decorator.Code;

public class Espresso extends Beverage{
    public Espresso(){
        description = "浓咖啡";
    }

    @Override
    public double cost() {
        return 3;
    }
}
