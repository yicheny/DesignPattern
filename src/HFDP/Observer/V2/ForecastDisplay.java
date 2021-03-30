package HFDP.Observer.V2;

public class ForecastDisplay implements Observer,DisplayElement{
    private float pressure;

    public ForecastDisplay(Subject weatherData){
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("这里是天气预告布告板" + "，气压：" + pressure);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.pressure = pressure;
        display();
    }
}