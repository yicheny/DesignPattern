package HFDP.FactoryMethod.code;

public class BeijingFactory extends Factory{
    @Override
    protected Pizza createPizza() {
        return new BeijingPizza();
    }
}
