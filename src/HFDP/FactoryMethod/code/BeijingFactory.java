package HFDP.FactoryMethod.code;

public class BeijingFactory extends Factory{
    @Override
    public Pizza createPizza() {
        return new BeijingPizza();
    }
}
