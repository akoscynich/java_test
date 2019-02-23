package tests;

import data.ContactData;
import data.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecon() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("First name")
                    .withMiddlename("Middle name")
                    .withLastname("Last name")
                    .withAddress("address")
                    .withEmail("email")
                    .withEmail2("email2")
                    .withEmail3("email3")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333"));
        }
    }

    @Test//(enabled = false)
    public void testContactDeletion() throws Exception {

        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        //int index = before.size() -1;
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.db().contacts();

        //before.remove(deletedContact);
        assertEquals(before.without(deletedContact), after);
    }


}
