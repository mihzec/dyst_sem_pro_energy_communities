package fh.technikum.energie.producer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {

    @Bean
    public Queue producerMessage(){
        return new Queue("producer_message", true);
    }
}
