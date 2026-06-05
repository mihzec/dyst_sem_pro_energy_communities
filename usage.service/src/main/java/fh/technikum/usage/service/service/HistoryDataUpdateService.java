package fh.technikum.usage.service.service;

import fh.technikum.usage.service.service.enums.SenderType;
import fh.technikum.usage.service.dto.ReceivedMessageDto;
import fh.technikum.usage.service.entity.HistoryDataEntity;
import fh.technikum.usage.service.repository.HistoryDataRepository;
import fh.technikum.usage.service.utils.LogUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
public class HistoryDataUpdateService {

    private final HistoryDataRepository historyDataRepository;

    public HistoryDataUpdateService(HistoryDataRepository historyDataRepository) {
        this.historyDataRepository = historyDataRepository;
    }

    @Transactional
    public synchronized void updateCurrentData(ReceivedMessageDto receivedMessageDto, SenderType senderType) {
        LogUtil.printInfo("Updating current data");

        LocalDateTime hour = receivedMessageDto.timestamp().truncatedTo(ChronoUnit.HOURS);

        HistoryDataEntity historyData = historyDataRepository.findByTimestampHour(hour)
                .orElse(new HistoryDataEntity(hour));

        if (senderType == SenderType.PRODUCER) {
            //add producer energy
            BigDecimal current = historyData.getCommunityProduced() != null
                    ? historyData.getCommunityProduced() : BigDecimal.ZERO;
            historyData.setCommunityProduced(current.add(receivedMessageDto.energyValue()));
        } else {
            //check if params are available, if not set to 0
            BigDecimal produced = historyData.getCommunityProduced() != null
                    ? historyData.getCommunityProduced() : BigDecimal.ZERO;
            BigDecimal alreadyUsed = historyData.getCommunityUsed() != null
                    ? historyData.getCommunityUsed() : BigDecimal.ZERO;
            BigDecimal gridUsed = historyData.getGridUsed() != null
                    ? historyData.getGridUsed() : BigDecimal.ZERO;

            //check available energy
            BigDecimal available = produced.subtract(alreadyUsed);
            //requested by energy.user
            BigDecimal requested = receivedMessageDto.energyValue();

            //check if energy can be used from community or must be used from grid
            if (requested.compareTo(available) <= 0) {
                historyData.setCommunityUsed(alreadyUsed.add(requested));
            } else {
                BigDecimal fromGrid = requested.subtract(available);
                historyData.setCommunityUsed(produced);
                historyData.setGridUsed(gridUsed.add(fromGrid));
            }
        }

        historyDataRepository.save(historyData);
    }
}
