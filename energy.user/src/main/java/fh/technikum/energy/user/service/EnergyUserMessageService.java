package fh.technikum.energy.user.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class EnergyUserMessageService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.name}")
    private String queueName;

    public EnergyUserMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(BigDecimal producedEnergy) {
        rabbitTemplate.convertAndSend(queueName, producedEnergy.toPlainString(), message -> {
            message.getMessageProperties().setTimestamp(new Date());
            return message;
        });
    }

}

