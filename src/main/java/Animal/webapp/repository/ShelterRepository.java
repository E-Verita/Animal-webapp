package Animal.webapp.repository;

import Animal.webapp.models.Shelter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends CrudRepository <Shelter, Long>{
    Shelter findByEmailAndPassword(String email, String password);
    List<Shelter> findAll();
}
