package MotorcycleRepo.CrudExample;

import MotorcycleRepo.CrudExample.Controller.MotorcycleController;
import MotorcycleRepo.CrudExample.Entity.MotorcycleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MotorcycleControllerTests {
    @Autowired
    MotorcycleController motorcycleController;

    @Test
    void listMotorcyclesTest(){
        ResponseEntity<List<MotorcycleEntity>> re = motorcycleController.listMotorcycles();

        assertThat(re.getBody()).isNotNull();
        List<MotorcycleEntity> list = re.getBody();
        assertThat(list.size() >= 3).isTrue();
        assertThat(list.get(0).getChassisNumber()).isEqualTo(123456789);
        assertThat(list.get(0).getID()).isEqualTo(0L);
    }

    //Add test and functionality for findMotorcycleByChassisNumber

    @Test
    void findMotorcycleByIdTest(){
        ResponseEntity<MotorcycleEntity> re = motorcycleController.findMotorcycleById(0L);
        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(re.getBody()).isNotNull();

        MotorcycleEntity motorcycle = re.getBody();
        assertThat(motorcycle.getID()).isEqualTo(0L);
        assertThat(motorcycle.getChassisNumber()).isEqualTo(123456789);
        assertThat(motorcycle.getMaker()).isEqualTo("KTM");
        assertThat(motorcycle.getModel()).isEqualTo("Duke 200");
        assertThat(motorcycle.getDisplacement()).isEqualTo(199.5F);
        assertThat(motorcycle.getStyle()).isEqualTo("Naked");
    }

    @Test
    @DirtiesContext
    void saveNewMotorcycleTest(){
        MotorcycleEntity newMotorcycle = new MotorcycleEntity();
        newMotorcycle.setChassisNumber(852741963);
        newMotorcycle.setDisplacement(184.4F);
        newMotorcycle.setMaker("Honda");
        newMotorcycle.setModel("CB190R");
        newMotorcycle.setStyle("Naked");

        ResponseEntity<MotorcycleEntity> response = motorcycleController.createMotorcycle(newMotorcycle);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Long newId = response.getBody().getID();
        assertThat(newId).isEqualTo(3L);

        //a bit of redundancy never hurt anyone but efficiency
        ResponseEntity<MotorcycleEntity> re = motorcycleController.findMotorcycleById(3L);
        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(re.getBody()).isNotNull();
        MotorcycleEntity motorcycleCheck = re.getBody();
        assertThat(motorcycleCheck.getID()).isEqualTo(newId);
        assertThat(motorcycleCheck.getChassisNumber()).isEqualTo(852741963);
        assertThat(motorcycleCheck.getMaker()).isEqualTo("Honda");
        assertThat(motorcycleCheck.getModel()).isEqualTo("CB190R");
        assertThat(motorcycleCheck.getDisplacement()).isEqualTo(184.4F);
        assertThat(motorcycleCheck.getStyle()).isEqualTo("Naked");
    }

    @Test
    void saveNewMotorcycleWithExistingChassisNumber(){
        ResponseEntity<MotorcycleEntity> re = motorcycleController.findMotorcycleById(0L);
        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(re.getBody()).isNotNull();
        MotorcycleEntity existingMotorcycle = re.getBody();

        MotorcycleEntity newMotorcycle = new MotorcycleEntity();
        newMotorcycle.setChassisNumber(existingMotorcycle.getChassisNumber());
        newMotorcycle.setDisplacement(184.4F);
        newMotorcycle.setMaker("Honda");
        newMotorcycle.setModel("CB190R");
        newMotorcycle.setStyle("Naked");

        try {
            motorcycleController.createMotorcycle(newMotorcycle);
        }catch (Exception e){
            assertThat(e.getMessage()).contains("Motorcycle chassis already registered");
        }
    }


    @Test
    @DirtiesContext
    void deleteMotorcycleTest(){
        ResponseEntity<MotorcycleEntity> re = motorcycleController.findMotorcycleById(1L);
        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(re.getBody()).isNotNull();
        MotorcycleEntity existingMotorcycle = re.getBody();

        assertThat(existingMotorcycle).isNotNull();
        assertThat(existingMotorcycle.getChassisNumber()).isEqualTo(456789123);

        ResponseEntity<Void> response = motorcycleController.deleteMotorcycle(existingMotorcycle.getID());
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        re = motorcycleController.findMotorcycleById(1L);
        existingMotorcycle = re.getBody();
        assertThat(re.getBody()).isNull();
        assertThat(existingMotorcycle).isNull();
    }
	@Test
	@DirtiesContext
	void updateMotorcycleTest(){
        Integer newChassisNum = 775533456;

        ResponseEntity<MotorcycleEntity> re = motorcycleController.findMotorcycleById(0L);
        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(re.getBody()).isNotNull();
        MotorcycleEntity existingMotorcycle = re.getBody();

		Integer oldChassisNum = existingMotorcycle.getChassisNumber();
        assertThat(oldChassisNum).isNotEqualTo(newChassisNum);

		existingMotorcycle.setChassisNumber(newChassisNum);
		ResponseEntity<MotorcycleEntity> response = motorcycleController.updateMotorcycle(existingMotorcycle);
        assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        re = motorcycleController.findMotorcycleById(0L);
        assertThat(re.getStatusCode()).isEqualTo(HttpStatus.OK);
        existingMotorcycle = re.getBody();
        assertThat(existingMotorcycle.getChassisNumber()).isEqualTo(newChassisNum);
	}
}
