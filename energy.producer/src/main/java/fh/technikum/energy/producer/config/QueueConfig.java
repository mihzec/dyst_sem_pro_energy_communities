package fh.technikum.energy.producer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue producerMessage(@Value("${queue.name}") String queueName){
        return new Queue(queueName, true);
    }
}
