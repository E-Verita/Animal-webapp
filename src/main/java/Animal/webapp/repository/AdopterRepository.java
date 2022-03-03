package Animal.webapp.repository;

import Animal.webapp.models.Adopter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdopterRepository extends CrudRepository<Adopter, Long> {
    Adopter findByEmailAndPassword(String email, String password);
    List<Adopter> findAll();
}

