package tests;

import org.testng.annotations.*;
import data.ContactData;


public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() throws Exception {
        app.initContactCreation();
        app.fillContactForm(new ContactData("First name", "Middle name", "Last name"));
        app.submitContactCreation();
    }

}
