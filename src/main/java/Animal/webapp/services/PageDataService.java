package Animal.webapp.services;

import Animal.webapp.models.Page;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Scope("singleton")
public class PageDataService {
    @Value("${animalappsgt.title}")
    String appTitle;
    List<Page> availablePages = new ArrayList<>();
    List<Page> shelterPages = new ArrayList<>();
    List<Page> adopterPages = new ArrayList<>();
    List<Page> animalButtons = new ArrayList<>();
    List<Page> adoptionPages = new ArrayList<>();
    List<Page> animalConfirmButtons = new ArrayList<>();


    public String getAppTitle() {
        return appTitle;
    }

    PageDataService() {
        availablePages.add(new Page("index", "Home", "", "/"));

        availablePages.add(new Page("shelterregister", "Register a shelter", "","/shelter/register"));
        availablePages.add(new Page("shelterlogin", "Shelter Login", "","/shelter/login"));


        shelterPages.add(new Page("shelterprofile", "Profile", "","/shelter/profile"));
        shelterPages.add(new Page("shelter-adoptions-undergoing", "Undergoing adoptions", "","/shelter/undergoingadoptions"));
        shelterPages.add(new Page("shelter-adoptions-finished", "Finished adoptions", "","/shelter/finishedadoptions"));

        animalButtons.add(new Page("shelteranimals-add", "Add Animal", "","/shelter/animals/add"));
//        animalButtons.add(new Page("shelteranimals-edit", "Edit Animal", "","/shelter/animals/edit"));
        animalButtons.add(new Page("shelteranimals-delete", "Delete Animal", "","/shelter/animals/delete"));
        animalButtons.add(new Page("shelteranimals-seeall", "See All Animals", "","/shelter/animals/all"));
        animalButtons.add(new Page("shelteranimals-find", "Find Animal by ID", "","/shelter/animals/find"));

        animalConfirmButtons.add(new Page("shelteranimals-find-results", "Find Animal: Results", "","/shelter/animals/find/results"));
        animalConfirmButtons.add(new Page("shelteranimals-delete-confirm", "Delete Animal - Confirmation", "","/shelter/animals/delete/confirm"));


        availablePages.add(new Page("adopterlogin", "Adopter Login", "","/adopter/login"));
        availablePages.add(new Page("adopterregister", "Adopter Register", "","/adopter/register"));

        adopterPages.add(new Page("adopterprofile", "Profile", "","/adopter/profile"));
        adopterPages.add(new Page("adoptersearchpage", "Search for animal", "","/adopter/search/"));
        adopterPages.add(new Page("adopterundergoingadoptions", "Undergoing adoptions", "","/adopter/undergoingadoptions"));
        adopterPages.add(new Page("adopterfinishedadoptions", "Finished adoptions", "","/adopter/finishedadoptions"));
    }

    public Page getPage(String pageName) {
        for (Page page : this.availablePages) {
            if (page.getName().equalsIgnoreCase(pageName)) return page;
        }
        return null;
    }
    public Page getShelterPage(String pageName) {
        for (Page page : this.shelterPages) {
            if (page.getName().equalsIgnoreCase(pageName)) return page;
        }
        return null;
    }

    public List<Page> getShelterPages() {
        return shelterPages;
    }


    public Page getAdopterPage(String pageName) {
        for (Page page : this.adopterPages) {
            if (page.getName().equalsIgnoreCase(pageName)) return page;
        }
        return null;
    }

    public Page getAnimalButton(String pageName) {
        for (Page page : this.animalButtons) {
            if (page.getName().equalsIgnoreCase(pageName)) return page;
        }
        return null;
    }

    public List<Page> getAnimalConfirmButtons() {
        return animalConfirmButtons;
    }

    public Page getAnimalConfirmButton(String pageName) {
        for (Page page : this.animalConfirmButtons) {
            if (page.getName().equalsIgnoreCase(pageName)) return page;
        }
        return null;
    }
    public List<Page> getAdopterPages() {
        return adopterPages;
    }

    public List<Page> getAnimalButtons() {
        return animalButtons;
    }

    public List<Page> getAdoptionPages() {
        return adoptionPages;
    }
}


