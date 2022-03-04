package Animal.webapp.repository;

import Animal.webapp.models.Shelter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Long> {
    Shelter findByEmailAndPassword(String email, String password);
    List<Shelter> findAll();
}
