package HFDP.Observer.V3;

import java.util.Observable;
import java.util.Observer;

public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;

    public CurrentConditionsDisplay(Observable weatherData){
        //如果想要取消注册，那么可以留有weatherData的引用
        weatherData.addObserver(this);
    }

    @Override
    public void display() {
        System.out.println("这里是实时信息布告板，" + "温度：" + temperature + "，湿度：" + humidity);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherData){
            WeatherData weatherData = (WeatherData) o;
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            display();
        }
    }
}
