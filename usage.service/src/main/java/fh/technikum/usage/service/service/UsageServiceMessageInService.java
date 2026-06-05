package fh.technikum.usage.service.service;

import fh.technikum.usage.service.dto.ReceivedMessageDto;
import fh.technikum.usage.service.service.enums.SenderType;
import fh.technikum.usage.service.utils.LogUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceMessageInService {

    private final HistoryDataUpdateService historyDataUpdateService;

    public UsageServiceMessageInService(HistoryDataUpdateService historyDataUpdateService) {
        this.historyDataUpdateService = historyDataUpdateService;
    }

    @RabbitListener(queues = "${queue.producer}")
    public void readFromProducerMessageQueue(ReceivedMessageDto receivedMessageDto) {
        LogUtil.printInfo(String.format("PRODUCER - Received message: %s - %s kWh", receivedMessageDto.datetime(), receivedMessageDto.kwh()));
        historyDataUpdateService.updateCurrentData(receivedMessageDto, SenderType.PRODUCER);
    }

    @RabbitListener(queues = "${queue.user}")
    public void readFromUserMessageQueue(ReceivedMessageDto receivedMessageDto) {
        LogUtil.printInfo(String.format("USER - Received message: %s - %s kWh", receivedMessageDto.datetime(), receivedMessageDto.kwh()));
        historyDataUpdateService.updateCurrentData(receivedMessageDto, SenderType.USER);
    }
}