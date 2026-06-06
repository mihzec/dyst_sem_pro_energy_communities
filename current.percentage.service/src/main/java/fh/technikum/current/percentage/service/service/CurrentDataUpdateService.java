package fh.technikum.current.percentage.service.service;

import fh.technikum.current.percentage.service.dto.UpdateMessageDto;
import fh.technikum.current.percentage.service.entity.CurrentDataEntity;
import fh.technikum.current.percentage.service.repository.CurrentDataRepository;
import fh.technikum.current.percentage.service.utils.LogUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrentDataUpdateService {

    private final CurrentDataRepository currentDataRepository;

    public CurrentDataUpdateService(CurrentDataRepository currentDataRepository) {
        this.currentDataRepository = currentDataRepository;
    }

    @Transactional
    public synchronized void updateCurrentData(UpdateMessageDto updateMessageDto) {
        LogUtil.printInfo("Updating current data");

        CurrentDataEntity currentData = currentDataRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new CurrentDataEntity());

        //konvertiere die Werte aus der rabbit message in BigDecimal
        BigDecimal communityProduced = BigDecimal.valueOf(updateMessageDto.communityProduced());
        BigDecimal communityUsed = BigDecimal.valueOf(updateMessageDto.communityUsed());
        BigDecimal gridUsed = BigDecimal.valueOf(updateMessageDto.gridUsed());

        //berechne
        BigDecimal communityDepleted = communityProduced.compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : communityUsed.divide(communityProduced, 4, RoundingMode.HALF_UP)
                  .multiply(BigDecimal.valueOf(100));

        BigDecimal total = communityUsed.add(gridUsed);
        BigDecimal gridPortion = total.compareTo(BigDecimal.ZERO) == 0
                ? BigDecimal.ZERO
                : gridUsed.divide(total, 4, RoundingMode.HALF_UP)
                  .multiply(BigDecimal.valueOf(100));

        currentData.setTimestampHour(updateMessageDto.timestamp_hour());
        currentData.setCommunityDepleted(communityDepleted);
        currentData.setGridPortion(gridPortion);

        currentDataRepository.save(currentData);
    }
}
