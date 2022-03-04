package Animal.webapp.controllers;

import Animal.webapp.models.Adopter;
import Animal.webapp.models.UserLogin;
import Animal.webapp.services.AdopterService;
import Animal.webapp.services.PageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdopterController {
    PageDataService pageDataService;
    AdopterService adopterService;

    @Autowired
    public AdopterController(PageDataService pageDataService, AdopterService adopterService) {
        this.pageDataService = pageDataService;
        this.adopterService = adopterService;
    }

    @GetMapping("/adopterlogin")
    public String showAdopterLoginPage(Model model){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("adopterlogin"));
        model.addAttribute("availablePages", pageDataService.getPages());
        return "adopterlogin";
    }

    @PostMapping("/adopterlogin")
    public String handleAdopterLogin(UserLogin userLogin){
        try{
            Adopter adopter = adopterService.verifyAdopter(userLogin);
            return "redirect:adoptermenu/" + adopter.getId();
        }catch (Exception exception){
            return "redirect:adopterlogin?status=login_failed&message="+exception.getMessage();
        }
    }

    @GetMapping("/adoptermenu/{adopterId}")
    public String showAdopterMenu(Model model){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("adoptermenu"));
        model.addAttribute("availablePages", pageDataService.getPages());
        return "adoptermenu";
    }

}


