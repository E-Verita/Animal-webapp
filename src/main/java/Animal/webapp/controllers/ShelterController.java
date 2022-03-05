package Animal.webapp.controllers;

import Animal.webapp.models.Shelter;
import Animal.webapp.models.UserLogin;
import Animal.webapp.models.enums.Region;
import Animal.webapp.services.PageDataService;
import Animal.webapp.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ShelterController {
    PageDataService pageDataService;
    ShelterService shelterService;

    @Autowired
    public ShelterController(PageDataService pageDataService, ShelterService shelterService) {
        this.pageDataService = pageDataService;
        this.shelterService = shelterService;
    }

    @GetMapping("/shelterlogin")
    public String showShelterLoginPage(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message,
            Model model
    ){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("shelterlogin"));
        model.addAttribute("availablePages", pageDataService.getPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "shelterlogin";
    }

    @PostMapping("/shelterlogin")
    public String handleShelterLogin(UserLogin userLogin) {
        try {
            Shelter shelter = shelterService.verifyShelter(userLogin);
            return "redirect:sheltermenu/" + shelter.getId();
        } catch (Exception exception) {
            return "redirect:shelterlogin?status=login_failed&message=" + exception.getMessage();
        }
    }

    @GetMapping("/sheltermenu/{shelterId}")
    public String showShelterMenu(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("sheltermenu"));
        model.addAttribute("availablePages", pageDataService.getPages());
        return "sheltermenu";
    }

    @GetMapping("/shelterregister")
    public String showShelterRegisterPage(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("shelterregister"));
        model.addAttribute("availablePages", pageDataService.getPages());
        model.addAttribute("shelter", new Shelter());
        model.addAttribute("region", Region.values());
        return "shelterregister";
    }

    @PostMapping("/shelterregister")
    public String processShelterRegisterPage(@ModelAttribute @Valid Shelter shelter) {
        try {
            shelterService.addShelter(shelter);
            return "redirect:shelterlogin?status=signup_success";
        } catch (Exception ex) {
            return "redirect:shelterregister?status=signup_failed&message=" + ex.getMessage();
        }
    }
}
