package fh.technikum.usage.service.service;

import fh.technikum.usage.service.dto.UpdateMessageDto;
import fh.technikum.usage.service.utils.LogUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceMessageOutService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${queue.update}")
    private String queueName;

    public UsageServiceMessageOutService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendUpdateMessage(UpdateMessageDto updateMessageDto) {
        rabbitTemplate.convertAndSend(queueName, updateMessageDto);
        LogUtil.printInfo("update sent");
    }

}