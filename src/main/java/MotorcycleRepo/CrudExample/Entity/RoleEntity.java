package MotorcycleRepo.CrudExample.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "T_ROLES")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long ID;

    @Column(name = "NAME")
    private String name;
}
