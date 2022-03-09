package Animal.webapp.services;

import Animal.webapp.models.Animal;
import Animal.webapp.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class AnimalService {
    AnimalRepository animalRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void addAnimal(Animal animal) throws Exception {
        animalRepository.save(animal);
    }


    public Animal findAnimalById(Long id) throws Exception {
        Animal animal = animalRepository.findById(id).orElseThrow();
        if (animal == null) {
            throw new Exception("Could not find animal. Please try again!");
        }
        return animal;
    }

    public Animal findByIdAndShelter(Long id, Long sessionUserId) throws Exception {
        Animal animal = animalRepository.findByIdAndShelterId(id, sessionUserId);
        if (animal == null) {
            throw new Exception("Could not find animal. Please try again!");
        }
        return animal;
    }

    public void setCookie(HttpServletResponse response, Long id) {
        Cookie cookie = new Cookie("animalId", id.toString());
        response.addCookie(cookie);
    }

    public void deleteCookie(HttpServletResponse response, Long id) {
        Cookie cookie = new Cookie("animalId", id.toString());
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public void deleteAnimal(Long animalId) {
        animalRepository.deleteById(animalId);
    }

    public List findAllByShelterId(Long shelterId) throws Exception {
        List<Animal> allAnimals = animalRepository.findAllByShelterId(shelterId);
        if (allAnimals == null) {
            throw new Exception("Could not find any animals. Please try again!");
        }
        return allAnimals;
    }
}
