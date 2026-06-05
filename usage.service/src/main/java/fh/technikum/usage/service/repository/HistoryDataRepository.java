package fh.technikum.usage.service.repository;

import fh.technikum.usage.service.entity.HistoryDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface HistoryDataRepository extends JpaRepository<HistoryDataEntity, Long> {

    Optional<HistoryDataEntity> findByTimestampHour(LocalDateTime timestampHour);
}