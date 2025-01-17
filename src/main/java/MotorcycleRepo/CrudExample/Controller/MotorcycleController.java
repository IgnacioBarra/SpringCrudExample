package MotorcycleRepo.CrudExample.Controller;

import MotorcycleRepo.CrudExample.Entity.MotorcycleEntity;
import MotorcycleRepo.CrudExample.Service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/motorcycles")
public class MotorcycleController {

    @Autowired
    private final MotorcycleService motorcycleService;

    public MotorcycleController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping
    public ResponseEntity<List<MotorcycleEntity>> listMotorcycles(){
        List<MotorcycleEntity> motorcycleList = motorcycleService.getAllMotorcycles();
        return new ResponseEntity<List<MotorcycleEntity>>(motorcycleList, HttpStatus.OK);

    }

    @GetMapping("/{Id}")
    public ResponseEntity<MotorcycleEntity> findMotorcycleById(@PathVariable("Id") Long Id){
        MotorcycleEntity motorcycle = motorcycleService.findById(Id);
        return new ResponseEntity<MotorcycleEntity>(motorcycle, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MotorcycleEntity> createMotorcycle(@RequestBody MotorcycleEntity motorcycle){
        MotorcycleEntity newMotorcycle = motorcycleService.addOrModifyMotorcycle(motorcycle);
        return new ResponseEntity<MotorcycleEntity>(newMotorcycle,HttpStatus.CREATED);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteMotorcycle(@PathVariable Long Id){
        motorcycleService.deleteMotorcycle(Id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<MotorcycleEntity> updateMotorcycle(@RequestBody MotorcycleEntity motorcycle){
        MotorcycleEntity updatedMotorcycle = motorcycleService.addOrModifyMotorcycle(motorcycle);
        return new ResponseEntity<MotorcycleEntity>(updatedMotorcycle,  HttpStatusCode.valueOf(204) );
    }


}
