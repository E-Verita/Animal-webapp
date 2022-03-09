package Animal.webapp.controllers;

import Animal.webapp.models.Adopter;
import Animal.webapp.models.UserLogin;
import Animal.webapp.models.enums.AdoptionStatus;
import Animal.webapp.models.enums.HousingType;
import Animal.webapp.services.AdopterService;
import Animal.webapp.services.AnimalService;
import Animal.webapp.services.PageDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

    @GetMapping("/adopterlogin")
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

    @PostMapping("/adopterlogin")
    public String handleAdopterLogin(UserLogin userLogin, HttpServletResponse response)
    {
        try {
            Adopter adopter = adopterService.verifyAdopter(userLogin);
            adopterService.setCookie(response, adopter.getId());
            return "redirect:adoptermenu/" + adopter.getId();
        } catch (Exception exception) {
            return "redirect:adopterlogin?status=login_failed&message=" + exception.getMessage();
        }
    }

    @GetMapping("/adoptermenu/{adopterId}")
    public String showAdopterMenu(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("adoptermenu"));
        model.addAttribute("availablePages", pageDataService.getAdopterPages());
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

    @GetMapping("/adopterprofile")
    public String showAdopterProfile(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getAdopterPage("adopterprofile"));
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        return "adopterprofile";
    }

    @GetMapping("/adoptersearchpage")
    public String searchForAnimals(@CookieValue(value = "adopterId", required = false) Long adopterId, Model model) throws Exception {
        model.addAttribute("animalList", adopterService.findAllAnimalsAvailableForAdoption(AdoptionStatus.Available));
        model.addAttribute("adopter", adopterService.getAdopter(adopterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getAdopterPage("adoptersearchpage"));
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        System.out.println("GET MAPPING");
        return "adoptersearchpage";
    }

    @GetMapping("/adopterundergoingadoptions")
    public String showUndergoingAdoptions(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getAdopterPage("adopterundergoingadoptions"));
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        return "adopterundergoingadoptions";
    }

    @GetMapping("/adopterfinnishedadoptions")
    public String showFinnishedAdoptions(Model model) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getAdopterPage("adopterfinnishedadoptions"));
        model.addAttribute("adopterPages", pageDataService.getAdopterPages());
        return "adopterfinnishedadoptions";
    }
}


