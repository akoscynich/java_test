package tests;

import data.ContactData;
import data.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecon() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("First name")
                    .withMiddlename("Middle name")
                    .withLastname("Last name")
                    .withAddress("address")
                    .withEmail("email")
                    .withEmail2("email2")
                    .withEmail3("email3")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333"));
        }
    }

    @Test
    public void testContactEdit() throws Exception {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstname("First name1")
                .withMiddlename("Middle name1")
                .withLastname("Last name1")
                .withAddress("address1")
                .withEmail("email1")
                .withEmail2("email21")
                .withEmail3("email31")
                .withHomePhone("1111")
                .withMobilePhone("2221")
                .withWorkPhone("3331")
                .withId(modifiedContact.getId());
        app.contact().modify(contact);
        assertEquals(app.contact().count(), before.size());
        Contacts after = app.db().contacts();
        assertThat(before.without(modifiedContact).withAdded(contact), equalTo(after));
        verifyConListUi();
    }
}
