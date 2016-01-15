package crowdcompass.test;

import crowdcompass.enums.HeaderNav;
import crowdcompass.pageobjects.SmartFeaturesPage;
import framework.TestCaseFramework;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Feature Tested: Smart Features App.
 */
public class SmartFeaturesTest extends TestCaseFramework {

    /**
     *
     * TestCase Description: Open Home Page, Click on Features
     * Verify page navigation to Smart Features App
     */
    @Test(groups = {GROUP_REGRESSION})
    public void verifyNavigationToSmartFeatures(){
        SmartFeaturesPage featuresPage = PageFactory.initElements(DriverManager.getDriver(), SmartFeaturesPage.class);
        DriverManager.getDriver().get(featuresPage.getBaseUrl());
        featuresPage.navigatePage(HeaderNav.FEATURES);
        Assert.assertTrue(featuresPage.verifyFeaturesLandingPage());
    }

    /**
     * TestCase ID: TC14_HP_Smart_Feature_Navigation
     * TestCase Description: Open Smart Feature URL
     * Verify the presence of Sticky Nav
     * Verify Title and StickyNav links
     */

    @Test(groups = {GROUP_REGRESSION})
    public void verifyStickyNav(){
        SmartFeaturesPage featuresPage = PageFactory.initElements(DriverManager.getDriver(), SmartFeaturesPage.class);
        DriverManager.getDriver().get(featuresPage.getFeaturesUrl());
        Assert.assertTrue(featuresPage.verifyStickNav());
    }

    /**
     * TestCase ID: TC14_HP_Smart_Feature_Navigation
     * TestCase Description: Open Smart Feature URL
     * Verify the presence of Sticky Nav
     * Verify StickyNav Button transition and page scroll feature
     */
    @Test(groups = {GROUP_REGRESSION})
    public void verifyStickyNavButtons(){
        SmartFeaturesPage featuresPage = PageFactory.initElements(DriverManager.getDriver(), SmartFeaturesPage.class);
        DriverManager.getDriver().get(featuresPage.getFeaturesUrl());
        Assert.assertTrue(featuresPage.verifyStickNavButtons());
    }

}
