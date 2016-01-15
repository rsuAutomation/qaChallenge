package crowdcompass.pageobjects;

import crowdcompass.enums.StickyNav;
import framework.TestCaseFramework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Smart Features Page validation.
 */
public class SmartFeaturesPage extends HomePage {
    private String featuresUrl = "http://www.crowdcompass.com/app-features/";

    @FindBy(css = ".wrapper.wrap-hero.features")
    WebElement featuresHeroWrapper;

    @FindBy(css = "#stickynav")
    WebElement stickyNav;

    @FindBy(css = ".centered.darkgray")
    WebElement stickynavHeader;

    private String expectedStickynavHeaderTitle = "Smart features";

    /**
     * This method returns the Features Page URL
     * @return featuresUrl
     */
    public String getFeaturesUrl(){
        return featuresUrl;
    }

    public boolean verifyFeaturesLandingPage(){
        return featuresHeroWrapper.isDisplayed();
    }

    /**
     * Smart Features page contains a second Nav - a Sticky Nav
     * This method verifies the sticky Nav Menu and nav header
     * @return result
     */
    public boolean verifyStickNav(){
        return verifyStickNavHeader() &&
                verifyStickyNavMenu();
    }

    /**
     * Verifies the sticky nav header title
     * @return result
     */
    private boolean verifyStickNavHeader(){
        WebDriver driver = TestCaseFramework.DriverManager.getDriver();
       //Scroll Page to Smart Navigation
        scrollInToView(driver,0,1000);
        boolean result = stickyNav.isDisplayed();
        String actualStickynavTitle = stickynavHeader.getText();
        System.out.println("StickyNav Header ActualTitle: "+ actualStickynavTitle + " Expected :" + expectedStickynavHeaderTitle);
        result &= actualStickynavTitle.equals(expectedStickynavHeaderTitle);
        return result;
    }

    /**
     * Verifies the Nav Menu Title
     * @return result
     */
    private boolean verifyStickyNavMenu(){
        boolean result = true;
        WebDriver driver = TestCaseFramework.DriverManager.getDriver();

        for(StickyNav subNav : StickyNav.values()){
            String actualSubNavTitle = driver.findElement(subNav.getSubNavButtonLocator()).getText();
            String expectedNavTitle = subNav.getSubNavButton().toUpperCase();

            System.out.println("Verify Header Nav Title: Actual: " + actualSubNavTitle +
                    " Expected: " + expectedNavTitle);
            result &= actualSubNavTitle.contains(expectedNavTitle);
        }
        return result;
    }

    /**
     * Verifies Sticky Nav Button,
     * Clicks the Button and verifies the page scroll and active button control
     * @return result
     */
    public boolean verifyStickNavButtons(){
        WebDriver driver = TestCaseFramework.DriverManager.getDriver();
        //Scroll Page to Smart Navigation
        scrollInToView(driver,0,1000);

        boolean result = true;
        for(StickyNav subNav : StickyNav.values()){
            waitForPageLoad(30);
            driver.findElement(subNav.getSubNavButtonLocator()).click();
            waitForPageLoad(30);
            result &= driver.findElement(subNav.getSubNavActiveButton()).isDisplayed();
            waitForPageLoad(30);
        }
        return result;
    }
}
