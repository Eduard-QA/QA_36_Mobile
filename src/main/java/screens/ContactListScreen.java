package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;

public class ContactListScreen extends BaseScreen{
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement activityTextView;

    @FindBy(xpath = "//*[@content-desc='More options']")
    MobileElement moreOption;
    @FindBy(id="com.sheygam.contactapp:id/title")
    MobileElement logoutButton;


    public boolean isContactListActivityDisplayed (){
        return isShouldHave(activityTextView,"Contact list",15);
    }

    public AuthenticationScreen logout(){
        moreOption.click();
        logoutButton.click();

        return new AuthenticationScreen(driver);
    }
}
