package fh.technikum.current.percentage.service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue updateMessage(@Value("${queue.update}") String queueName) {
        return new Queue(queueName, true);
    }
}
