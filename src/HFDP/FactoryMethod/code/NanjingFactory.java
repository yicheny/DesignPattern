package HFDP.FactoryMethod.code;

public class NanjingFactory extends Factory{
    @Override
    public Pizza createPizza() {
        return new NanjingPizza();
    }
}
