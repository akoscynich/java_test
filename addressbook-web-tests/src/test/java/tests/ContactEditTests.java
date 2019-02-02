package tests;

import data.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactEditTests extends TestBase {

    @Test
    public void testContactEdit() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()){
            app.getContactHelper().createContact(new ContactData("First name", "Middle name", "Last name"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().initContactEdit();
        app.getContactHelper().clearContactForm();
        ContactData contact = new ContactData("First name1", "Middle name1", "Last name1");
        app.getContactHelper().fillContactForm(contact);
        app.getContactHelper().submitContactEdit();
        app.getNavigationHelper().gotoHomePage();

        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(contact);
        Comparator<? super ContactData> byId = (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
