package fh.technikum.energy.producer.service.weatherApiService.helper;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherApiResponse(@JsonProperty("current") CurrentData current) {
    public record CurrentData(
            @JsonProperty("shortwave_radiation") double shortwaveRadiation,
            @JsonProperty("cloud_cover") double cloudCover
    ) {
    }
}