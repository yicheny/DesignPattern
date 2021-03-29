package HFDP.Strategy.Composition;

abstract class Duck {
    protected FlyBehavior flyBehavior;//子类可以在构造器设置初始的默认行为

    public void performFly() {
        flyBehavior.fly();
    }
}
