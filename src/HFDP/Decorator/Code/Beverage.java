package HFDP.Decorator.Code;

public abstract class Beverage {
    protected String description = "这个描述由子类实现";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
