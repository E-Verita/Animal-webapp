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

    public String getAppTitle() {
        return appTitle;
    }

    PageDataService() {
        availablePages.add(new Page("index", "Home", "Homepage", "/"));
        availablePages.add(new Page("shelterlogin", "Shelter Login", "Shelter Login","/shelterlogin"));
        availablePages.add(new Page("sheltermenu", "Shelter Menu", "Shelter Menu","/sheltermenu"));
        availablePages.add(new Page("adopterlogin", "Adopter Login", "Adopter Login","/adopterlogin"));
        availablePages.add(new Page("adoptermenu", "Adopter Menu", "Adopter Menu","/adoptermenu"));
        availablePages.add(new Page("volunteerlogin", "Volunteer Login", "Volunteer Login","/volunteerlogin"));
        availablePages.add(new Page("volunteermenu", "Volunteer Menu", "Volunteer Menu","/volunteermenu"));

    }

    public Page getPage(String pageName) {
        for (Page page : this.availablePages) {
            if (page.getName().equalsIgnoreCase(pageName)) return page;
        }
        return null;
    }

    public List<Page> getPages() {
        return availablePages;
    }
}
