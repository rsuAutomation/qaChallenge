package crowdcompass.test;

import crowdcompass.pageobjects.FooterPage;
import framework.TestCaseFramework;
import junit.framework.Assert;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

/**
 * Feature Tested: Footer.
 */
public class FooterTest extends TestCaseFramework{
    /**
     * Testcase ID: TC17_HP_Footer_Links
     * TestCase Description: Open Home Page Scroll to the bottom of the Page,
     * Verify the Footer is displayed, verify Footer has social Accounts - Facebook, Twitter, LinkedIn and Google+
     * Verify FooterLink Title and URL
     */
    @Test(groups = {GROUP_SMOKE})
    public void verifyHeaderNavigation(){
        FooterPage footer = PageFactory.initElements(DriverManager.getDriver(), FooterPage.class);
        DriverManager.getDriver().get(footer.getBaseUrl());

        Assert.assertTrue(footer.verifyFooterLinks());
        Assert.assertTrue(footer.verifyFooterSocials());
    }
}
