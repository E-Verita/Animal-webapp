package Animal.webapp.services;

import Animal.webapp.models.UserLogin;
import Animal.webapp.models.Volunteer;
import Animal.webapp.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {
    VolunteerRepository volunteerRepository;

    @Autowired
    public VolunteerService(VolunteerRepository volunteerRepository) {
        this.volunteerRepository = volunteerRepository;
    }

    public Volunteer verifyVolunteer(UserLogin userLogin) throws Exception {
        Volunteer volunteer = volunteerRepository.findByEmailAndPassword(userLogin.getEmail(), userLogin.getPassword());
        if (volunteer == null) {
            throw new Exception("Email or password incorrect");
        }
        return volunteer;
    }

}
