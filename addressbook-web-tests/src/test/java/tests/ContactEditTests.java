package tests;

import data.ContactData;
import data.Contacts;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecon() {
        app.goTo().homePage();
        if (app.contact().all().size() == 0){
            app.contact().create(new ContactData()
                    .withFirstname("First name")
                    .withMiddlename("Middle name")
                    .withLastname("Last name")
                    .withAddress("address")
                    .withEmail("email")
                    .withHomePhone("111")
                    .withMobilePhone("222"));
        }
    }

    @Test
    public void testContactEdit() throws Exception {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        //int index = before.size() -1;
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("First name1")
                .withMiddlename("Middle name1")
                .withLastname("Last name1")
                .withAddress("address")
                .withEmail("email")
                .withHomePhone("111")
                .withMobilePhone("222");
        app.contact().modify(contact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.contact().all();

        //before.remove(modifiedContact);
        //before.add(contact);

        assertThat(before.without(modifiedContact).withAdded(contact), equalTo(after));
    }



}
