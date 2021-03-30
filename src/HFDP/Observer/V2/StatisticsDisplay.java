package HFDP.Observer.V2;

public class StatisticsDisplay  implements Observer,DisplayElement{
    private float pressure;

    public StatisticsDisplay(Subject weatherData){
        weatherData.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("这里是气象追踪布告板" + "，气压：" + pressure);
    }

    @Override
    public void update(float temp, float humidity, float pressure) {
        this.pressure = pressure;
        display();
    }
}
