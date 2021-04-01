package HFDP.Decorator.Code;

public class Whip extends CondimentDecorator{
    Beverage beverage;

    public Whip(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 4 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",奶泡";
    }
}
