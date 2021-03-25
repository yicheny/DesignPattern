[TOC]

# 生成单一`Pizza`
现在我们有一家比萨厂，可以生产`Pizza1`这种类型的披萨，代码如下：
```java
public class PizzaStore{
    public Pizza orderPizza(){
        Pizza pizza = new Pizza();
        
        //制作pizza的流程
        pizza.step1();  
        pizza.step2();
        pizza.step3();  
        pizza.step4();
        
        return pizza;
    }
}
```

# 生成更多类型`Pizza`
## 第一次实现
我们想要制作更多类型的`Pizza`，先这么做：
```java
public class PizzaStore{
    public Pizza orderPizza(String type){
        Pizza pizza;
        
        if (type.equals("t1")){
            pizza = new PizzaT1();
        } else if (type.equals("t2")) {
            pizza = new PizzaT2();
        } else if (type.equals("t3")) {
            pizza = new PizzaT3();
        }
        
        pizza.step1();  
        pizza.step2();
        pizza.step3();  
        pizza.step4();
        
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
> 简单工厂并不是23种设计模式之一，但它用的场景很多，也很有用，是比较特殊的一种模式
```java
public class SimplePizzaFactory{
    public Pizza createPizza(String type){
        Pizza pizza;

        if (type.equals("t1")){
            pizza = new PizzaT1();
        } else if (type.equals("t2")) {
            pizza = new PizzaT2();
        } else if (type.equals("t3")) {
            pizza = new PizzaT3();
        }
        
        return pizza;
    }
}
```

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
        pizza.step4();
        
        return pizza;
    }
}
```

### 分析
1. 这么做了之后好处在什么地方？（很容易认为这种做法只是将问题搬移到另一个类，问题依旧存在）<br/>
我们使用了封装，这样修改代码就不会影响到其他地方，可以减少意外影响，使系统更具有弹性。<br/>
虽然书上说这里可以被其他地方复用，但我看法不同，因为这里暂时上它并没有被复用，在没有复用需求的时候提前进行设计我认为这不是一个好的做法。<br/>
我认可独立出这部分变化的代码，理由是减少修改代码时的影响。

2. 将简单工厂定义为类和方法有什么不同？
方法同样具有封装性，但类更强大，使用方法意味着不能通过继承来改变创建方法的行为，不能继承自然也不能使用多态。

# 披萨加盟店
现在情况有所变化，随着生意扩大，有很多人想加盟，现在在南京和北京各有一家加盟店，这两家的披萨配料和口味与主家不同，但是制作流程是相同的。
## 第一次实现--抽象工厂
比较容易想到的做法是我们多开几家披萨工厂，然后通过聚合的方式使用，比如：
```java
public class Client{
    public static void main(String[] args) {
        NanJingPizzaFactory njFactory = new NanJingPizzaFactory();
        //这里，PizzaStore引用的其实是一个接口IFactory，这样可以接收不同的工厂类
        PizzaStore njStore = new PizzaStore(njFactory);
        njFactory.orderPizza("t1");
        
        BeiJingPizzaFactory bjFactory = new BeiJingPizzaFactory();
        PizzaStore bjStore = new PizzaStore(bjFactory);
        bjFactory.orderPizza("t1");
    }
}
```
这个其实是抽象工厂，我们通过接口进行约定，通过聚合进行使用，达到了解耦和依赖倒置的目的。

## 另一种实现--简单工厂
事实上，简单工厂的思路是每个加盟店使用自己的`PizzaStore`，如下：
```java
public class Client{
    public static void main(String[] args) {
        NanJingPizzaFactory njFactory = new NanJingPizzaFactory();
        //这里商店类引用的是NanJingPizzaFactory这个具体的类
        NanJingPizzaStore njStore = new NanJingPizzaStore(njFactory); 
        njFactory.orderPizza("t1");
    }
}
```
这个写法其实存在一些问题，如果我们希望每个加盟店的制作方法都保持不同，那么这种做法自然合理。不过事实上我们想要的是每个加盟店可以有不同口味的`Pizza`，但制作流程必须相同。

想要达到这个目的，制作流程就需要由一个类统一处理，下面说一下工厂方法模式

## 另一种实现--工厂方法模式
大致思路就是公共抽象类`PizzaStore`负责公共的制作流程，以及定义一个统一的抽象方法`createPizza`，由具体子类去实现`createPizza`

```java
public abstract class PizzaStore {
    Pizza orderPizza(String type) {
        //这个是所有子类公用的方法
    }

    //这个交由子类实现
    protected abstract Pizza createPizza(String type);
}

public class NanJingPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        //子类具体实现
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

## 另一种实现--过程式写法
```java
public class DependentPizzaStore{
    public Pizza createPizza(String style,String type){
        Pizza pizza = null;
        if (style.equals("Nanjing")){ 
            if(type.equals("t1")){
                pizza = new NanjingPizzaT1();
            }else if(type.equals("t2")){
                pizza = new NanjingPizzaT2();
            }else if(type.equals("t3")){
                pizza = new NanjingPizzaT3();
            }else if(type.equals("t4")){
                pizza = new NanjingPizzaT4();
            }
        }else if (style.equals("Beijing")){
            if(type.equals("t1")){
                pizza = new BeijingPizzaT1();
            }else if(type.equals("t2")){
                pizza = new BeijingPizzaT2();
            }else if(type.equals("t3")){
                pizza = new BeijingPizzaT3();
            }else if(type.equals("t4")){
                pizza = new BeijingPizzaT4();
            }
        }else{
            System.out.println("Error:没有这种类型的披萨！");
            return null;
        }
        
        pizza.step1();
        pizza.step2();
        pizza.step3();
        pizza.step4();
        
        return pizza;
    }
}
```
这个写法里，`createPizza`里依赖了8个具体类（当我们直接实例化一个对象时，就是在依赖它的具体类）。

这种做法有什么问题？
1. 违反单一职责原则
2. 违反开放封闭
3. //没有使用继承，就不说里氏替换原则了 
4. 违反接口隔离原则
5. 违反依赖倒转原则

我们假设一个场景：现在我们需要添加一种新的Pizza类型`XijingPizzaT1`，那么需要改动的类有哪些？
1. 增加`XijingPizzaT1`
2. 修改`DependentPizzaStore`的`createPizza`方法【需要增加两处判断，因为这里承担着两种类型变化`style`、`type`】

如果是使用工厂方法模式，会有什么不同，我们需要改变什么？
1. 增加`XijingPizzaT1`
2. 增加`XijingPizzaFactory`及`createPizza`方法【注意，这里只承担着1种类型变化`type`】

事实上，当我们使用工厂方法模式，选用哪个加盟商这个责任被摘出去了，由客户端负责，判断是始终存在的。

将责任分离是非常非常重要的，OO的五大原则之一就是”单一职责“原则，究其原因，如果一个类或方法存在承担多个职责，就会有多个被修改的理由，那么修改的可能性就会变得很高，同样，每次修改都会影响到其他职责，因此单一职责非常重要。

什么是"职责"？`Robert-c-Martin`给出的定义是：被修改的理由。一个类或方法应该有且只有一个被修改的理由。

事实上可以发现依赖倒转原则和单一职责原则有所关联，不依赖抽象依赖实现就会违反单一职责原则【抽象是统一的，实现是多变的】，违反单一职责原则几乎必然导致开放封闭原则的破坏

现在回顾下五大基本原则之间的关系：<br/>
不遵守里氏替换原则，就无法遵守依赖倒转原则。<br/>
违反依赖倒转、接口隔离，就会进而违反单一职责。<br/>
违反单一职责，那么开放封闭也无法被保证。