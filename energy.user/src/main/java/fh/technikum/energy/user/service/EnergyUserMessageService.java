package fh.technikum.energy.user.service;

import fh.technikum.energy.user.dto.MessageDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EnergyUserMessageService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.name}")
    private String queueName;

    public EnergyUserMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(MessageDto energyUserMessageDto) {
        rabbitTemplate.convertAndSend(queueName, energyUserMessageDto);
    }

}

