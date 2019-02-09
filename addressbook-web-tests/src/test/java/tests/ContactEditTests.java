package tests;

import data.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecon() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0){
            app.contact().create(new ContactData().withFirstname("First name").withMiddlename("Middle name").withLastname("Last name"));
        }
    }

    @Test
    public void testContactEdit() throws Exception {
        List<ContactData> before = app.contact().list();
        int index = before.size() -1;
        ContactData contact = new ContactData()
                .withId(before.get(index).getId()).withFirstname("First name1").withMiddlename("Middle name1").withLastname("Last name1");
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }



}
