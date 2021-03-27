package HFDP.Strategy;

public class Client {
    public static void main(String[] args) {
        Duck duck = new RedDuck();
        duck.performFly();
    }
}

interface FlyBehavior{
    public void fly();
}

class FlyWithWings implements FlyBehavior{
    @Override
    public void fly() {
        System.out.println("我会飞！");
    }
}

abstract class Duck {
    protected FlyBehavior flyBehavior;//子类可以在构造器设置初始的默认行为

    public void performFly(){
        flyBehavior.fly();
    }
}

class RedDuck extends Duck{
    public RedDuck(){
        this.flyBehavior = new FlyWithWings();
    }
}
