package Animal.webapp.services;

import Animal.webapp.models.Adopter;
import Animal.webapp.models.UserLogin;
import Animal.webapp.repository.AdopterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdopterService {
    AdopterRepository adopterRepository;

    @Autowired
    public AdopterService(AdopterRepository adopterRepository) {
        this.adopterRepository = adopterRepository;
    }

    public Adopter verifyAdopter(UserLogin userLogin) throws Exception {
        Adopter adopter = adopterRepository.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
        if (adopter == null) {
            throw new Exception("Email or password incorrect");
        }
        return adopter;
    }
}
