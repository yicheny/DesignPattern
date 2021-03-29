package HFDP.Strategy.Aggregation;

class RedDuck extends Duck {
    public RedDuck() {
        this.flyBehavior = new FlyWithWings();
    }
}
