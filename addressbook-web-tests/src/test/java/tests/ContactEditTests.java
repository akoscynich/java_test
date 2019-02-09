package tests;

import data.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecon() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData().withFirstname("First name").withMiddlename("Middle name").withLastname("Last name"));
        }
    }

    @Test
    public void testContactEdit() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        //int index = before.size() -1;
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId()).withFirstname("First name1").withMiddlename("Middle name1").withLastname("Last name1");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);

        Assert.assertEquals(before, after);
    }



}
