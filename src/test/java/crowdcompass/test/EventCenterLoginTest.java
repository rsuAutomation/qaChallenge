package crowdcompass.test;

import crowdcompass.pageobjects.EventCenterLoginPage;
import framework.TestCaseFramework;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Feature Tested: Event Center Login Page
 */
public class EventCenterLoginTest extends TestCaseFramework {
    /**
     * TestCase ID: TC03_HP_Login_Button_Validation
     * TestCase Description: Open Home Page,Verify presence of Login Button
     * Clicks on Login Button and verifies page navigation
     */
    @Test(groups = {GROUP_SMOKE})
    public void verifyEventCenterLogin(){
        EventCenterLoginPage loginPage = PageFactory.initElements(DriverManager.getDriver(), EventCenterLoginPage.class);
        DriverManager.getDriver().get(loginPage.getBaseUrl());

        Assert.assertTrue(loginPage.verifyLoginButton());
    }

    /**
     * TestCase ID: TC05_HP_Event_Center_Login_Form_Validation
     * TestCase Description: Verify the Event Center Login Form
     * Verifies Social Provider Accounts and Login form
     */
    @Test(groups = {GROUP_SMOKE})
    public void verifyEventCenterLoginForm(){
        EventCenterLoginPage loginPage = PageFactory.initElements(DriverManager.getDriver(), EventCenterLoginPage.class);
        DriverManager.getDriver().get(loginPage.getEventCenterUrl());

        Assert.assertTrue(loginPage.verifyLoginForm());
    }

    /**
     * TestCase Description: Verifies the Login Form-
     * Email and Password Input Field property and help Message
     */

    @Test(groups = {GROUP_REGRESSION})
    public void verifyRequiredLoginFields(){
        EventCenterLoginPage loginPage = PageFactory.initElements(DriverManager.getDriver(), EventCenterLoginPage.class);
        DriverManager.getDriver().get(loginPage.getEventCenterUrl());

        Assert.assertTrue(loginPage.verifyRequiredFields());
    }

    /**
     * TestCase ID - TC03_NP_Invalid_User_Login_Error_Messages
     * TestCase Description: Verify Login Form
     * Enter Invalid input, verify the Error Message
     */
    @Test(groups = {GROUP_REGRESSION})
    public void verifyInvalidUserLoginErrorMessage(){
        EventCenterLoginPage loginPage = PageFactory.initElements(DriverManager.getDriver(), EventCenterLoginPage.class);
        DriverManager.getDriver().get(loginPage.getEventCenterUrl());

        Assert.assertTrue(loginPage.verifyInvalidUserLogin());
    }
}
