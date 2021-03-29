package HFDP.Strategy.Aggregation;

public class Client {
    public static void main(String[] args) {
        Duck duck = new RedDuck();
        duck.performFly();
        duck.setFlyBehavior(new FlyNoWay());
        duck.performFly();
    }
}

