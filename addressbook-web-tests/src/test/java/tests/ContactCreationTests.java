package tests;

import org.testng.Assert;
import org.testng.annotations.*;
import data.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.contact().list();
        ContactData contact = new ContactData().withFirstname("First name1").withMiddlename("Middle name1").withLastname("Last name1");
        app.contact().create(contact);
        app.goTo().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
