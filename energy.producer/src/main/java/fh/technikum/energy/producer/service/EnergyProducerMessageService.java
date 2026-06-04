package fh.technikum.energy.producer.service;

import fh.technikum.energy.producer.dto.MessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnergyProducerMessageService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.name}")
    private String queueName;

    public EnergyProducerMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDto MessageDto) {
        rabbitTemplate.convertAndSend(queueName, MessageDto);
    }

}

