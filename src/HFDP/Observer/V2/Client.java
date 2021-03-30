package HFDP.Observer.V2;

public class Client {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        new CurrentConditionsDisplay(weatherData);
        new StatisticsDisplay(weatherData);
        new ForecastDisplay(weatherData);

        weatherData.setMeasurements(25,10,40.4f);
    }
}
