package fh.technikum.energie.producer.app;

import fh.technikum.energie.producer.service.EnergieProducerMessageService;
import fh.technikum.energie.producer.service.weatherApiService.WeatherApiService;
import fh.technikum.energie.producer.service.weatherApiService.helper.WeatherCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ProducerApp implements CommandLineRunner {

    private final WeatherApiService weatherApiService;

    private final EnergieProducerMessageService energieProducerMessageService;

    private static final Logger LOG = LoggerFactory.getLogger(ProducerApp.class);

    @Value("${producer.interval.from}")
    private int intervalFrom;
    @Value("${producer.interval.to}")
    private int intervalTo;

    @Value("${kwh.range.from}")
    private double kwhRangeMin;

    @Value("${kwh.range.to}")
    private double kwhRangeMax;

    @Value("${city.latitude}")
    private double cityLatitude;

    @Value("${city.longitude}")
    private double cityLongitude;

    private final Random random;

    public ProducerApp(WeatherApiService weatherApiService, EnergieProducerMessageService energieProducerMessageService) {
        this.weatherApiService = weatherApiService;
        this.energieProducerMessageService = energieProducerMessageService;
        this.random = new Random();
    }

    @Override
    public void run(String... args) {
        LOG.info("energie.producer service - consuming simulation starting");
        simulateProduction();
    }

    private void simulateProduction() {
        // könnte noch optimiert werden in Richtung stündliche Abfrage - für unser Beispiel aber ausreichend
        WeatherCondition condition = weatherApiService.getCurrentWeatherConditions(cityLatitude, cityLongitude);
        LOG.info("energie.producer service - weather condition: {} - factor = {}", condition, condition.factor);

        AtomicBoolean running = new AtomicBoolean(true);
        while (running.get()) {
            BigDecimal kwhProduced = calculateProduction(condition);
            LOG.info("energie.producer service - generated: {} kWh", kwhProduced);
            energieProducerMessageService.sendMessage(kwhProduced);
            LOG.info("energie.producer service - message sent to queue");
            LOG.info("________________________________________________");
            try {
                Thread.sleep(calculateRandomWaitTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private BigDecimal calculateProduction(WeatherCondition condition) {
        double base = kwhRangeMin + random.nextDouble() * (kwhRangeMax - kwhRangeMin); //random base production
        double produced = Math.min(kwhRangeMax, base * condition.factor); //mit factor berechnen je nach wetter - aber als gate, nicht über maximal wert
        return BigDecimal.valueOf(produced).setScale(3, RoundingMode.HALF_UP);
    }

    private int calculateRandomWaitTime() {
        return intervalFrom + random.nextInt(intervalTo - intervalFrom + 1);
    }
}
