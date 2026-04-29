package fh.technikum.energie.server.repository;

import fh.technikum.energie.server.entity.HistoryDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryDataRepository extends JpaRepository<HistoryDataEntity, Long> {
    List<HistoryDataEntity> findByTimestampHourBetween(LocalDateTime start, LocalDateTime end);
}