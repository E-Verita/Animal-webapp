package Animal.webapp.controllers;

import Animal.webapp.models.UserLogin;
import Animal.webapp.models.Volunteer;
import Animal.webapp.services.PageDataService;
import Animal.webapp.services.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VolunteerController {
    PageDataService pageDataService;
    VolunteerService volunteerService;

    @Autowired
    public VolunteerController(PageDataService pageDataService, VolunteerService volunteerService) {
        this.pageDataService = pageDataService;
        this.volunteerService = volunteerService;
    }

    @GetMapping("/volunteerlogin")
    public String showVolunteerLoginPage(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message,
            Model model
    ){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("volunteerlogin"));
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "volunteerlogin";
    }

    @PostMapping("/volunteerlogin")
    public String handleVolunteerLogin(UserLogin userLogin){
        try{
            Volunteer volunteer = volunteerService.verifyVolunteer(userLogin);
            return "redirect:volunteermenu/" + volunteer.getId();
        }catch (Exception exception){
            return "redirect:volunteerlogin?status=login_failed&message="+exception.getMessage();
        }
    }

    @GetMapping("/volunteermenu/{volunteerId}")
    public String showVolunteerMenu(Model model){
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("volunteermenu"));
        return "volunteermenu";
    }
}
