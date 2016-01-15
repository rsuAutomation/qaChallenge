package crowdcompass.enums;

import org.openqa.selenium.By;

/**
 * Created by 370797 on 1/14/2016.
 */
public enum SocialShare {
    LINKEDIN ("LinkedIn"),
    TWITTER ("Twitter"),
    FACEBOOK ("Facebook"),
    GOOGLE_OAUTH2 ("Google");

    final String socialAccountTitle;
    private SocialShare(String socialAccountTitle){
        this.socialAccountTitle = socialAccountTitle;
    }

    public String getSocialAccountTitle(){
        return socialAccountTitle;
    }

    public By getSocialAccountLocator(){
        return By.cssSelector(".provider."+this.name().toLowerCase());
    }
}
