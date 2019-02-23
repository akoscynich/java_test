package tests;

import data.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactInfoTests extends TestBase {

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
    public void testContactPhones(){

        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData infoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getPhones(), equalTo(mergePhones(infoFromEditForm)));
        assertThat(contact.getEmails(), equalTo(mergeEmails(infoFromEditForm)));

    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
               .stream().filter((s) -> !s.equals("")).map(ContactInfoTests::cleaned).collect(Collectors.joining("\n"));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                .stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }

}
