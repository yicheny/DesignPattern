package HFDP.Observer.V2;

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
        System.out.println("这里是实时信息布告板，" + "温度：" + temperature + "，湿度：" + humidity);
    }
}
