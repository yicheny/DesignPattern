[TOC]

# 实现气象站
需求：
第三方应用`WeatherData`负责追踪目前的天气状况，我们调用`WeatherData`提供的数据，以此建立一个应用。
1. 有三种布告板，分别显示目前的状况、气象统计及简单的预报
1. 一旦数据更新则三个布告板同时进行更新
1. 公布一组API，好让其他开发者可以写出自己的布告板并插入此应用

首先我们看下第三方为我们提供的API：
```java
class WeatherData{
    getTemperature();//温度
    getHumidity();//湿度
    getPressure();//气压
    measurementsChanged();//每次数据更新时执行
}
```

我们预期创建的三个布告板内容：
1. 目前状况（温度、湿度、气压）
2. 气象统计（记录连续的数据）
3. 天气预报（预测未来数据）

## 第一次实现
```java
public class WeatherData{
    //...
    
    public void measurementsChanged(){
        float temp = getTemperature();
        float humidity = getHumidity();
        float pressure = getPressure();
        
        currentCoditionsDisplay.update(temp,humidity,pressure);
        statisticsDisplay.update(temp,humidity,pressure);
        forecastDisplay.update(temp,humidity,pressure);
    }
}
```
这种写法的问题是：
1. 我们是针对具体实现编程，而非针对接口编程
1. 布告板没有实现一个公共的接口
1. 对于每个新的布告板，我们都得修改代码
1. 我们尚未封装改变的部分
1. 我们无法在运行时动态地增加（或删除）布告板

具体分析下：
1. 这里根本没定义超类，所以是针对实现编程
1. 没有实现公共接口，所以想要实现一个新的布告板就没有统一规范了，和具体布告板会有很高的耦合。
1. 每次增加新的布告板，都需要在这里调整【这里其实也很有意思，容易想到的一点是，这里是否遵守单一职责原则？简单工厂与其有着相似之处，这里遵守了单一职责原则，但是违反了开放封闭原则，每次进行扩展都需要修改这里】
1， 改变的部分在哪里？通知的布告板是容易变化的部分，可能增加或修改，
1. 无法动态增加或删除布告板

## 第二次实现：自己实现的观察者模式
意图：观察者模式定义了对象之间的一对多依赖，当一个主题对象改变状态时，它的所有依赖者都会收到通知并自动更新。

我们首先实现相关接口：
```java
public interface Observer {
    public void update(float temp, float humidity, float pressure);
}

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}

public interface DisplayElement {
    public void display();
}
```

实现`weatherData`
```java
public class WeatherData implements Subject{
    private final ArrayList<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData(){
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer value : observers) {
            value.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged(){
        notifyObservers();
    }

    public void setMeasurements(float temperature,float humidity, float pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
```

实现布告板（这里仅作为示例实现一种）：
```java
public class CurrentConditionsDisplay implements Observer,DisplayElement{
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Subject weatherData){
        //如果想要取消注册，那么可以留有weatherData的引用
        weatherData.registerObserver(this);
    }

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    @Override
    public void display() {
        System.out.println("这里是实时信息：" + "\n温度：" + temperature + "\n湿度：" + humidity);
    }
}
```

客户端调用：
```java
public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(25,10,40.4f);
    }
}
```

这是观察者模式的一个应用，我们考虑现在`update`接收的数据方式，这里是将全部数据通过参数传递，问题在于：有些时候某些子类中可能并不需要用到所有数据，这个是浪费的，不过好处是更加解耦。

我们也可以使用另一种方案就是直接传入`Subject`类，根据我们需要自己取用数据，这种方式更灵活，不会进行无用的数据通信，缺陷是`Subject`和`Observer`类的联系会更加紧密。

其实这个问题在策略模式里已经提到，关于`Context`和`Strategy`的数据通信，这两种方式都有其可取之处及其劣势，需要根据情况进行选择。

另一个我觉得明显的问题是，`WeatherData`不纯粹，除了管理`Observer`之外，它还承担着业务上的职责，违反了单一职责原则。我认为将观察者模式交由一个专门的类来负责实现更合适。

## 第三种实现：Java内置的观察者模式
从类图上可以看出，主要区别在于主题不是一个接口，而是一个类。

这是一个很重要的区别，类意味着可以实现公共部分，`Observable`类实现了增加、删除、通知操作，子类直接继承即可使用，不需要再自己实现。

//这里先不贴代码了...

这里使用内置的观察者模式提示的顺序和之前我们自己实现的不同（我们是按添加的顺序进行提示，这里是后添加的先提示）。这是选择上的不同，说不上是哪一个的错误，但是这让我们明白一件事：不可依赖观察者的通知顺序。

### `Java.util.Observable`黑暗面
#### 难以同时复用【超类实现公共方法】
`Observable`是一个超类，而不是一个接口，而且也没有提供一个接口，这就使得`Observable`的复用和使用受到了很多限制。

`Observable`是一个超类，我们想要复用它的方法需要通过继承，如果我们需要另一个超类的方法，Java没有提供多重继承的能力，这限制了`Observable`类的使用潜力。【复用正是设计模式的目标之一】

然后，因为没有`Observable`接口，所以我们无法建立自己的实现和Java内置的`Observer API`搭配使用。

#### 只能通过继承复用，难以直接组合【关键方法被保护】
比如`setChanged`方法，它的修饰符是`protected`，也就是说，除非继承`Observable`，否则我们无法创建`Observable`实例并组合到自己的对象中的【也就是说，只能通过继承的方式被复用】——这里违反了我们之前总结的设计原则：多用组合，少用继承

# 知识
## 设计原则：为了交互对象之间的松耦合设计而努力
实现松耦合的关键在于不依赖实现，依赖抽象。

松耦合可以让系统更具有弹性，消除修改代码的依赖影响，而且更容易复用类

以观察者模式而言，`Subject`依赖的是`Observer`的接口，只要实现了这个接口，都可以进行注册、移除、通知操作，所以我们不需要知道具体类是什么，做了什么或其他任何细节——只要满足`Observer`接口即可。

然后，如果有新的`Observer`类出现，我们不需要改动`Subject`代码。如果我们想让一个类成为观察者，我们只需要让它实现`Observer`接口即可。

而且在使用上也更加灵活，我们可以在运行时替换、删除、新增观察者，只要是满足`Observer`接口的类都是可以的，主题不会受到影响。