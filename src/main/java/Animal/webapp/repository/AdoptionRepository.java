package Animal.webapp.repository;

import Animal.webapp.models.Adoption;
import Animal.webapp.models.enums.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdoptionRepository extends CrudRepository<Adoption, Long> {
    List<Adoption> findAll();
    List<Adoption> findAllByStatusAndAdopterId (Status status, Long adopterId);
}

