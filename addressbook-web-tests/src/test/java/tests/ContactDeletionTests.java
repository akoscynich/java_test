package tests;

import data.ContactData;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() throws Exception {
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("First name", "Middle name", "Last name"));
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteContact();
    }

}
