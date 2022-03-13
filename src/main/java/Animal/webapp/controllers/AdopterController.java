package Animal.webapp.controllers;

import Animal.webapp.models.Adopter;
import Animal.webapp.models.Adoption;
import Animal.webapp.models.Animal;
import Animal.webapp.models.UserLogin;
import Animal.webapp.models.enums.AdoptionStatus;
import Animal.webapp.models.enums.HousingType;
import Animal.webapp.models.enums.Status;
import Animal.webapp.services.AdopterService;
import Animal.webapp.services.AnimalService;
import Animal.webapp.services.PageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/adopter")
@Controller
public class AdopterController {
    PageDataService pageDataService;
    AdopterService adopterService;
    AnimalService animalService;

    @Autowired
    public AdopterController(PageDataService pageDataService, AdopterService adopterService, AnimalService animalService) {
        this.pageDataService = pageDataService;
        this.adopterService = adopterService;
        this.animalService = animalService;
    }

    @GetMapping("/login")
    public String showAdopterLoginPage(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message,
            Model model
    ) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("adopterlogin"));
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "adopterlogin";
    }

    @PostMapping("/login")
    public String handleAdopterLogin(UserLogin userLogin, HttpServletResponse response) {
        try {
            Adopter adopter = adopterService.verifyAdopter(userLogin);
            adopterService.setCookie(response, adopter.getId());
            return "redirect:profile";
        } catch (Exception exception) {
            return "redirect:login?status=login_failed&message=" + exception.getMessage();
        }
    }

    @GetMapping("/register")
    public String showAdopterRegisterPage(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("adopterregister"));
        model.addAttribute("adopter", new Adopter());
        model.addAttribute("housingType", HousingType.values());
        return "adopterregister";
    }

    @PostMapping("/register")
    public String processAdopterRegisterPage(@ModelAttribute @Valid Adopter adopter) {
        try {
            adopterService.addAdopter(adopter);
            return "redirect:login?status=signup_success";
        } catch (Exception ex) {
            return "redirect:register?status=signup_failed&message=" + ex.getMessage();
        }
    }

    @GetMapping("/profile")  // - if time, add {adopterId}
    public String showAdopterProfile(@CookieValue(value = "adopterId", required = false) Long adopterId,
                                     Model model) {
        model.addAttribute("adopter", adopterService.getAdopter(adopterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getAdopterPage("adopterprofile"));
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        return "adopterprofile";
    }

    @GetMapping("/search")
    public String searchForAnimals(@CookieValue(value = "adopterId", required = false) Long adopterId,
                                   @RequestParam(name = "status", required = false) String status,
                                   @RequestParam(name = "message", required = false) String message,
                                   Model model) throws Exception {
        model.addAttribute("animalList", adopterService.findAllAnimalsAvailableForAdoption(AdoptionStatus.Available));
        model.addAttribute("adopter", adopterService.getAdopter(adopterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getAdopterPage("adoptersearchpage"));
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "adoptersearchpage";
    }

    @PostMapping("/search")
    public String processSearchForAnimals(Long id) throws Exception {
        Animal animal = animalService.findAnimalById(id);
        return "redirect:search/apply?status=animal_to_adopt_found" + "&animalId=" + animal.getId();

    }

    @GetMapping("/search/apply")
    public String applyForAnimals(@CookieValue(value = "adopterId", required = false) Long adopterId,
                                  @RequestParam(name = "status", required = false) String status,
                                  @RequestParam(name = "message", required = false) String message,
                                  @RequestParam(value = "animalId", required = false) Long animalId,
                                  Model model) throws Exception {
        model.addAttribute("adoption", new Adoption());
        model.addAttribute("animal", animalService.findAnimalById(animalId));
        model.addAttribute("animalList", adopterService.findAllAnimalsAvailableForAdoption(AdoptionStatus.Available));
        model.addAttribute("adopter", adopterService.getAdopter(adopterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getAdopterPage("adoptersearchpage"));
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "adopter-apply-for-adoption";
    }

    @PostMapping("/search/apply")
    public String processApplyForAnimals(@CookieValue(value = "adopterId", required = false) Long adopterId,
                                         @RequestParam(value = "animalId", required = false) Long animalId,
                                         @ModelAttribute Adoption adoption)
            throws Exception {
        try {
            adopterService.addAdoption(adoption, Status.Undergoing, adopterId);
            animalService.setAdoptionStatus(animalId, AdoptionStatus.Undergoing);
            return "redirect:?status=animal_adopted";
        } catch (Exception ex) {
            return "redirect:?status=animal_adoption_failed";
        }
    }


    @GetMapping("/undergoingadoptions")
    public String showUndergoingAdoptions(@CookieValue(value = "adopterId", required = false) Long adopterId,
                                          Model model) throws Exception {
        model.addAttribute("undergoingAdoptionList", adopterService.findAllByStatusAndAdopterId(Status.Undergoing, adopterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getAdopterPage("adopterundergoingadoptions"));
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        return "adopterundergoingadoptions";
    }

    @GetMapping("/finishedadoptions")
    public String showFinishedAdoptions(@CookieValue(value = "adopterId", required = false) Long adopterId,
                                        Model model) throws Exception {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("undergoingAdoptionList", adopterService.findAllByStatusAndAdopterId(Status.Finished, adopterId));
        model.addAttribute("pageInfo", pageDataService.getAdopterPage("adopterfinishedadoptions"));
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        return "adopterfinishedadoptions";
    }
}


