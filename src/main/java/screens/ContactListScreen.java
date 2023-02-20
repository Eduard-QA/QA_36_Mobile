package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.List;

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

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement plusButton;
    @FindBy(id="com.sheygam.contactapp:id/rowName")
    List<MobileElement> nameList;
    @FindBy(id="com.sheygam.contactapp:id/rowPhone")
    List<MobileElement> phoneList;

    private void checkContacts(List<MobileElement> list,String text){

        boolean isPresent = false;
        for(MobileElement el:list){
            if( el.getText().contains(text)){
                isPresent = true;
                break;
            }
        }
        Assert.assertTrue(isPresent);
    }

    public ContactListScreen isContactAddedByNameLastName(String name, String lastName){
        isShouldHave(activityTextView,"Contact list",20);
        System.out.println(nameList.size());
        checkContacts(nameList,name+" "+lastName);
        return this;
    }

    public ContactListScreen isContactAddedByPhone(String phone){
        isShouldHave(activityTextView,"Contact list",10);
        checkContacts(phoneList,phone);
        return this;
    }

    public AddNewContactScreen openContactForm(){
        plusButton.click();
        return new AddNewContactScreen(driver);
    }


    public boolean isContactListActivityDisplayed (){
        return isShouldHave(activityTextView,"Contact list",30);
    }

    public AuthenticationScreen logout(){

        if(activityTextView.getText().equals("Contact list")) {
            moreOption.click();
            should(logoutButton, 10);
            logoutButton.click();
        }

        return new AuthenticationScreen(driver);
    }
}