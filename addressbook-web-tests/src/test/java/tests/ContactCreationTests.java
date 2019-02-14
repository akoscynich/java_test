package tests;

import data.ContactData;
import data.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test(enabled = true)
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        File photo = new File("src/test/resources/s1200.png");
        ContactData contact = new ContactData()
                .withFirstname("First name")
                .withMiddlename("Middle name")
                .withLastname("Last name")
                .withAddress("address")
                .withEmail("email")
                .withEmail2("email2")
                .withEmail3("email3")
                .withHomePhone("111")
                .withMobilePhone("222")
                .withWorkPhone("333")
                .withPhoto(photo);
        app.contact().create(contact);
        app.goTo().homePage();
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        //contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        //before.add(contact);
        assertThat(before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())), equalTo(after));
    }

}
