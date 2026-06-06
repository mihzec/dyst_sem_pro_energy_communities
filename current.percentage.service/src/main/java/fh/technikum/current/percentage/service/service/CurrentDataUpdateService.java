package fh.technikum.current.percentage.service.service;

import fh.technikum.current.percentage.service.dto.UpdateMessageDto;
import fh.technikum.current.percentage.service.entity.CurrentDataEntity;
import fh.technikum.current.percentage.service.repository.CurrentDataRepository;
import fh.technikum.current.percentage.service.utils.LogUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrentDataUpdateService {

    private final CurrentDataRepository currentDataRepository;

    public CurrentDataUpdateService(CurrentDataRepository currentDataRepository) {
        this.currentDataRepository = currentDataRepository;
    }

    @Transactional
    public void updateCurrentData(UpdateMessageDto updateMessageDto) {
        LogUtil.printInfo("Updating current data");
        CurrentDataEntity currentData = currentDataRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new CurrentDataEntity());
        //TODO: calculate percentage
        mapDtoToEntity(currentData, updateMessageDto);
        currentDataRepository.save(currentData);
    }

    private void mapDtoToEntity(CurrentDataEntity currentDataEntity, UpdateMessageDto updateMessageDto) {
        currentDataEntity.setTimestampHour(updateMessageDto.timestamp_hour());
        currentDataEntity.setGridPortion(new BigDecimal(updateMessageDto.gridUsed()));
        currentDataEntity.setCommunityDepleted(new BigDecimal(updateMessageDto.communityUsed()));
    }
}
