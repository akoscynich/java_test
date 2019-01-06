package tests;

import data.ContactData;
import org.testng.annotations.Test;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdit() throws Exception {
        app.getContactHelper().initContactEdit();
        app.getContactHelper().clearContactForm();
        app.getContactHelper().fillContactForm(new ContactData("First name1", "Middle name1", "Last name1"));
        app.getContactHelper().submitContactEdit();

    }

}
