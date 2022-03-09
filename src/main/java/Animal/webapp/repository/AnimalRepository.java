package Animal.webapp.repository;

import Animal.webapp.models.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findByIdAndShelterId(Long id, Long shelterId);
    List<Animal> findAllByShelterId (Long shelterId);
}
