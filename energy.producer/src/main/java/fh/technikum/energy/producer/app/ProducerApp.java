package fh.technikum.energy.producer.app;

import fh.technikum.energy.producer.dto.MessageDto;
import fh.technikum.energy.producer.service.EnergyProducerMessageService;
import fh.technikum.energy.producer.service.weatherApiService.WeatherApiService;
import fh.technikum.energy.producer.service.weatherApiService.helper.WeatherCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ProducerApp implements CommandLineRunner {

    private final WeatherApiService weatherApiService;

    private final EnergyProducerMessageService energyProducerMessageService;

    private static final Logger LOG = LoggerFactory.getLogger(ProducerApp.class);

    private final List<MessageDto> failedToSendMessages = new ArrayList<>();

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

    public ProducerApp(WeatherApiService weatherApiService, EnergyProducerMessageService energyProducerMessageService) {
        this.weatherApiService = weatherApiService;
        this.energyProducerMessageService = energyProducerMessageService;
        this.random = new Random();
    }

    @Override
    public void run(String... args) {
        logInfo("energy.producer service - consuming simulation starting");
        simulateProduction();
    }

    private void simulateProduction() {
        // könnte noch optimiert werden in Richtung stündliche Abfrage - für unser Beispiel aber ausreichend
        WeatherCondition condition = weatherApiService.getCurrentWeatherConditions(cityLatitude, cityLongitude);
        logInfo(String.format("energy.producer service - weather condition: %s - factor = %s", condition, condition.factor));

        AtomicBoolean running = new AtomicBoolean(true);
        while (running.get()) {
            BigDecimal kwhProduced = calculateProduction(condition);
            sendMessageToQueue(kwhProduced);

            try {
                Thread.sleep(calculateRandomWaitTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void sendMessageToQueue(BigDecimal kwhProduced) {

        logInfo(String.format("energy.producer service - generated: %s kWh", kwhProduced));
        MessageDto MessageDto = createMessageDto(kwhProduced);
        try {
            energyProducerMessageService.sendMessage(MessageDto);
            logInfo("energy.producer service - message sent to queue");

            if (!failedToSendMessages.isEmpty()) {
                resendMessages();
            }
        } catch (AmqpException e) {
            //rabbitMQ z.b. nicht erreichbar -> wenn msg nicht gesendet wird -> setzte in liste und versucht erneut
            failedToSendMessages.add(MessageDto);
            logInfo("energy.producer service - message not sent to queue");
        }
    }

    private void resendMessages() {
        Iterator<MessageDto> messageIterator = failedToSendMessages.iterator();
        while (messageIterator.hasNext()) {
            MessageDto message = messageIterator.next();
            try {
                energyProducerMessageService.sendMessage(message);
                messageIterator.remove();
                logInfo("energy.producer service - message resent to queue");
            } catch (AmqpException e) {
                logInfo("energy.producer service - message resend failed");
            }
        }
    }

    private void logInfo(String message) {
        LOG.info("________________________________________________");
        LOG.info(message);
        LOG.info("________________________________________________");
    }

    private BigDecimal calculateProduction(WeatherCondition condition) {
        double base = kwhRangeMin + random.nextDouble() * (kwhRangeMax - kwhRangeMin); //random base production
        double produced = Math.min(kwhRangeMax, base * condition.factor); //mit factor berechnen je nach wetter - aber als gate, nicht über maximal wert
        return BigDecimal.valueOf(produced).setScale(3, RoundingMode.HALF_UP);
    }

    private int calculateRandomWaitTime() {
        return intervalFrom + random.nextInt(intervalTo - intervalFrom + 1);
    }

    private MessageDto createMessageDto(BigDecimal kwhProduced) {
        return new MessageDto(LocalDateTime.now(), kwhProduced);
    }
}
