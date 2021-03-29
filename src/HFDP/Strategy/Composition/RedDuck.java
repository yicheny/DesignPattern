package HFDP.Strategy.Composition;

class RedDuck extends Duck {
    public RedDuck() {
        this.flyBehavior = new FlyWithWings();
    }
}
