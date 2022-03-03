package Animal.webapp.controllers;

import Animal.webapp.models.Shelter;
import Animal.webapp.models.UserLogin;
import Animal.webapp.services.PageDataService;
import Animal.webapp.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String showShelterLoginPage(Model model){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("shelterlogin"));
        model.addAttribute("availablePages", pageDataService.getPages());
        return "shelterlogin";
    }

    @PostMapping("/shelterlogin")
    public String handleShelterLogin(UserLogin userLogin){
        try{
            Shelter shelter = shelterService.verifyShelter(userLogin);
            return "redirect:sheltermenu/" + shelter.getId();
        }catch (Exception exception){
            return "redirect:shelterlogin?status=login_failed&message="+exception.getMessage();
        }
    }

    @GetMapping("/sheltermenu/{shelterId}")
    public String showShelterMenu(Model model){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("sheltermenu"));
        model.addAttribute("availablePages", pageDataService.getPages());
        return "sheltermenu";
    }

}
