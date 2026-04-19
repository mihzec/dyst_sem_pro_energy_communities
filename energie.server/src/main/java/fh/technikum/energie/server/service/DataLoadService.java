package fh.technikum.energie.server.service;

import fh.technikum.energie.server.entity.HistoryData;
import fh.technikum.energie.server.repository.HistoryDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DataLoadService {

    private final HistoryDataRepository historyDataRepository;

    public DataLoadService(HistoryDataRepository historyDataRepository) {
        this.historyDataRepository = historyDataRepository;
    }

    @Transactional
    public List<HistoryData> loadHistoryData(LocalDateTime start, LocalDateTime end) {
        return this.historyDataRepository.findByTimestampHourBetween(start,end);
    }
}
