package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import model.Auth;

import org.openqa.selenium.support.FindBy;

public class AuthenticationScreen extends BaseScreen{
    public AuthenticationScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id ='com.sheygam.contactapp:id/inputEmail']")
    MobileElement emailEditText;
    @FindBy(id="com.sheygam.contactapp:id/inputPassword")
    MobileElement passwordEditText;
    @FindBy(xpath = "//*[@text='LOGIN']")
    MobileElement loginButton;
    @FindBy(id="com.sheygam.contactapp:id/regBtn")
    MobileElement registrationButton;

    public AuthenticationScreen fillLoginRegistrationForm(Auth auth){

        should(emailEditText,15);
        type(emailEditText, auth.getEmail());
        type(passwordEditText, auth.getPassword());

        return this;
    }

    public AuthenticationScreen fillEmail(String  email){
        should(emailEditText,15);
        type(emailEditText,email);
        return this ;
    }
    public AuthenticationScreen fillPassword(String password){
        type(passwordEditText,password);
        return this;
    }
    public ContactListScreen submitLogin(){
        driver.hideKeyboard();
        loginButton.click();
        return new ContactListScreen(driver);
    }

    public ContactListScreen submitRegistration() {
        driver.hideKeyboard();
        registrationButton.click();
        return new ContactListScreen(driver);
    }
}