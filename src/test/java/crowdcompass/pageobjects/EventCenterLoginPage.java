package crowdcompass.pageobjects;

import crowdcompass.enums.SocialShare;
import framework.TestCaseFramework;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Event Center Login Form Verification.
 */
public class EventCenterLoginPage extends HomePage{
    public final String eventCenterLoginUrl = "https://eventcenter.crowdcompass.com/users/sign_in";
    private final String expectedLoginTitle = "Login to the CrowdCompass EventCenter";
    private String loginAlertMessage = "You must log in to access the Event Center.";
    private String requiredFieldErrorMessage = "This field is required";
    private String invalidEmailErrorMessage = "Please enter a valid email address.";

    By homePageLoginButtonIcon = By.cssSelector(".login.block a");

    @FindBy(css = "alert.notice")
    WebElement loginAlertNotice;

    @FindBy(css = ".login-wrap")
    WebElement loginForm;

    @FindBy(id = "user_email")
    WebElement emailField;

    @FindBy(id = "user_password")
    WebElement passwordField;

    @FindBy(css = "[name='commit']")
    WebElement loginButton;

    @FindBy (css = "#hub-container > a")
    List<WebElement> loginFormHyperLinks;

    @FindBy (id = "user_email-error")
    WebElement userEmailError;

    @FindBy (id = "user_password-error")
    WebElement userPasswordError;

    /**
     * This method returns the Event Center Login Page URL
     * @return eventCenterLoginUrl
     */
    public String getEventCenterUrl(){
        return eventCenterLoginUrl;
    }

    /**
     * Verifies the Presence of Login Button on HomePage and
     * verifies Login Button is clickable.
     * Performs Click Action and verifies successful Page navigation
     * @return result
     */
    public boolean verifyLoginButton(){
        WebDriver driver = TestCaseFramework.DriverManager.getDriver();
        WebElement loginIcon = driver.findElement(homePageLoginButtonIcon);
        boolean result = loginIcon.isDisplayed();
        String actualLoginTitle = loginIcon.getAttribute("title");
        System.out.println("Verify Login Button Title ActualTitle :" + actualLoginTitle + " Expected :" +expectedLoginTitle);
        result &= expectedLoginTitle.equals(actualLoginTitle);
        if(elementClickable(driver,homePageLoginButtonIcon)){
            waitForPageLoad(30);
            loginIcon.click();
            waitForPageLoad(30);
            String actualLoginUrl = driver.getCurrentUrl();
            System.out.println("Verify Login URL Actual :" + actualLoginUrl + " Expected :" +eventCenterLoginUrl);
            waitForPageLoad(30);
            result &= actualLoginUrl.equals(eventCenterLoginUrl);
        }
        return result;
    }

    /**
     * Verifies the Event Center Login input field Email and Password,
     * verifies the Forgot Password link and confirmation instruction link.
     * Validates the presence of Social Provider Accounts
     * @return result
     */
    public boolean verifyLoginForm(){
        System.out.println("Verify Login Form Fields");
        boolean result = loginForm.isDisplayed();
        result &= emailField.isDisplayed();
        result &= passwordField.isDisplayed();
        result &= loginButton.isDisplayed();
        result &= loginButton.isEnabled();
        result &= loginFormHyperLinks.get(0).isDisplayed();
        result &= loginFormHyperLinks.get(1).isDisplayed();

        System.out.println("Verify Social Provider Accounts on Login Form");
        WebDriver driver = TestCaseFramework.DriverManager.getDriver();
        for(SocialShare socialProvider : SocialShare.values()){
            WebElement socialAccount = driver.findElement(socialProvider.getSocialAccountLocator());
            result &= socialAccount.isDisplayed();
            String actualSocialAccountTitle = socialAccount.getText();
            String expectedSocialAccountTitle = socialProvider.getSocialAccountTitle();
            System.out.println("Social Provider Title: "+actualSocialAccountTitle + " Expected: "+expectedSocialAccountTitle);
            result &= actualSocialAccountTitle.equals(expectedSocialAccountTitle);
        }
        return result;
    }

    /**
     * Validates the presence of Alert Message
     * @return result
     */
    public boolean loginAlerts(){
        boolean result = true;
        String actualLoginAlertNoticeMessage = loginAlertNotice.getText();
        System.out.println("Verify Alert Message: Actual :" + actualLoginAlertNoticeMessage + " Expected: "+loginAlertMessage);
        result &= actualLoginAlertNoticeMessage.equals(loginAlertMessage);
        return result;
    }

    /**
     * Validates Login Form input fields are mandatory
     * Verifies error message is displayed when user tries to login with incomplete Login input
     * @return result
     */
    public boolean verifyRequiredFields(){
        System.out.println("Attempt To login Without Entering any Values in Login Form");
        loginButton.click();

        //User Email
        System.out.println("Verify User Email and Password are Required Fields");
        boolean result = Boolean.valueOf(emailField.getAttribute("aria-required"));
        result &= userEmailError.isDisplayed();
        String actualUserEmailErrorMessage = userEmailError.getText();
        System.out.println("Required Email Error Message :" + actualUserEmailErrorMessage + " Expected: " + requiredFieldErrorMessage);

        //User Password
        result &= Boolean.valueOf(passwordField.getAttribute("aria-required"));
        result &= userPasswordError.isDisplayed();
        String actualUserPasswordErrorMessage = userPasswordError.getText();
        System.out.println("Required Password Error Message :" + actualUserPasswordErrorMessage + " Expected: " + requiredFieldErrorMessage);

        return result;
    }

    /**
     * Verifies error message is displayed when user tries to login with invalid Email
     * @return result
     */
    public boolean verifyInvalidUserLogin(){
        String invalidEmail = RandomStringUtils.randomAlphanumeric(20);
        System.out.println("Invalid Email : " + invalidEmail);

        emailField.click();
        emailField.sendKeys(invalidEmail);
        passwordField.click();
        String actualInvalidEmailError = userEmailError.getText();
        System.out.println("Invalid Email Error Message Actual: "+ actualInvalidEmailError + " Expected: "+invalidEmailErrorMessage);
        return actualInvalidEmailError.equals(invalidEmailErrorMessage);
    }



}
