package tests;

import data.Contacts;
import org.testng.Assert;
import org.testng.annotations.*;
import data.ContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstname("First name1").withMiddlename("Middle name1").withLastname("Last name1");
        app.contact().create(contact);
        app.goTo().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() + 1));
        //contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        //before.add(contact);
        assertThat(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())), equalTo(after));
    }

}
