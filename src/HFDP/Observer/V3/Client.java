package HFDP.Observer.V3;

public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);
        new StatisticsDisplay(weatherData);
        new ForecastDisplay(weatherData);
        weatherData.setMeasurements(15,8,70.1f);
    }
}