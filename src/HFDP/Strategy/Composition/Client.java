package HFDP.Strategy.Composition;

public class Client {
    public static void main(String[] args) {
        Duck duck = new RedDuck();
        duck.performFly();
    }
}

