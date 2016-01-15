package crowdcompass.enums;

import org.openqa.selenium.By;

/**
 * Created by 370797 on 1/13/2016.
 */
public enum HeaderNav {
    FEATURES ("Features", "app-features/", "features"),
    RESOURCES ("Resources", "resources/", "resources"),
    OUR_CLIENTS ("Our Clients", "clients.shtml", "clients"),
    ABOUT_US ("About Us", "about.shtml", "about");

    final String navTitle;
    final String navLink;
    final String navButtonClass;
    private HeaderNav(String navTitle, String navLink, String navButtonClass) {
        this.navTitle = navTitle;
        this.navLink = navLink;
        this.navButtonClass = navButtonClass;
    }

    public String getNavTitle(){
        return navTitle;
    }

    public String getNavLink(){
        return "/"+navLink;
    }

    public By getNavButtonLocator(){
        return By.cssSelector("#header-nav ." + navButtonClass);
    }
}
