package tests;

import config.AppiumConfig;
import model.Auth;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class RegistrationTests extends AppiumConfig {
    @Test
    public void RegistrationSuccess(){
        Random random = new Random();
        int i = random.nextInt(1000);
        String email = "foxi" + i + "@gmail.com";

        boolean res = new AuthenticationScreen(driver)
                .fillEmail(email)
                .fillPassword("aQa$1234")
                .submitRegistration()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);

    }

    @Test
    public void loginSuccessModel(){
        Random random = new Random();
        int i = random.nextInt(1000);

        Auth auth = Auth.builder().email("foxi" + i + "@gmail.com").password("aQa$1234").build();
        boolean res = new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(auth)
                . submitRegistration()
                .isContactListActivityDisplayed();
        Assert.assertTrue(res);

    }

    @AfterMethod
    public void postCondition(){
        new ContactListScreen(driver)
                .logout();

    }
}
