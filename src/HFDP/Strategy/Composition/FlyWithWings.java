package HFDP.Strategy.Composition;

class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("我会飞！");
    }
}
