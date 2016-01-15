package crowdcompass.test;

import crowdcompass.pageobjects.ContactUsPage;
import framework.TestCaseFramework;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Feature Tested: Contact US Form
 */
public class ContactUsTest extends TestCaseFramework{
    /**
     * TestCase ID- TC02_HP_UserCan_ContactUs
     * TestCase Description: Open Home Page, Verify the presence of Contact US Button
     * on top of the Page. Verify clicking on Contact US button navigates to Contact US Page
     */
    @Test(groups = {GROUP_SMOKE})
    public void verifyContactUsButtonIcon(){
        ContactUsPage contactsPage = PageFactory.initElements(DriverManager.getDriver(), ContactUsPage.class);
        DriverManager.getDriver().get(contactsPage.getBaseUrl());

        Assert.assertTrue(contactsPage.verifyContactUsIcon());
    }

    /**
     * TestCaseID- TC02_HP_UserCan_ContactUs
     * TestCase Description: Open Contact US Page
     * Verify the Contact US Form, validate Input fields and Submit Button
     */
    @Test(groups = {GROUP_SMOKE})
    public void verifyContactUsForm(){
        ContactUsPage contactsPage = PageFactory.initElements(DriverManager.getDriver(), ContactUsPage.class);
        DriverManager.getDriver().get(contactsPage.getContactUsUrl());

        Assert.assertTrue(contactsPage.verifyContactUsFormFields());
    }

    /**
     * TestCase ID- TC01_NP_ContactUs_Error_Messages
     * TestCase Description: Open Contact US Page,
     * Submit a incomplete form, verify the error message
     */
    @Test(groups = {GROUP_SMOKE})
    public void verifyIncompleteContactUsFormErrorMessage(){
        ContactUsPage contactsPage = PageFactory.initElements(DriverManager.getDriver(), ContactUsPage.class);
        DriverManager.getDriver().get(contactsPage.getContactUsUrl());

        Assert.assertTrue(contactsPage.verifyIncompleteContactUsFormNotAllowed());
    }
}
