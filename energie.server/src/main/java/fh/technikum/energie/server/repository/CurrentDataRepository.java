package fh.technikum.energie.server.repository;

import fh.technikum.energie.server.entity.CurrentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentDataRepository extends JpaRepository<CurrentData, Long> {

}