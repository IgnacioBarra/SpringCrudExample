package MotorcycleRepo.CrudExample.Service;

import MotorcycleRepo.CrudExample.Entity.MotorcycleEntity;
import MotorcycleRepo.CrudExample.Repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleService {


    private final MotorcycleRepository motorcycleRepository;

    @Autowired
    public MotorcycleService(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    public List<MotorcycleEntity> getAllMotorcycles(){
        return motorcycleRepository.findAll();
    }

    public MotorcycleEntity findById(Long id){
        return motorcycleRepository.findById(id).orElse(null);
    }

    public Optional<MotorcycleEntity> findByChassisNumber(Integer chassisNumber){
        return motorcycleRepository.findMotorcycleByChassisNumber(chassisNumber);
    }

    public MotorcycleEntity addOrModifyMotorcycle(MotorcycleEntity motorcycle){
        if(findByChassisNumber(motorcycle.getChassisNumber()).isPresent()){
            throw new IllegalStateException("Motorcycle chassis already registered");
        }else{
           return saveMotorcycle(motorcycle);
        }
    }
    //probably redundant method, but will keep it here in case I need a different, independent business logic.
    public MotorcycleEntity saveMotorcycle(MotorcycleEntity motorcycle){
        return motorcycleRepository.save(motorcycle);
    }

    public void deleteMotorcycle(Long Id){
        motorcycleRepository.deleteById(Id);
    }



}
