package fh.technikum.energie.producer;

import fh.technikum.energie.producer.service.WeatherApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ProducerApp implements CommandLineRunner {

    private final WeatherApiService weatherApiService;

    private static final Logger LOG = LoggerFactory.getLogger(ProducerApp.class);

    @Value("${producer.interval.from}")
    private int intervalFrom;
    @Value("${producer.interval.to}")
    private int intervalTo;

    @Value("${kwh.range.from}")
    private double kwhRangeMin;

    @Value("${kwh.range.to}")
    private double kwhRangeMax;

    private final Random random;

    public ProducerApp(WeatherApiService weatherApiService) {
        this.weatherApiService = weatherApiService;
        this.random = new Random();
    }

    @Override
    public void run(String... args) {
        LOG.info("energie.producer service - consuming simulation starting");
        simulateProduction();
    }

    private void simulateProduction() {
        AtomicBoolean running = new AtomicBoolean(true);
        while (running.get()) {
            // TODO: call something like weatherApiService.getCurrentWeather();
            BigDecimal kwhProduced = new BigDecimal("0.001"); //temp value
            LOG.info("energie.producer service - generated: {} Wh", kwhProduced);
            try {
                Thread.sleep(calculateRandomWaitTime());
                //ToDo: RMQ call
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private int calculateRandomWaitTime() {
        return intervalFrom + random.nextInt(intervalTo - intervalFrom + 1);
    }
}
