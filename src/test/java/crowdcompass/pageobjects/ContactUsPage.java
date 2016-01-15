package crowdcompass.pageobjects;

import framework.TestCaseFramework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Feature Tested: Contact US
 */
public class ContactUsPage extends HomePage {
    String contactUsUrl = "http://www.crowdcompass.com/contact.shtml";

    By contactUsIcon = By.cssSelector(".contact.block");

    @FindBy (css = ".contact-form")
    WebElement contactUsForm;

    @FindBy (css = "[name='FirstName']")
    WebElement inputFieldFirstName;

    @FindBy (css = "[name='LastName']")
    WebElement inputFieldLastName;

    @FindBy (css = "[name='Company']")
    WebElement inputFieldOrganization;

    @FindBy (css = "[name='text']")
    WebElement inputFieldEmail;

    @FindBy (css = "[name='Phone']")
    WebElement inputFieldPhoneNumber;

    @FindBy (id = "comments")
    WebElement inputFieldComments;

    @FindBy (id = "submit-frm")
    WebElement submitButton;

    By requiredErrorClass = By.cssSelector(".required.error");

    /**
     * This method returns the Contact Us Page URL
     * @return contactUS Url
     */
    public String getContactUsUrl(){
        return contactUsUrl;
    }

    /**
     * Verifies the Presence of Contact US Button on HomePage and
     * verifies Contact US Button is clickable.
     * Performs Click Action and verifies successful Page navigation
     * @return result
     */
    public boolean verifyContactUsIcon(){
        WebDriver driver = TestCaseFramework.DriverManager.getDriver();
        System.out.println("Contact Us Icon on Home Page");
        boolean result = driver.findElement(contactUsIcon).isDisplayed();
        result &= elementClickable(driver, contactUsIcon);

        driver.findElement(contactUsIcon).click();
        waitForLazyLoad(driver);
        result &= contactUsForm.isDisplayed();

        return result;
    }

    /**
     * Verifies the Contact US Form Input fields
     * FirstName, LastName, Organization, Email, PhoneNumber and Comments
     * @return result
     */
    public boolean verifyContactUsFormFields(){
        System.out.println("Verify the Contact US Form");
        boolean result = contactUsForm.isDisplayed();
        result &= inputFieldFirstName.isDisplayed() && inputFieldFirstName.isEnabled();
        result &= inputFieldLastName.isDisplayed() && inputFieldLastName.isEnabled();
        result &= inputFieldOrganization.isDisplayed();
        result &= inputFieldEmail.isDisplayed();
        result &= inputFieldPhoneNumber.isDisplayed();
        result &= inputFieldComments.isDisplayed();
        result &= submitButton.isDisplayed() && submitButton.isEnabled();
        return result;
    }

    /**
     * Verifies the Contact US Form does not allow incomplete Form Submission
     * and verifies the error Message is displayed on incomplete form Submission
     * @return result
     */
    public boolean verifyIncompleteContactUsFormNotAllowed(){
        boolean result = submitButton.isDisplayed() && submitButton.isEnabled();
        //Click Empty Submit button
        submitButton.click();

        WebDriver driver = TestCaseFramework.DriverManager.getDriver();
        List<WebElement> requiredFields = driver.findElements(requiredErrorClass);
        List<WebElement> inputFields = driver.findElements(By.cssSelector(".form-input"));

        result &= requiredFields.size() == inputFields.size();
        for(WebElement reqInput : requiredFields){
            String reqFieldName = reqInput.getAttribute("id");
            System.out.println(reqFieldName  + " : is a required field");
        }

        return result;
    }
}
