package tests;

import config.AppiumConfig;
import models.Auth;
import models.Contact;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;

import java.util.Random;

public class AddContactTests extends AppiumConfig {

    @BeforeMethod
    public void precondition() {
        new AuthenticationScreen(driver)
                .fillLoginRegistrationForm(Auth.builder().email("bqa@mail.ru").password("bQa$1234").build())
                .submitLogin()
                .isContactListActivityDisplayed();
    }
    @Test (invocationCount = 5)
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
                .isContactAddedByPhone(contact.getPhone())
                .logout();
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
                .isErrorMessageContainsText("name=must not be")
               .returnToContactList()
                .logout();

    }
    @Test
    public void addNewContactEmptyLastName() {

        Contact contact = Contact.builder()
                .name("Bart")
                .email("bart@mail.com")
                .phone("123451234500")
                .address("NY")
                .description("Friend").build();

        new ContactListScreen(driver)
                .openContactForm()
                .fillContactForm(contact)
                .submitContactFormNegative()
                .isErrorMessageContainsText("lastName=must not be")
                .returnToContactList()
                .logout();
    }

}
