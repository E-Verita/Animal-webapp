package Animal.webapp.repository;

import Animal.webapp.models.Shelter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShelterRepository extends CrudRepository <Shelter, Long>{
    Shelter findByNameAndPassword(String name, String password);
    List<Shelter> findAll();
}
