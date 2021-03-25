package HFDP.Base.Polymorphic;

/*
* 多态的三个前提：
* 1. 要有继承关系
* 2. 子类要重写父类方法
* 3. 父类引用指向子类
* */

public class Client {
    public static void main(String[] args) {
        Animal animal = new Cat();
        animal.eat();
        animal.run();
//        animal.catchMouse();//多态不能使用子类独有的方法和属性
    }
}

class Animal{
    public void eat() {
        System.out.println("动物吃饭");
    }

    public void run(){
        System.out.println("动物在奔跑");
    }
}

class Cat extends Animal{
    public void eat() {
        System.out.println("猫吃饭");
    }
    public static void sleep() {
        System.out.println("猫在睡觉");
    }
    public void catchMouse() {
        System.out.println("猫在抓老鼠");
    }
}
