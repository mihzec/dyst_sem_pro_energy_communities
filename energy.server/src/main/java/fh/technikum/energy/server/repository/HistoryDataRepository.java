package fh.technikum.energy.server.repository;

import fh.technikum.energy.server.entity.HistoryDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface HistoryDataRepository extends JpaRepository<HistoryDataEntity, Long> {
    List<HistoryDataEntity> findByTimestampHourBetween(LocalDateTime start, LocalDateTime end);
}