package HFDP.FactoryMethod;

abstract public class Pizza {
    protected String name;
    protected String material1;
    protected String material2;
    protected String material3;

    public void step1(){
        System.out.println("Pizza第1步制作中……");
    }

    public void step2(){
        System.out.println("Pizza第2步制作中……");
    }

    public void step3(){
        System.out.println("Pizza第3步制作中……");
    }

    public void step4(){
        System.out.println("Pizza第4步制作中……");
    }
}
