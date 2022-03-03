package Animal.webapp.repository;

import Animal.webapp.models.Volunteer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends CrudRepository<Volunteer, Long> {
    Volunteer findByEmailAndPassword(String email, String password);
    List<Volunteer> findAll();
}
