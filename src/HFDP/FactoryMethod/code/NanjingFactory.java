package HFDP.FactoryMethod.code;

public class NanjingFactory extends Factory{
    @Override
    protected Pizza createPizza() {
        return new NanjingPizza();
    }
}
