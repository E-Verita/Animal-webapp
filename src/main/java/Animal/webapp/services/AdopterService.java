package Animal.webapp.services;

import Animal.webapp.models.Adopter;
import Animal.webapp.models.Adoption;
import Animal.webapp.models.Animal;
import Animal.webapp.models.UserLogin;
import Animal.webapp.models.enums.AdoptionStatus;
import Animal.webapp.models.enums.Status;
import Animal.webapp.repository.AdopterRepository;
import Animal.webapp.repository.AdoptionRepository;
import Animal.webapp.repository.AnimalRepository;
import Animal.webapp.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class AdopterService {
    AdopterRepository adopterRepository;
    AnimalRepository animalRepository;
    AdoptionRepository adoptionRepository;
    ShelterRepository shelterRepository;

    @Autowired
    public AdopterService(AdopterRepository adopterRepository, AnimalRepository animalRepository, AdoptionRepository adoptionRepository) {
        this.adopterRepository = adopterRepository;
        this.animalRepository = animalRepository;
        this.adoptionRepository = adoptionRepository;

    }

    public Adopter verifyAdopter(UserLogin userLogin) throws Exception {
        Adopter adopter = adopterRepository.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
        if (adopter == null) {
            throw new Exception("Email or password incorrect. Please try again!");
        }
        return adopter;
    }

    public void addAdopter(Adopter adopter) throws Exception {
        adopterRepository.save(adopter);
    }

    public void setCookie(HttpServletResponse response, Long id) {
        Cookie cookie = new Cookie("adopterId", id.toString());
        response.addCookie(cookie);
    }

    public Adopter getAdopter(Long adopterId) {
        Adopter adopter = adopterRepository.findById(adopterId).orElseThrow();
        return adopter;
    }

    public List<Animal> findAllAnimalsAvailableForAdoption(AdoptionStatus adoptionStatus) throws Exception {
        List<Animal> availableAnimals = animalRepository.findAllByAdoptionStatus(AdoptionStatus.Available);
        if (availableAnimals == null) {
            throw new Exception("Could not find any animals available for adoption. Please try again!");
        }
        return availableAnimals;
    }

    public void addAdoption(Adoption adoption, Status status, Long adopterId) {
        Adopter adopter = getAdopter(adopterId);
        adoption.setAdopterId(adopter);
        adoption.setStatus(status);
        adoption.setShelterId(adoption.getAnimalId().getShelter());
        adoptionRepository.save(adoption);
    }

    public List<Adoption> findAllByStatusAndAdopterId(Status status, Long adopterId) throws Exception {
        Adopter adopter = getAdopter(adopterId);
        List<Adoption> adoptionList = adoptionRepository.findAllByStatusAndAdopterId(status, adopter);
        if (adoptionList == null) {
            throw new Exception("You don't have any applications for adoption!");
        }
        return adoptionList;
    }



}
