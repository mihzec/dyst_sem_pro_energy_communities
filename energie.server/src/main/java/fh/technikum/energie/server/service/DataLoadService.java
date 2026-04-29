package fh.technikum.energie.server.service;

import fh.technikum.energie.server.dto.CurrentDataDto;
import fh.technikum.energie.server.dto.HistoryDataDto;
import fh.technikum.energie.server.entity.CurrentDataEntity;
import fh.technikum.energie.server.entity.HistoryDataEntity;
import fh.technikum.energie.server.repository.CurrentDataRepository;
import fh.technikum.energie.server.repository.HistoryDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

@Service
public class DataLoadService {

    private final HistoryDataRepository historyDataRepository;
    private final CurrentDataRepository currentDataRepository;


    public DataLoadService(HistoryDataRepository historyDataRepository, CurrentDataRepository currentDataRepository) {
        this.historyDataRepository = historyDataRepository;
        this.currentDataRepository = currentDataRepository;
    }

    @Transactional
    public CurrentDataDto loadCurrentData() {
        List<CurrentDataEntity> currentDataEntityList = this.currentDataRepository.findAll();

        CurrentDataEntity currentDataEntity = currentDataEntityList.getFirst();
        return new CurrentDataDto(currentDataEntity.getCommunityDepleted(), currentDataEntity.getGridPortion());
    }

    @Transactional
    public HistoryDataDto loadHistoryData(LocalDateTime start, LocalDateTime end) {
        //get data by timeperiod
        List<HistoryDataEntity> historyDataEntityList = this.historyDataRepository.findByTimestampHourBetween(start, end);

        //sum results -> methode wird aufgerufen mit (list, HistoryData Objekt :: die getMethode ())
        BigDecimal communityProduced = sumValuesFromList(historyDataEntityList, HistoryDataEntity::getCommunityProduced);
        BigDecimal communityUsed = sumValuesFromList(historyDataEntityList, HistoryDataEntity::getCommunityUsed);
        BigDecimal gridUsed = sumValuesFromList(historyDataEntityList, HistoryDataEntity::getGridUsed);

        return new HistoryDataDto(communityProduced, communityUsed, gridUsed);
    }

    //methode - iteriert über liste , holt je nach getterMetode jeweiligen wert -> ZERO, wenn nicht vorhanden, sonst addiert
    private BigDecimal sumValuesFromList(List<HistoryDataEntity> list, Function<HistoryDataEntity, BigDecimal> getterMethode) {
        return list.stream()
                .map(getterMethode)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}