package tests;

import config.AppiumConfig;
import models.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;


public class LoginTests extends AppiumConfig {

    @Test
    public void loginSuccess(){

        boolean res = new AuthenticationScreen(driver)
                .fillEmail("aqa@mail.ru")
                .fillPassword("aQa$1234")
                .submitLogin()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);
        // logout

    }

    @Test
    public void loginSuccessModel(){
        Auth auth = Auth.builder().email("aqa@mail.ru").password("aQa$1234").build();

        boolean res = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(auth)
                . submitLogin()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);
    }
    @Test
    public void loginWrongEmail(){
        Auth auth = Auth.builder().email("aqamail.ru").password("aQa$1234").build();

        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(auth)
                .submitLoginNegative()
                .isErrorMessageContainsText("Login or Password incorrect");
    }


    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver)
                .logout();

    }
}
