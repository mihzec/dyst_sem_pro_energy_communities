package fh.technikum.energie.server.service;

import fh.technikum.energie.server.dto.HistoryDataDto;
import fh.technikum.energie.server.entity.HistoryData;
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

    public DataLoadService(HistoryDataRepository historyDataRepository) {
        this.historyDataRepository = historyDataRepository;
    }

    @Transactional
    public HistoryDataDto loadHistoryData(LocalDateTime start, LocalDateTime end) {
        //get data by timeperiod
        List<HistoryData> historyDataList = this.historyDataRepository.findByTimestampHourBetween(start, end);

        //sum results -> methode wird aufgerufen mit (list, HistoryData Objekt :: die getMethode ())
        BigDecimal communityProduced = sumValuesFromList(historyDataList, HistoryData::getCommunityProduced);
        BigDecimal communityUsed = sumValuesFromList(historyDataList, HistoryData::getCommunityUsed);
        BigDecimal gridUsed = sumValuesFromList(historyDataList, HistoryData::getGridUsed);

        return new HistoryDataDto(communityProduced, communityUsed, gridUsed);
    }

    //methode - iteriert über liste , holt je nach getterMetode jeweiligen wert -> ZERO, wenn nicht vorhanden, sonst addiert
    private BigDecimal sumValuesFromList(List<HistoryData> list, Function<HistoryData, BigDecimal> getterMethode) {
        return list.stream()
                .map(getterMethode)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}