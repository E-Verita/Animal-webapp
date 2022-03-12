package Animal.webapp.repository;

import Animal.webapp.models.Adopter;
import Animal.webapp.models.Adoption;
import Animal.webapp.models.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    List<Adoption> findAll();
    List<Adoption> findAllByStatusAndAdopterId (Status status, Adopter adopterId);
    List<Adoption> findAllByStatusAndShelterIdId(Status status, Long shelterId);
    Adoption findByAnimalIdId(Long animalId);

}

