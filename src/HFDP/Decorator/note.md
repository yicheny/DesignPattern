[TOC]

# 星巴兹订单系统
星巴兹是一家咖啡店，他们的订单系统一开始是这样的：
```java
//饮料抽象类——所有饮料类都继承于此
abstract class Beverage{
    protected String description;//由子类设置，用于描述饮料
    public getDesciption();
    public abstract cost();
}

class HouseBlend extends Beverage{//首选咖啡
    public cost();
}
class DarkRoast extends Beverage{//焦炒咖啡
    public cost();
}
class Decaf extends Beverage{//脱因咖啡
    public cost();
}
class Espresso extends Beverage{ //浓咖啡
    public cost();
}
```

咖啡店现在有了一些变化，我们现在可以加入各种调料：`Steamed Milk`【蒸奶】、`Soy`【豆浆】、`Mocha`【摩卡，就是巧克力】

计算价格的时候，我们需要根据加入的配料进行不同的收费。

## 第一次实现
思路就是为每种可能的饮料+配料实现一个类，结果就是会产生大量的类。

我们很容易就可以想象到的是，虽然不同饮料+配料得到的价格是不同的，但是价格的计算是有规律的，那么这部分规律是可以被复用的。

如果我们为每一种可能都实现一个类，首先是不能复用公共的部分【单体饮料和配料的价格】，然后就是它应对变化的能力不足，比如说我加上一种配料，就会多出很多不同的类。如果考虑数量和不同种配料的搭配，可能几乎是无限，这种方案从逻辑上考虑就不可能被实现。

从实际结果来说，这种做法的问题是：
1. 没有抽离变化的部分，弹性不足

## 第二次实现
这个方案的思路由超类负责实现调料的价格计算，子类饮料只负责的自己的价格，计算总价格时候，将超类计算出来的调料价格加上饮料价格得出总价格。

这个方案对超类`Beverage`改动较大，现在`Beverage`将承担计算调料的责任
```java
abstract class Beverage{
    protected String description;
    protected int milk;//由子类设置配料的数量，默认为0——书上是boolean类型，但很明显不如int类型灵活
    protected int soy;
    protected int mocha;
    protected int whip;
    
    public getDesciption();
    public cost();//现在这个方法不再是抽象方法了，它负责调料进行计算

    public setMilk();
    public setSoy();
    public setMocha();
    public setWhip();
}
```
现在就是`Beverage`将会维护负责维护调料的价格计算【调料的单价也是在这里维护的】

现在分析下这种做法：<br/>
现在这种相比于前一种是代码量大大减少，而且足够动态，我们可以很灵活的设置调料的类型和数量。

不过还有有一些明显的问题：
1. `Beverage`承担了不属于它的职责【使得配料和饮料的部分混合在一起了】
1. 职责过多过重，即使是计算配料，也不该由一个类负责所有配料的计算，这意味当某个配料调整时都需要调整这里
1. 通过继承而不是组合的方式进行代码复用，继承是一种强耦合关系，这种做法会带来两个问题，一个是修改超类，会影响到所有子类，会带来意外的影响；另一个是复用，在不能多类继承的编程语言中，使用继承会限制复用的范围。

## 第三次实现：装饰者模式

# 知识
## 设计原则：类应该对扩展开放，对修改关闭
面向对象中最重要的原则之一，也是最终原则，可以说其他原则都是为此服务的。

遵循开放-封闭原则通常会引入新的抽象层次，增加代码的复杂度。所以我们必须要认识到一点：不可能让设计的每个部分都遵守开放封闭原则，我们应该在最有可能改变的部分应用开放-封闭原则。

现在的关键是判断哪部分有可能被改变，这取决于个人的知识量和代码经验，这是个人认知的部分，很难通过语言来描述。