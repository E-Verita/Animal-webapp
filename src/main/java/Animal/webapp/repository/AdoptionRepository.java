package Animal.webapp.repository;

import Animal.webapp.models.Adopter;
import Animal.webapp.models.Adoption;
import Animal.webapp.models.enums.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepository extends CrudRepository<Adoption, Long> {
    List<Adoption> findAll();
    List<Adoption> findAllByStatusAndAdopterId (Status status, Adopter adopterId);

//    List<Adoption> findAllByStatusAndShelterId(Status status, Long shelterId);

//    List<Adoption> findAllByShelterId(Long shelterId);

//    List<Adoption> findAllByStatus(Status status);

    List<Adoption> findAllByStatusAndShelterIdId(Status status, Long shelterId);
    Adoption findByAnimalIdId(Long animalId);
}

