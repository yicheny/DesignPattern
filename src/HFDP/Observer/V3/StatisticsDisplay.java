package HFDP.Observer.V3;

import java.util.Observable;
import java.util.Observer;

public class StatisticsDisplay implements Observer, DisplayElement {
    private float pressure;

    public StatisticsDisplay(Observable weatherData){
        //如果想要取消注册，那么可以留有weatherData的引用
        weatherData.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("这里是气象追踪布告板" + "，气压：" + pressure);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData) o;
            this.pressure = weatherData.getPressure();
            display();
        }
    }
}