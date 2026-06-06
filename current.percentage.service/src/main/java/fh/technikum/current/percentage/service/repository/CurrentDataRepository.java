package fh.technikum.current.percentage.service.repository;

import fh.technikum.current.percentage.service.entity.CurrentDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDataRepository extends JpaRepository<CurrentDataEntity, Long> {

}