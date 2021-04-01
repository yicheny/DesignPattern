package HFDP.Decorator.Code;

public class Decaf extends Beverage{
    public Decaf(){
        description = "脱因咖啡";
    }

    @Override
    public double cost() {
        return 4;
    }
}
