package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

public class RemoveContactTests extends AppiumConfig {
    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("bqa@mail.ru").password("bQa$1234").build())
                .submitLogin()
                .isContactListActivityDisplayed();
    }

    @BeforeMethod
    public void providerContacts() {
        //providerContactData: check count of contacts & if count<2 --->add 2 contacts
    }

    @Test
    public void removeOneContactSuccess() {
        int result = new ContactListScreen(driver)
                .removeOneContact(2);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void removeOneContactSuccessAssert() {
        new ContactListScreen(driver)
                .removeOneContactAssert(2);
    }
    @Test
    public void removeOneContactSuccess1() {
        new ContactListScreen(driver)
                .removeOneContactBase(1)
                .isListSizeLessOne();
    }

    @Test
    public void removeAllContacts() {
        new ContactListScreen(driver)
                .removeAllContact();
               // .isNoContactHere();
    }
}
