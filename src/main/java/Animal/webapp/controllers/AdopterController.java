package Animal.webapp.controllers;

import Animal.webapp.models.Adopter;
import Animal.webapp.models.UserLogin;
import Animal.webapp.models.enums.HousingType;
import Animal.webapp.services.AdopterService;
import Animal.webapp.services.PageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    public String showAdopterLoginPage(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message,
            Model model
    ){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("adopterlogin"));
        model.addAttribute("status", status);
        model.addAttribute("message", message);
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
        return "adoptermenu";
    }

    @GetMapping("/adopterregister")
    public String showAdopterRegisterPage(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("adopterregister"));
        model.addAttribute("adopter", new Adopter());
        model.addAttribute("housingType", HousingType.values());
        return "adopterregister";
    }

    @PostMapping("/adopterregister")
    public String processAdopterRegisterPage(@ModelAttribute @Valid Adopter adopter) {
        try {
            adopterService.addAdopter(adopter);
            return "redirect:adopterlogin?status=signup_success";
        } catch (Exception ex) {
            return "redirect:adopterregister?status=signup_failed&message=" + ex.getMessage();
        }
    }

}


