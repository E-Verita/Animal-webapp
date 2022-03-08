package Animal.webapp.repository;

import Animal.webapp.models.Animal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {
    Animal findByShelterId(Long shelterId);

}
