package Animal.webapp.repository;

import Animal.webapp.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findByIdAndShelterId(Long id, Long shelterId);

}
