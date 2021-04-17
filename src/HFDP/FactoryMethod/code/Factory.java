package HFDP.FactoryMethod.code;

abstract public class Factory {
    abstract protected Pizza createPizza();

    public void pizzaOperation(){
        Pizza pizza = createPizza();
        pizza.step1();
        pizza.step2();
        pizza.step3();
    }
}
