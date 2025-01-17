package MotorcycleRepo.CrudExample.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "T_MOTORCYCLES")
@Data
@NoArgsConstructor
public class MotorcycleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "CHASSIS_NUMBER")
    private Integer chassisNumber;

    @Column(name = "MAKER")
    private String maker;

    @Column(name="MODEL")
    private String model;

    @Column(name = "DISPLACEMENT")
    private Float displacement;

    @Column(name = "STYLE")
    private String style;

    public void printInfo(){
        System.out.println(this.chassisNumber.toString() + " - " + this.maker.toString() + " " + this.model.toString()+ " (" + this.style.toString() + ")");
    }
}
