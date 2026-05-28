package fh.technikum.energie.producer.service.weatherApiService;

import fh.technikum.energie.producer.service.weatherApiService.helper.WeatherApiResponse;
import fh.technikum.energie.producer.service.weatherApiService.helper.WeatherCondition;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collection;
import java.util.List;

@Service
public class WeatherApiService {

    private final RestClient restClient = RestClient.create();

    public WeatherApiService() {
    }

    public WeatherCondition getCurrentWeatherConditions(double latitude, double longitude) {
        WeatherApiResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .host("api.open-meteo.com")
                        .path("/v1/forecast")
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("current", apiCallParams())
                        .queryParam("timezone", "Europe/Vienna")
                        .queryParam("forecast_days", "1")
                        .build())
                .retrieve()
                .body(WeatherApiResponse.class);
        return resolveCondition(response.current().cloudCover(), response.current().shortwaveRadiation());
    }

    private Collection<String> apiCallParams() {
        return List.of(
                "shortwave_radiation",
                "cloud_cover"
        );
    }

    private WeatherCondition resolveCondition(double cloudCover, double radiation) {
        if (radiation > 600 && cloudCover < 20) return WeatherCondition.FULL_SUN;
        if (radiation > 300 && cloudCover < 60) return WeatherCondition.PARTLY_CLOUDY;
        if (radiation > 100) return WeatherCondition.CLOUDY;
        return WeatherCondition.OVERCAST;
    }
}
