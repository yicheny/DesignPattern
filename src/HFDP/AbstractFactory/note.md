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

我们假设一个场景：现在我们需要添加一种新的`Pizza`类型`XijingPizzaT1`，那么需要改动的类有哪些？
1. 增加`XijingPizzaT1`
2. 修改`DependentPizzaStore`的`createPizza`方法【需要增加两处判断，因为这里承担着两种类型变化`style`、`type`】

如果是使用工厂方法模式，会有什么不同，我们需要改变什么？
1. 增加`XijingPizzaT1`
2. 增加`XijingPizzaFactory`及`createPizza`方法【注意，这里只承担着1种类型变化`type`】

事实上，当我们使用工厂方法模式，选用哪个加盟商这个责任被摘出去了，由客户端负责，判断是始终存在的。

将责任分离是非常非常重要的，`OO`的五大原则之一就是”单一职责“原则，究其原因，如果一个类或方法存在承担多个职责，就会有多个被修改的理由，那么修改的可能性就会变得很高，同样，每次修改都会影响到其他职责，因此单一职责非常重要。

什么是"职责"？`Robert-c-Martin`给出的定义是：被修改的理由。一个类或方法应该有且只有一个被修改的理由。

事实上可以发现依赖倒转原则和单一职责原则有所关联，不依赖抽象依赖实现就会违反单一职责原则【抽象是统一的，实现是多变的】，违反单一职责原则几乎必然导致开放封闭原则的破坏

现在回顾下五大基本原则之间的关系：<br/>
不遵守里氏替换原则，就无法遵守依赖倒转原则。<br/>
违反依赖倒转、接口隔离，就会进而违反单一职责。<br/>
违反单一职责，那么开放封闭也无法被保证。