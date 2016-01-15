package crowdcompass.test;

import crowdcompass.pageobjects.HomePage;
import framework.TestCaseFramework;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Feature Tested: Home Page Header.
 */
public class HomePageTest extends TestCaseFramework{
    /**
     * TestCase ID: TC10_HP_Header_Menu_Navigations
     * Test Description: Verify HeaderNav on Home Page, verifies the HomePage Logo
     * HeaderNav Title and Links. Clicks on the headerNav Links and verifies page Navigation
     */
    @Test(groups = {GROUP_SMOKE})
    public void verifyHeaderNavigation(){
        HomePage headerPage = PageFactory.initElements(DriverManager.getDriver(), HomePage.class);
        DriverManager.getDriver().get(headerPage.getBaseUrl());

        headerPage.verifyTopHeader();
    }

}
