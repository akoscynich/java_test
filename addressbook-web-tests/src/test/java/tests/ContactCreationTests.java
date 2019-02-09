package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import data.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        Set<ContactData> before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("First name1").withMiddlename("Middle name1").withLastname("Last name1");
        app.contact().create(contact);
        app.goTo().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}
