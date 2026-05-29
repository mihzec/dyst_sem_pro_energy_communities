package fh.technikum.energy.producer.service.weatherApiService.helper;

public enum WeatherCondition {
    //type(factor für Berechnung)
    FULL_SUN(0.5),
    PARTLY_CLOUDY(0.3),
    CLOUDY(0.2),
    OVERCAST(0.1);

    public final double factor;

    WeatherCondition(double factor) {
        this.factor = factor;
    }
}