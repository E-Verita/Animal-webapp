package Animal.webapp.controllers;

import Animal.webapp.models.Animal;
import Animal.webapp.models.Shelter;
import Animal.webapp.models.UserLogin;
import Animal.webapp.models.enums.*;
import Animal.webapp.services.AnimalService;
import Animal.webapp.services.PageDataService;
import Animal.webapp.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RequestMapping("/shelter")
@Controller
public class ShelterController {
    PageDataService pageDataService;
    ShelterService shelterService;
    AnimalService animalService;

    @Autowired
    public ShelterController(PageDataService pageDataService, ShelterService shelterService) {
        this.pageDataService = pageDataService;
        this.shelterService = shelterService;
    }

    @GetMapping("/login")
    public String showShelterLoginPage(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message,
            Model model
    ){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("shelterlogin"));
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "shelterlogin";
    }

    @PostMapping("/login")
    public String handleShelterLogin(UserLogin userLogin) {
        try {
            Shelter shelter = shelterService.verifyShelter(userLogin);
            return "redirect:menu/" + shelter.getId();
        } catch (Exception exception) {
            return "redirect:login?status=login_failed&message=" + exception.getMessage();
        }
    }

    @GetMapping("/menu/{shelterId}")
    public String showShelterMenu(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("sheltermenu"));
        model.addAttribute("availablePages", pageDataService.getShelterPages());
        return "sheltermenu";
    }

    @GetMapping("/register")
    public String showShelterRegisterPage(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("shelterregister"));
        model.addAttribute("shelter", new Shelter());
        model.addAttribute("region", Region.values());
        return "shelterregister";
    }

    @PostMapping("/register")
    public String processShelterRegisterPage(@ModelAttribute @Valid Shelter shelter) {
        try {
            shelterService.addShelter(shelter);
            return "redirect:login?status=signup_success";
        } catch (Exception ex) {
            return "redirect:register?status=signup_failed&message=" + ex.getMessage();
        }
    }

    @GetMapping("/profile")
    public String showShelterProfile(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelterprofile"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        return "shelterprofile";
    }

    @GetMapping("/animals")
    public String showShelterAnimalMenu(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("animalButtons",pageDataService.getAnimalButtons());
        return "shelteranimals";

    }
    @GetMapping("/animals/add")
    public String addAnimals(       @RequestParam(name = "status", required = false) String status,
                                    @RequestParam(name = "message", required = false) String message,
                                    Model model
    ){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("animal", new Animal());
        model.addAttribute("type", Type.values());
        model.addAttribute("ageGroup", AgeGroup.values());
        model.addAttribute("housingType", HousingType.values());
        model.addAttribute("adoptionStatus", AdoptionStatus.values());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "shelteranimals-add";
    }

    @PostMapping("/animals/add")
    public String processAddingAnimal(@ModelAttribute @Valid Animal animal){
        try{
            animalService.addAnimal(animal);
            return "redirect:add?status=animal_adding_successful";
        }catch (Exception exception){
            return "redirect:add?status=animal_adding_failed";
        }
    }
    @GetMapping("/animals/edit")
    public String editAnimals(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        return "shelteranimals-edit";
    }
    @GetMapping("/animals/delete")
    public String deleteAnimals(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        return "shelteranimals-delete";

    }   @GetMapping("/animals/all")
    public String seeallAnimals(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        return "shelteranimals-seeall";

    }   @GetMapping("/animals/find")
    public String findAnimals(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        return "shelteranimals-find";
    }


    @GetMapping("/volunteers")
    public String showShelterVolunteerMenu(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("sheltervolunteers"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        return "sheltervolunteers";
    }
}
