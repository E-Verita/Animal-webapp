package Animal.webapp.services;

import Animal.webapp.models.Shelter;
import Animal.webapp.models.UserLogin;
import Animal.webapp.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class ShelterService {
    ShelterRepository shelterRepository;

    @Autowired
    public ShelterService(ShelterRepository shelterRepository) {
        this.shelterRepository = shelterRepository;
    }

    public Shelter verifyShelter(UserLogin userLogin) throws Exception {
        Shelter shelter = shelterRepository.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
        if (shelter == null) {
            throw new Exception("Email or password incorrect. Please try again!");
        }
        return shelter;
    }

    public void addShelter(Shelter shelter) throws Exception {
        shelterRepository.save(shelter);
    }

    public Shelter getShelter(Long sessionUserId) throws Exception{
        Shelter shelter = shelterRepository.findById(sessionUserId).orElseThrow();
        return shelter;
    }

    public void setCookie(HttpServletResponse response, Long id) {
        // create a cookie
        Cookie cookie = new Cookie("shelterId", id.toString());

        //add cookie to response
        response.addCookie(cookie);

    }

}
