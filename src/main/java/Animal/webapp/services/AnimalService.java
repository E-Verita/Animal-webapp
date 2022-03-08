package Animal.webapp.services;

import Animal.webapp.models.Animal;
import Animal.webapp.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return animal;
    }
}
