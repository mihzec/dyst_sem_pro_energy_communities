package fh.technikum.current.percentage.service.service;

import fh.technikum.current.percentage.service.dto.UpdateMessageDto;
import fh.technikum.current.percentage.service.utils.LogUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class MessageInService {

     private final CurrentDataUpdateService currentDataUpdateService;

    public MessageInService(CurrentDataUpdateService currentDataUpdateService) {
        this.currentDataUpdateService = currentDataUpdateService;
    }

    @RabbitListener(queues = "${queue.update}")
    public void readFromProducerMessageQueue(UpdateMessageDto updateMessageDto) {
        LogUtil.printInfo(String.format("UPDATE - Received message: %s - %s kWh", updateMessageDto.timestamp_msg()));
        currentDataUpdateService.updateCurrentData(updateMessageDto);
    }
}