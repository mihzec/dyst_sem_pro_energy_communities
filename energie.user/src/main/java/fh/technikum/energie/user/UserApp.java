package fh.technikum.energie.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class UserApp implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(UserApp.class);

    @Value("${user.interval.from}")
    private int intervalFrom;
    @Value("${user.interval.to}")
    private int intervalTo;

    @Value("${kwh.range.from}")
    private int kwhRangeMin;

    @Value("${kwh.range.to}")
    private int kwhRangeMax;

    public UserApp() {
    }

    @Override
    public void run(String... args) {
        LOG.info("energie.user service - consuming simulation starting");

        Random random = new Random();
        AtomicBoolean running = new AtomicBoolean(true);
        while (running.get()) {
            int consumption = kwhRangeMin + random.nextInt(kwhRangeMax - kwhRangeMin + 1);
            LOG.info("energie.user service - consumption: {} Wh/s", consumption);
            try {
                Thread.sleep(intervalFrom + random.nextInt(intervalTo + intervalFrom));
                //ToDo: RMQ call
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
