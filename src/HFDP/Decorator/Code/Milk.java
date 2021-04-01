package HFDP.Decorator.Code;

public class Milk extends CondimentDecorator{
    Beverage beverage;

    public Milk(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 1 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",牛奶";
    }
}
