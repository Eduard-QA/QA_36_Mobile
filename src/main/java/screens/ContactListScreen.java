package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextVew;

    @FindBy(xpath = "//*[")
    MobileElement moreOption;

    @FindBy()
    MobileElement logOutButton;

    public AuthenticationScreen logOut(){
        moreOption.click();
        logOutButton.click();
        return new AuthenticationScreen (driver);
    }

    public boolean isContactListActivityDisplayed(){
        return isShouldHave(activityTextVew,"Contact list", 20);
    }
}
