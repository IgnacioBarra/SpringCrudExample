package MotorcycleRepo.CrudExample.Repository;

import MotorcycleRepo.CrudExample.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.management.relation.Role;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);
}
