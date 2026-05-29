package fh.technikum.energy.user;

import fh.technikum.energy.user.service.EnergyUserMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class UserApp implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(UserApp.class);
    private final EnergyUserMessageService energyUserMessageService;

    @Value("${user.interval.from}")
    private int intervalFrom;
    @Value("${user.interval.to}")
    private int intervalTo;

    @Value("${kwh.range.from}")
    private double kwhRangeMin;
    @Value("${kwh.range.to}")
    private double kwhRangeMax;

    @Value("${kwh.peak.factor}")
    private double kwhPeakFactor;

    private final Random random;
    private final LocalTime morningEnd;
    private final LocalTime eveningStart;

    public UserApp(@Value("${consumption.morning.end}") String morningEnd,
                   @Value("${consumption.evening.start}") String eveningStart,
                   EnergyUserMessageService energyUserMessageService) {
        this.morningEnd = LocalTime.parse(morningEnd, DateTimeFormatter.ofPattern("HH:mm"));
        this.eveningStart = LocalTime.parse(eveningStart, DateTimeFormatter.ofPattern("HH:mm"));
        this.random = new Random();
        this.energyUserMessageService = energyUserMessageService;
    }

    @Override
    public void run(String... args) {
        LOG.info("energy.user service - consuming simulation starting");
        simulateConsumption();
    }

    private void simulateConsumption() {
        AtomicBoolean running = new AtomicBoolean(true);
        while (running.get()) {
            BigDecimal consumption = calculateConsumption();
            LOG.info("energy.user service - consumption: {} Wh", consumption);
            energyUserMessageService.sendMessage(consumption);
            try {
                Thread.sleep(calculateRandomWaitTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private int calculateRandomWaitTime() {
        return intervalFrom + random.nextInt(intervalTo - intervalFrom + 1);
    }

    private BigDecimal calculateConsumption() {
        LocalTime currenTime = LocalTime.now();
        boolean isPeak = currenTime.isBefore(morningEnd) || currenTime.isAfter(eveningStart);

        double baseConsumption = kwhRangeMin + random.nextDouble(kwhRangeMax - kwhRangeMin);
        //includes peakFactor in case currenTime is before || after time gates in application.properties file
        double consumption = isPeak ? baseConsumption * kwhPeakFactor : baseConsumption;

        return BigDecimal.valueOf(consumption)
                .setScale(3, RoundingMode.HALF_UP);
    }
}