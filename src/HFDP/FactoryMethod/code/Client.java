package HFDP.FactoryMethod.code;

public class Client {
    public static void main(String[] args) {
        Factory bjFactory = new BeijingFactory();
        bjFactory.pizzaOperation();

        Factory njFactory = new NanjingFactory();
        njFactory.pizzaOperation();
    }
}