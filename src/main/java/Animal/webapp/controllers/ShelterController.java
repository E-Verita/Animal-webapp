package Animal.webapp.controllers;

import Animal.webapp.models.Shelter;
import Animal.webapp.models.UserLogin;
import Animal.webapp.models.enums.Region;
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
            return "login?status=login_failed&message=" + exception.getMessage();
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
            return "redirect:shelter/login?status=signup_success";
        } catch (Exception ex) {
            return "redirect:shelter/register?status=signup_failed&message=" + ex.getMessage();
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
        return "shelteranimals";
    }
    @GetMapping("/volunteers")
    public String showShelterVolunteerMenu(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("sheltervolunteers"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        return "sheltervolunteers";
    }
}
