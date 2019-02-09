package tests;

import data.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        //int index = before.size() -1;
        app.contact().delete(deletedContact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before, after);
    }



}
