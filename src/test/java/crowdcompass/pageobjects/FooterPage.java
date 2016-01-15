package crowdcompass.pageobjects;

import crowdcompass.enums.FooterLinks;
import framework.TestCaseFramework;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Footer Links and Footer Socials.
 */
public class FooterPage extends HomePage {
    /**
     * Footer Social Accounts
     */
    enum FooterSocial{
        FACEBOOK    ("Facebook"),
        TWITTER     ("Twitter"),
        LINKEDIN    ("LinkedIn"),
        GOOGLE_PLUS ("Google+");

        final String social;
        FooterSocial(String social){
            this.social = social;
        }
        public String getSocial(){
            return social;
        }
    }

    @FindBy (id = "footer")
    WebElement footer;

    @FindBy (css = "#footer .hide-for-small ul li a")
    List<WebElement> footerColumnLinks;

    @FindBy (css = ".social.home-footer li a")
    List<WebElement> footerSocialLocator;

    /**
     * Verifies the presence of Footer Links
     * @return result
     */
    public boolean verifyFooterLinks(){
        boolean result = footer.isDisplayed();
        WebDriver driver = TestCaseFramework.DriverManager.getDriver();
        scrollInToView(driver,0,3000);

        int index =0;
        for(FooterLinks links : FooterLinks.values()){
            String actualFooterLinkTitle = footerColumnLinks.get(index).getText().toLowerCase();
            String expectedFooterLinkTitle = links.name().replaceAll("_"," ").toLowerCase();
            System.out.println("Actual Footer Link :" + actualFooterLinkTitle+" Expected Link:"+expectedFooterLinkTitle);
            result &= actualFooterLinkTitle.equals(expectedFooterLinkTitle);
            index++;
        }
        return result;
    }

    /**
     * Verifies Social Accounts on Footer
     * @return result
     */
    public boolean verifyFooterSocials(){
        boolean result = true;
        int index=0;
        System.out.println("Verify Footer Socials");
        for(FooterSocial social : FooterSocial.values()){
            String actualSocialAccount = footerSocialLocator.get(index).getText();
            String expectedSocialAccount = social.getSocial();
            System.out.println("Actual : " + actualSocialAccount + " Expected: " + expectedSocialAccount);
            result &= actualSocialAccount.equals(expectedSocialAccount);
            index++;
        }
        return result;
    }

}
