package crowdcompass.pageobjects;

import crowdcompass.enums.HeaderNav;
import framework.PageFramework;
import framework.TestCaseFramework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Verifies Top Navigation Header title and Page redirects
 */
public class HomePage extends PageFramework {
    public final String baseUrl = "http://www.crowdcompass.com";
    private final String expectedTopLogoImage = "/images/crowdcompass-by-cvent-header.png";

    @FindBy(css = "#header-nav .logo-container img")
    public WebElement homePageLogo;

    @FindBy(css = "#header-nav .inline-list li a")
    public List<WebElement> headerNavList;

    /**
     * This method returns the HomePage URL
     * @return  baseUrl
     */
    public String getBaseUrl(){
        return baseUrl;
    }

    /**
     * Verifies the Top Header
     * @return result
     */
    public boolean verifyTopHeader(){
        boolean result = verifyHeaderNav();
        result &= verifyNavRedirects();
        return result;
    }

    /**
     * Verifies the Company Logo Image and Header Navigation Title
     * @return result
     */
    private boolean verifyHeaderNav(){
        boolean result = homePageLogo.isDisplayed();
        String actualTopLogo = homePageLogo.getAttribute("src").replace(baseUrl,"").trim();
        if (!expectedTopLogoImage.equalsIgnoreCase(actualTopLogo)) {
            System.out.println("Home Page Header Logo Not Found");
            result &= false;
        }

        int index = 0;
        for(HeaderNav nav : HeaderNav.values()) {
            String expectedNavTitle = headerNavList.get(index).getText();
            System.out.println("Verify Header Nav Title : " + expectedNavTitle);
            result &= nav.getNavTitle().equals(expectedNavTitle);
            index ++;
        }
        return result;
    }

    /**
     * Verifies Header Nav Links URL redirect
     * Clicks on the Logo and waits for PageLoad
     * @return result
     */
    private boolean verifyNavRedirects(){
        System.out.println("Verify Header Nav Redirect");
        boolean result = true;
        int index = 0;
        for(HeaderNav nav : HeaderNav.values()){
            String actualHeaderNavLink = headerNavList.get(index).getAttribute("href");
            String expectedHeaderNavLink = baseUrl + nav.getNavLink();
            System.out.println("ActualNavLink: " + actualHeaderNavLink + " ExpectedNavLink: " + expectedHeaderNavLink);
            result &= actualHeaderNavLink.equals(expectedHeaderNavLink);
            headerNavList.get(index).click();
            waitForLazyLoad(TestCaseFramework.DriverManager.getDriver());

            String redirectUrl = TestCaseFramework.DriverManager.getDriver().getCurrentUrl();
            result &= redirectUrl.equals(expectedHeaderNavLink);

            homePageLogo.click();
            waitForLazyLoad(TestCaseFramework.DriverManager.getDriver());

            index++;
        }
        return result;
    }

    /**
     * This method clicks on the header Nav Item
     * @param navLink
     */

    public void navigatePage(HeaderNav navLink){
        WebDriver driver = TestCaseFramework.DriverManager.getDriver();

        driver.findElement(navLink.getNavButtonLocator()).click();
        waitForLazyLoad(driver);
    }
}
