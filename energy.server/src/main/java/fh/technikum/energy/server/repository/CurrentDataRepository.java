package fh.technikum.energy.server.repository;

import fh.technikum.energy.server.entity.CurrentDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDataRepository extends JpaRepository<CurrentDataEntity, Long> {

}