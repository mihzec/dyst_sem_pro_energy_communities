package fh.technikum.energie.server.repository;

import fh.technikum.energie.server.entity.HistoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryDataRepository extends JpaRepository<HistoryData, Long> {
    List<HistoryData> findByTimestampHourBetween(LocalDateTime start, LocalDateTime end);
}