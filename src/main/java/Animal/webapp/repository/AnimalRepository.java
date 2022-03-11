package Animal.webapp.repository;

import Animal.webapp.models.Animal;
import Animal.webapp.models.enums.AdoptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    Animal findByIdAndShelterId(Long id, Long shelterId);
    List<Animal> findAll();
    List<Animal> findAllByShelterId (Long shelterId);
    List<Animal> findAllByAdoptionStatus (AdoptionStatus adoptionStatus);

    List<Animal> findAllByAdoptionStatusAndShelterId(AdoptionStatus adoptionStatus, Long shelterId);
}
