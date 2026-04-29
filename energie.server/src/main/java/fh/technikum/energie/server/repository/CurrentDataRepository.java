package fh.technikum.energie.server.repository;

import fh.technikum.energie.server.entity.CurrentDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDataRepository extends JpaRepository<CurrentDataEntity, Long> {

}