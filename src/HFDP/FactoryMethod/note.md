[TOC]

# 关于`new`
首先看代码：
```
Duck duck = new RedHeadDuck();
```
这段代码里，我们使用`Duck`接口使得声明更具有弹性，但是通过`new`进行具体类的实例化，代码绑着具体类意味着对它的依赖，这段代码是脆弱的，缺乏弹性。

使用`new`进行类的创建，意味着我们在针对实现进行编程，这会有什么问题？

# 生成单一`Pizza`
假设我们有一家比萨厂，可以生产披萨，代码如下：
```java
public class PizzaStore{
    public Pizza orderPizza(){
        Pizza pizza = new Pizza();
        
        //制作pizza的流程
        pizza.step1();  
        pizza.step2();
        pizza.step3();  
        
        return pizza;
    }
}
```

# 生成更多类型`Pizza`
## 第一次实现
现在我们的pizza店做大了，在北京、南京、东京等地都有分店，不同地区的人口味不同，所以允许分店的口味和本家不同【但是我们要求保持一致的制作步骤】。

想要满足需求，比较直接的思路是：
```java
public class PizzaStore {
    public Pizza orderPizza(String type) {
        Pizza pizza;

        if (type.equals("Nanjing")) {
            pizza = new NanjingPizza();
        } else if (type.equals("Beijing")) {
            pizza = new BeijingPizza();
        } else if (type.equals("Dongjing")) {
            pizza = new DongjingPizza();
        }

        pizza.step1();
        pizza.step2();
        pizza.step3();

        return pizza;
    }
}
```
这种实现方式有什么问题？<br/>
判断类型这部分代码是动态的，因为每当要添加一种新类型时都需要修改这部分代码。<br/>
`Pizza`的制作流程这部分代码是固定的，无论哪种类型的`Pizza`都遵守同样的制作流程。

现在，我们知道代码哪部分是会变化的，哪部分是不变的。那么，这个时候就让我们使用封装吧。

关于封装：
类和方法都可以起到封装的作用，但类更强大，类拥有封装、继承、多态三大核心能力。

## 第二次实现-`SimpleFactory`
首先我们将变化的部分抽取成一个类，这是一个简单工厂类
> 简单工厂并不是23种设计模式之一，但它使用场景很多，也很有用，是比较特殊的一种模式

### 定义制作`Pizza`的简单工厂类
```java
public class SimplePizzaFactory{
    public Pizza createPizza(String type){
        Pizza pizza;

        if (type.equals("Nanjing")) {
            pizza = new NanjingPizza();
        } else if (type.equals("Beijing")) {
            pizza = new BeijingPizza();
        } else if (type.equals("Dongjing")) {
            pizza = new DongjingPizza();
        }
        
        return pizza;
    }
}
```

### 聚合至`PizzaStore`
然后我们将`SimplePizzaFactory`聚合到`PizzaStore`中
```java
public class PizzaStore{
    SimplePizzaFactory factory;
    
    public PizzaStore(SimplePizzaFactory factory){
        this.factory = factory;
    }
    
    public Pizza orderPizza(String type){
        Pizza pizza = factory.createPizza(type);
        pizza.step1();  
        pizza.step2();
        pizza.step3();  
        return pizza;
    }
}
```

### 分析
1. 这么做了之后好处在什么地方？（很容易认为这种做法只是将问题搬移到另一个类，问题依旧存在）<br/>
我们使用了封装，这样修改代码就不会影响到其他地方，可以减少意外影响，使系统更具有弹性。<br/>
然后就是方便复用，职责越少复用越简单，一个承担着多种职责的方法是很难被维护和复用的。

2. 将简单工厂定义为类和方法有什么不同？
方法同样具有封装性，但类更强大，使用方法不具有继承和多态的能力，继承方便复用，多态是方便扩展。
   
3. 简单工厂存在什么问题？
- 违反了单一职责原则
- 违反了开放封闭原则

## 第三次实现--`Factory Method`
我们需要一个公共抽象类`PizzaStore`，它有两个作用：
1. 定义公共的制作流程`PizzaOperation`，
1. 定义抽象方法`createPizza`，由具体子类去实现`createPizza`
```java
import HFDP.FactoryMethod.code.NanjingPizza;

public abstract class PizzaStore {
    public abstract Pizza createPizza();

    public void orderPizza() {
        Pizza pizza = createPizza();
        pizza.step1();
        pizza.step2();
        pizza.step3();
    }
}

public class NanjingPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza() {
        //子类具体实现
        return new NanjingPizza();
    }
}
```
工厂方法模式事实上也是一个很容易想到的方案，它和简单工厂思路侧重点不同，简单工厂的目的是抽离变化的部分，工厂方法的目的是抽离公共的部分，由每个子类负责不同的部分，这种方式对于复用很友好。

不过工厂模式依旧存在一些隐患，它的复用方式是继承，继承的耦合度是高于组合与聚合的。——并不是说不能使用继承，而是要慎重使用继承，合理使用继承实现更加简洁，而且并不会造成什么影响。

使用继承有两条基本原则，遵守的话就没问题：
1. 不能继承具体类，继承抽象【接口或抽象类】
2. 不能覆写基类中已实现的方法

### 工厂方法模式定义
定义：工厂方法模式定义了一个创建对象的接口【抽象类也算接口】，但由子类决定要实例化的类是哪一个，工厂方法让类把实例化推迟到子类。

#### 需要注意的点
”由子类**决定**要实例化的类是哪一个“，并不是允许子类本身在运行时做决定，而是说在编写创建者类时，不需要知道实际创建的产品时哪一个，因为实例创建由子类控制，所以选择使用哪个子类，也决定了实际创建的产品是什么。