[TOC]

## 意图
定义了算法族并分别封装起来，让它们之间可以相互替换，让算法的变化独立于使用算法的客户。

## 适用性
1. 许多相关类仅仅是行为有异。策略提供了一种用多个行为中的一个行为来配置要给类的方法
2. 需要使用一个算法的不同变体。比如说可能定义一些反映不同空间/时间权衡的算法
3. 算法使用客户不应该知道的数据。可使用策略模式以避免暴露复杂的、与算法相关的数据结构
4. 一个类定义了多种行为，并且这些行为在这个类的操作中以多个条件语句的形式出现，价格相关的条件分支移入它们各自的`Strategy`类中以代替这些条件语句

## 结构
见同目录下的`UML`图

## 参与者
1. `Strategy`：定义所有支持的算法的公共接口。`Context`使用这个接口来调用某`ConcreteStrategy`定义的算法
2. `ConcreteStrategy`：以`Strategy`接口实现某具体算法
3. `Context`
    - 用一个`ConcreteStrategy`来配置
    - 维护一个对`Stratyge`的引用
    - 可定义一个接口来让`Strategy`来访问它的数据

## 协作
- `Strategy`与`Context`相互作用以实现选定的算法。当算法被调用时，`Context`可以将该算法所需要的数据都传递给该`Strategy`。或者，`Context`可以将自身作为一个参数传递给`Strategy`进行操作，这样可以让`Stratyge`可以在需要时回调使用。
- `Context`将它的客户请求转发给它的`Stratyge`,客户通常创建并传递一个`ConcreteStrategy`对象给该`Context`，这样，客户仅与`Context`交互。通常有一系列的`ConcreateStrategy`可供客户从中选择

## 效果
### 优点
1. 相关算法系列：`Strategy`类层次为`Context`定义了一系列可供重用的算法或行为，方便复用
1. 替代继承：继承提供了另一种支持多种算法或行为的方案，可以直接生成`Context`子类，从而给它以不同的行为，但这会将行为硬编码到`Context`，而且会有重复。将算法的实现和`Context`实现耦合，会使得代码难以理解、难以维护和难以扩展，而且不能动态的改变算法。将算法独立封装使得我们可以独立于`Context`改变它，使它易于切换、易于理解、易于扩展
1. 消除了一些条件语句：将不同行为交由一个类负责时，很难避免使用条件语句进行选择，有时候将行为封装在`Strategy`中可以消除条件语句
1. 实现的选择：我们可以提供相同行为的不同，客户可以根据时间/空间权衡选择相应策略

### 缺点
1. 客户必须了解不同的`Strategy`：如果客户想要选择一个合适的`Strategy`，就需要了解这些`Strategy`的不同
1. `Strategy`和`Context`的通信开销：我们通过`Strategy`定义接口，可能有些`ConcreteStrategy`的算法不需要定义的参数，那么这个时候传递参数数据的开销就是多余的。当然也可以使用`Context`进行传递，然后根据需要去获取数据，但这种做法将使得`Stratyge`、`Context`进行更紧密的耦合。
1. 增加了对象的数目：只有需要一个`Strategy`的引用变量，有时候我们可以将`Strategy`实现为可供各`Context`共享的无状态对象来减少这一开销【？这里暂未理解】。

## 实现
### 1. 定义`Strategy`和`Context`接口
两者必须使得`ConcreteStrategy`能够有效访问它所需要的`Context`中的任何数据，反义亦然【反之亦然在这里是什么意思？让`Context`的子类可以获取到`Strategy`的任何数据？】。

一种方法是让`Context`将数据放到参数中传递，这使得两者解耦，但是对一些`ConcreteStrategy`可能会接收到不需要的数据。

另一种方法是将`Context`作为参数传递，然后`Strategy`根据自己需要显示的调用API以获取所需数据——这使得`Context`必须对它的数据定义一个更为精细的接口，这将`Strategy`的`Context`更紧密的耦合在一起

### 2. 将`Strategy`作为模板（泛型）参数
在支持泛型的语言中，在满足以下条件时可以使用：
1. 可以在编译时选择`Strategy`
2. 不需要在运行时改变

示例代码：
```c++
template <class AStrategy>
class Context {
   private:Abstrategy theStrategy;
   void Operation(){ theStrategy.DoAlgorithm(); }
   //...
}

class MyStrategy{
   public:void DoAlgorithm();
}

Context<MyStrategy> aContext;
```
//...

### 3. 使`Strategy`对象成为可选的
如果不使用额外的`Strategy`对象的情况下，`Context`还有意义的话，那么还可以进一步优化。`Context`在访问某`Strategy`前先检查它是否存在，如果有就直接使用，没有那么`Context`就执行缺省的行为。这种做法的好处是客户根本不需要处理`Strategy`对象，除非它们不喜欢缺省的行为

## 代码示例
见本目录下的示例代码

## 已知应用
//待补充...

## 相关模式
1. `FlyWeight`