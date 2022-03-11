package Animal.webapp.services;

import Animal.webapp.models.Adoption;
import Animal.webapp.models.Animal;
import Animal.webapp.models.enums.AdoptionStatus;
import Animal.webapp.models.enums.Status;
import Animal.webapp.repository.AdoptionRepository;
import Animal.webapp.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {
    AnimalRepository animalRepository;
    AdoptionRepository adoptionRepository;

    @Autowired
    public AnimalService(AnimalRepository animalRepository, AdoptionRepository adoptionRepository) {
        this.animalRepository = animalRepository;
        this.adoptionRepository = adoptionRepository;
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

    public void setAdoptionStatus(Long animalId, AdoptionStatus status) throws Exception {
        Animal animal = findAnimalById(animalId);
        animal.setAdoptionStatus(status);
        animalRepository.save(animal);
    }

    public List<Animal> findAllAnimalsByStatusAndShelterId(AdoptionStatus adoptionStatus, Long shelterId) throws Exception {
        List<Animal> allFromShelterUndergoing = animalRepository.findAllByAdoptionStatusAndShelterId(adoptionStatus, shelterId);
        if (allFromShelterUndergoing == null) {
            throw new Exception("Could not find any animals. Please try again!");
        }
        return allFromShelterUndergoing;
    }

    public List <Adoption> findAllAdoptionsByStatusAndShelterId(Long shelterId, Status status){
        return adoptionRepository.findAllByStatusAndShelterIdId(status, shelterId);
    }



}
