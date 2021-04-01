package HFDP.Decorator.Code;

public class HouseBlend extends Beverage{
    public HouseBlend(){
        description = "首选咖啡";
    }

    @Override
    public double cost() {
        return 1;
    }
}
