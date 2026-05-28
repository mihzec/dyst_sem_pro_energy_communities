package fh.technikum.energie.producer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EnergieProducerMessageService {

    private final RabbitTemplate rabbitTemplate;

    public EnergieProducerMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(BigDecimal producedEnergy) {
        rabbitTemplate.convertAndSend("producer_message", producedEnergy);
    }

}

