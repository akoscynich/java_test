package tests;

import data.ContactData;
import data.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecon() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData().withFirstname("First name").withMiddlename("Middle name").withLastname("Last name"));
        }
    }

    @Test//(enabled = false)
    public void testContactDeletion() throws Exception {

        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        //int index = before.size() -1;
        app.contact().delete(deletedContact);
        assertThat(app.contact().count(), equalTo(before.size() - 1));
        Contacts after = app.contact().all();

        //before.remove(deletedContact);
        assertEquals(before.without(deletedContact), after);
    }



}
