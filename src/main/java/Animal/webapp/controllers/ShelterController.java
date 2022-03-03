package Animal.webapp.controllers;

import Animal.webapp.services.PageDataService;
import Animal.webapp.services.ShelterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
        model.addAttribute("pageInfo", pageDataService.getPage("Shelter Login"));
        model.addAttribute("availablePages", pageDataService.getPages());
        return "shelterlogin";
    }

}
