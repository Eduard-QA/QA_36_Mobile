package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddContactBeforeClassTests extends AppiumConfig {

    @BeforeClass
    public void precondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("bqa@mail.ru").password("bQa$1234").build())
                .submitLogin()
                .isContactListActivityDisplayed();
    }

    @Test
    public void addNewContactSuccess() {
        int i = new Random().nextInt(1000) + 1000;
        Contact contact = Contact.builder()
                .name("Bart")
                .lastName("Simpson")
                .email("bart" + i + "@mail.com")
                .phone("1234512345" + i)
                .address("NY")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactForm()
                .isContactAddedByNameLastName(contact.getName(), contact.getLastName())
                .isContactAddedByPhone(contact.getPhone());

    }

    @Test
    public void addNewContactEmptyName() {
        Contact contact = Contact.builder()
                .lastName("Simpson")
                .email("bart@mail.com")
                .phone("123451234500")
                .address("NY")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageContainsText("name=must not be blank");


    }

    @Test
    public void addNewContactEmptyLastName() {
        Contact contact = Contact.builder()
                .name("Wow")
                .email("bart@mail.com")
                .phone("123451234500")
                .address("NY")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageContainsText("lastName=must not be blank");
    }

    @AfterClass
    public void posCondition() {
        new ContactListScreen(driver)
                .logout();
    }

}