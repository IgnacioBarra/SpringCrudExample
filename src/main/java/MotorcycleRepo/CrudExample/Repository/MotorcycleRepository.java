package MotorcycleRepo.CrudExample.Repository;

import MotorcycleRepo.CrudExample.Entity.MotorcycleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MotorcycleRepository extends JpaRepository<MotorcycleEntity, Long> {

    @Query("SELECT m FROM MotorcycleEntity m WHERE m.chassisNumber = ?1")
    Optional<MotorcycleEntity> findMotorcycleByChassisNumber(Integer chassisNumber);

}
