package fh.technikum.energy.producer.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class EnergyProducerMessageService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.name}")
    private String queueName;

    public EnergyProducerMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(BigDecimal producedEnergy) {
        rabbitTemplate.convertAndSend(queueName, producedEnergy.toPlainString(), message -> {
            message.getMessageProperties().setTimestamp(new Date());
            return message;
        });
    }

}

