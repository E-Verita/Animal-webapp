package Animal.webapp.controllers;

import Animal.webapp.models.Adoption;
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

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/shelter")
@Controller
public class ShelterController {
    PageDataService pageDataService;
    ShelterService shelterService;
    AnimalService animalService;

    @Autowired
    public ShelterController(PageDataService pageDataService, ShelterService shelterService, AnimalService animalService) {
        this.pageDataService = pageDataService;
        this.shelterService = shelterService;
        this.animalService = animalService;
    }

    @GetMapping("/login")
    public String showShelterLoginPage(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "message", required = false) String message,
            Model model
    ) {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getPage("shelterlogin"));
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "shelterlogin";
    }

    @PostMapping("/login")
    public String handleShelterLogin(UserLogin userLogin, HttpServletResponse response) {  //??
        try {
            Shelter shelter = shelterService.verifyShelter(userLogin);
            shelterService.setCookie(response, shelter.getId());
//
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
    public String showShelterProfile(@CookieValue(value = "shelterId", required = false) Long shelterId, Model model) throws Exception {  //?
        model.addAttribute("shelter", shelterService.getShelter(shelterId)); //?
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
        model.addAttribute("animalButtons", pageDataService.getAnimalButtons());
        return "shelteranimals";

    }

    @GetMapping("/animals/add")
    public String addAnimals(@RequestParam(name = "status", required = false) String status,
                             @RequestParam(name = "message", required = false) String message,
                             Model model
    ) {
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

    public String processAddingAnimal(@CookieValue(value = "shelterId", required = false) Long shelterId, @ModelAttribute Animal animal) {
        try {
            animal.setShelter(shelterService.getShelter(shelterId));
            animalService.addAnimal(animal);
            return "redirect:add?status=animal_adding_successful";
        } catch (Exception exception) {
            exception.getStackTrace();
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
    public String findAnimalToDelete(@RequestParam(name = "status", required = false) String status,
                                     @RequestParam(name = "message", required = false) String message,
                                     @CookieValue(value = "shelterId", required = false) Long shelterId,
                                     Model model) throws Exception {
        model.addAttribute("shelter", shelterService.getShelter(shelterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        System.out.println(" @GetMapping     (animals/delete");
        return "shelteranimals-delete";
    }

    @PostMapping("/animals/delete")
    public String processFindAnimalToDelete(@CookieValue(value = "shelterId", required = false) Long shelterId,
                                            Long id)
            throws Exception {
        try {
            Animal animal = animalService.findByIdAndShelter(id, shelterId);
            System.out.println(animal.getName());
            System.out.println(" @PostMapping   animals/delete      out of try-catch");
            return "redirect:delete/confirm?status=animal_to_DELETE_found" + "&animalId=" + animal.getId();

        } catch (Exception ex) {
            System.out.println(" @PostMapping   animals/delete   in try-catch");
            return "redirect:delete?status=animal_finding_failed";
        }
    }

    @GetMapping("/animals/delete/confirm")
    public String deleteAnimals(@RequestParam(name = "status", required = false) String status,
                                @RequestParam(name = "message", required = false) String message,
                                @CookieValue(value = "shelterId", required = false) Long shelterId,
                                @RequestParam(value = "animalId", required = false) Long animalId, Model model) throws Exception {
        model.addAttribute("animal", animalService.findAnimalById(animalId));
        model.addAttribute("shelter", shelterService.getShelter(shelterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        System.out.println("Animal id: " + animalId);
        System.out.println(" @Get Mapping   animals/delete/confirm   ");
        return "shelteranimals-delete-confirm";
    }

    @PostMapping("/animals/delete/confirm")
    public String confirmDeletingAnimal(@RequestParam(value = "animalId", required = false) Long animalId,
                                        @ModelAttribute Animal animal)
            throws Exception {
        try {
            System.out.println(animalId);
            animalService.deleteAnimal(animalId);
            System.out.println(" @Post Mapping   animals/delete/confirm  out try catch  ");
            return "redirect:?status=animal_deleted";  //!
        } catch (Exception ex) {
            System.out.println(" @Post Mapping   animals/delete/confirm   in try catch  ");
            return "redirect:?status=animal_delete_failed";
        }
    }

    @GetMapping("/animals/all")
    public String seeallAnimals(@RequestParam(name = "status", required = false) String status,
                                @RequestParam(name = "message", required = false) String message,
                                @CookieValue(value = "shelterId", required = false) Long shelterId,
                                Model model) throws Exception {
        model.addAttribute("animalList", animalService.findAllByShelterId(shelterId));
        model.addAttribute("shelter", shelterService.getShelter(shelterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        System.out.println("GET-MAPPING");
        return "shelteranimals-seeall";
    }

    @RequestMapping("/animals/all")
    public String processallAnimals(@RequestParam(name = "status", required = false) String status,
                                    @RequestParam(name = "message", required = false) String message,
                                    @CookieValue(value = "shelterId", required = false) Long shelterId,
                                    Model model) throws Exception {
        model.addAttribute("shelter", shelterService.getShelter(shelterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        System.out.println("REQUEST-MAPPING");
        return "shelteranimals-seeall";
    }


    @GetMapping("/animals/find")
    public String findAnimals(@RequestParam(name = "status", required = false) String status,
                              @RequestParam(name = "message", required = false) String message,
                              @CookieValue(value = "shelterId", required = false) Long shelterId,
                              @RequestParam(value = "animalId", required = false) Long animalId,
                              Model model) throws Exception {

        try {
            model.addAttribute("animal", animalService.findAnimalById(animalId));
            model.addAttribute("shelter", shelterService.getShelter(shelterId));

        } catch (Exception ex) {
            status = status == null ? "animal_finding_failed" : status;
            message = message == null ? "Could not find animal" : message;

        }
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        System.out.println("Animal id: " + animalId);
        return "shelteranimals-find";

    }


    @PostMapping("/animals/find")
    public String processFindingAnimals(@CookieValue(value = "shelterId", required = false) Long shelterId,
                                        Long id, @ModelAttribute Animal animal, HttpServletResponse response)
            throws Exception {
        try {
            animal = animalService.findByIdAndShelter(id, shelterId);
            System.out.println(animal.getName());
            return "redirect:find?status=animal_finding_successful&name=" + animal.getName() + "&animalId=" + animal.getId();
        } catch (Exception ex) {
            return "redirect:find?status=animal_finding_failed";
        }
    }


    @GetMapping("/adoptions")
    public String showShelterAdoptionMenu(Model model) throws Exception {
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelter-adoptions"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("adoptionPages", pageDataService.getAdoptionPages());
        return "shelter-adoptions";
    }

    @GetMapping("/adoptions/undergoing")
    public String showUndergoingAdoptions(@RequestParam(name = "status", required = false) String status,
                                          @RequestParam(name = "message", required = false) String message,
                                          @CookieValue(value = "shelterId", required = false) Long shelterId,
                                          Model model) throws Exception {
        System.out.println(shelterId);
        model.addAttribute("adoptionList", animalService.findAllAdoptionsByStatusAndShelterId(shelterId, Status.UNDERGOING));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelter-adoptions"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("adoptionPages", pageDataService.getAdoptionPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "shelter-adoptions-undergoing";
    }

    @PostMapping("/adoptions/undergoing")
    public String processUndergoingGoingAdoption(Long id) throws Exception {
        try {
            Animal animal = animalService.findAnimalById(id);
            return "redirect:undergoing/confirm?status=animal_to_adopt_found" + "&animalId=" + animal.getId();

        } catch (Exception ex) {
            return "redirect:undergoing?status=animal_finding_failed";
        }
    }

    @GetMapping("/adoptions/undergoing/confirm")
    public String confirmAdoption(@RequestParam(name = "status", required = false) String status,
                                  @RequestParam(name = "message", required = false) String message,
                                  @CookieValue(value = "shelterId", required = false) Long shelterId,
                                  @RequestParam(value = "animalId", required = false) Long animalId, Model model) throws Exception {
        model.addAttribute("adoption", animalService.findByAnimalId(animalId));
        model.addAttribute("animal", animalService.findAnimalById(animalId));
        model.addAttribute("shelter", shelterService.getShelter(shelterId));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelteranimals"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("status", status);
        model.addAttribute("message", message);
        return "shelter-adoptions-undergoing-confirm";
    }

    //            Adoption adoption = animalService.findByAnimalId(animalId);
    @PostMapping("/adoptions/undergoing/confirm")
    public String processConfirmAdoption(@RequestParam(value = "animalId", required = false) Long animalId,
                                         @RequestParam(value = "sheltersText", required = false) String shelterText)
//                                         @RequestParam(value = "animalId", required = false) Long animalId)
            throws Exception {
        try {
            Adoption adoption = animalService.findByAnimalId(animalId);
            animalService.processAdoption(adoption, shelterText, AdoptionStatus.Adopted, Status.FINNINSHED, animalId);
//           System.out.println(animalId + " ADOPTED!");
            System.out.println(adoption);
            System.out.println("Adoption id:" + adoption.getId() + " text:" + adoption.getSheltersText());
//            TODO Business logic
            return "redirect:?status=animal_adopted";
        } catch (Exception ex) {
            System.out.println(" @Post Mapping   animals/delete/confirm   in try catch  ");
            return "redirect:?undergoing?status=animal_adoption_failed";
        }
    }


    @GetMapping("/adoptions/finished")
    public String showFinishedAdoptions( @CookieValue(value = "shelterId", required = false) Long shelterId,
                                         Model model) {
        model.addAttribute("adoptionList", animalService.findAllAdoptionsByStatusAndShelterId(shelterId, Status.FINNINSHED));
        model.addAttribute("appTitle", pageDataService.getAppTitle());
        model.addAttribute("pageInfo", pageDataService.getShelterPage("shelter-adoptions"));
        model.addAttribute("shelterPages", pageDataService.getShelterPages());
        model.addAttribute("adoptionPages", pageDataService.getAdoptionPages());
        return "shelter-adoptions-finished";
    }

}
