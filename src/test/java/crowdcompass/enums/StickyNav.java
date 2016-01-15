package crowdcompass.enums;

import org.openqa.selenium.By;
/**
 * Created by 370797 on 1/13/2016.
 */
public enum StickyNav {
    SMART_CONTENT ("Smart Content", "smartContentBtn"),
    SOCIAL ("Social", "socialBtn"),
    YEAR_ROUND_ROI ("Year-Round ROI", "roiBtn"),
    FLEXIBILITY ("Flexibility", "flexBtn"),
    SUPPORT ("Support", "supportBtn");

    final String subNavButtonTitle;
    final String subNavButtonLocator;

    private StickyNav(String subNavButtonTitle, String subNavButtonLocator){
        this.subNavButtonTitle = subNavButtonTitle;
        this.subNavButtonLocator = subNavButtonLocator;
    }

    public String getSubNavButton(){
        return subNavButtonTitle;
    }

    public By getSubNavButtonLocator(){
        return By.cssSelector(".subNavBtn."+subNavButtonLocator);
    }

    public By getSubNavActiveButton(){
        return By.cssSelector(".subNavBtn." + subNavButtonLocator +".active");
    }
}
