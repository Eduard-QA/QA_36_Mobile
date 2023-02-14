package tests;

import config.AppiumConfig;
import model.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTests extends AppiumConfig {
    @Test
    public void LoginSuccess() {
        boolean res = new SplashScreen(driver)
                .checkVersion("1.0.0")
                .fillEmail("aqa@mail.ru")
                .fillPassword("aQa$1234")
                .submitLogin()
                .isContactListActivityDisplayed();

        Assert.assertTrue(res);
    }

    @Test
    public void loginSuccessModel() {
        Auth auth = Auth.builder().email("aqa@mail.ru").password("aQa$1234").build();
        boolean res = new SplashScreen(driver)
                .checkVersion("1.0.0")
                .fillLoginRegistrationForm(auth)
                .submitLogin()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);
    }

    @AfterMethod
    public void postCondition(){
new ContactListScreen(driver)
        .logOut();
    }
}
