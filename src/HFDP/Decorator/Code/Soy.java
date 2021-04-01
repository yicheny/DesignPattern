package HFDP.Decorator.Code;

public class Soy extends CondimentDecorator{
    Beverage beverage;

    public Soy(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 3 + beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ",豆浆";
    }
}
