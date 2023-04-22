package 디자인패턴.옵저버패턴;

public class WeatherStation {
    public static void main(String[] args){
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        CurrentConditionsDisplay currentConditionsDisplay2 = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurement(12, 44, 34.5f);
    }
}
