package fh.technikum.energie.server.repository;

import fh.technikum.energie.server.entity.HistoryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryDataRepository extends JpaRepository<HistoryData, Long> {
}
