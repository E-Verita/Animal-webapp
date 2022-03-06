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
    List<Page> volunteerPages = new ArrayList<>();
    List<Page> adopterPages = new ArrayList<>();
    List<Page> animalButtons = new ArrayList<>();


    public String getAppTitle() {
        return appTitle;
    }

    PageDataService() {
        availablePages.add(new Page("index", "Home", "", "/"));
        availablePages.add(new Page("shelterlogin", "Shelter Login", "","/shelter/login"));
        availablePages.add(new Page("sheltermenu", "Shelter Menu", "","/shelter/menu"));

        shelterPages.add(new Page("shelterprofile", "Profile", "","/shelter/profile"));
        shelterPages.add(new Page("shelteranimals", "Animals", "","/shelter/animals"));
        shelterPages.add(new Page("sheltervolunteers", "Volunteers", "","/shelter/volunteers"));

        animalButtons.add(new Page("shelteranimals-add", "Add Animal", "","/shelter/animals/add"));
        animalButtons.add(new Page("shelteranimals-edit", "Edit Animal", "","/shelter/animals/edit"));
        animalButtons.add(new Page("shelteranimals-delete", "Delete Animal", "","/shelter/animals/delete"));
        animalButtons.add(new Page("shelteranimals-seeall", "See All Animals", "","/shelter/animals/all"));
        animalButtons.add(new Page("shelteranimals-find", "Find Animal by ID", "","/shelter/animals/find"));


        availablePages.add(new Page("adopterlogin", "Adopter Login", "","/adopterlogin"));
        availablePages.add(new Page("adoptermenu", "Adopter Menu", "","/adoptermenu"));
        availablePages.add(new Page("volunteerlogin", "Volunteer Login", "","/volunteerlogin"));
        availablePages.add(new Page("volunteermenu", "Volunteer Menu", "","/volunteermenu"));
        availablePages.add(new Page("shelterregister", "Register a shelter", "","/shelterregister"));
        availablePages.add(new Page("adopterregister", "Register as an adopter", "","/adopterregister"));

        volunteerPages.add(new Page("volunteerprofile", "Profile", "","/volunteerprofile"));
        volunteerPages.add(new Page("volunteermessages", "Messages", "","/volunteermessages"));
        adopterPages.add(new Page("adopterprofile", "Profile", "","/adopterprofile"));
        adopterPages.add(new Page("adoptersearchpage", "Search for animal", "","/adoptersearchpage"));
        adopterPages.add(new Page("adopterundergoingadoptions", "Undergoing adoptions", "","/adopterundergoingadoptions"));
        adopterPages.add(new Page("adopterfinnishedadoptions", "Finished adoptions", "","/adopterfinnishedadoptions"));
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

    public Page getVolunteerPage(String pageName) {
        for (Page page : this.volunteerPages) {
            if (page.getName().equalsIgnoreCase(pageName)) return page;
        }
        return null;
    }

    public List<Page> getVolunteerPages() {
        return volunteerPages;
    }

    public Page getAdopterPage(String pageName) {
        for (Page page : this.adopterPages) {
            if (page.getName().equalsIgnoreCase(pageName)) return page;
        }
        return null;
    }

    public List<Page> getAdopterPages() {
        return adopterPages;
    }

    public Page getAnimalButton(String pageName) {
        for (Page page : this.animalButtons) {
            if (page.getName().equalsIgnoreCase(pageName)) return page;
        }
        return null;
    }

    public List<Page> getAnimalButtons() {
        return animalButtons;
    }
}


