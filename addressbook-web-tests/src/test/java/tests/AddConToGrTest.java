package tests;

import data.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddConToGrTest extends TestBase {

    private long now = System.currentTimeMillis();

    @BeforeClass
    public void ensurePrecon() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("")
                    .withMiddlename("Middle name")
                    .withLastname(Long.toString(now))
                    .withAddress("address")
                    .withEmail("email")
                    .withEmail2("email2")
                    .withEmail3("email3")
                    .withHomePhone("111")
                    .withMobilePhone("222")
                    .withWorkPhone("333"));
        }
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName(Long.toString(now))
                    .withHeader("test2")
                    .withFooter("test3"));
        }
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size()) {
                break;
            } else {
                app.goTo().groupPage();
                app.group().create(new GroupData()
                        .withName(Long.toString(now * 2))
                        .withHeader("test2")
                        .withFooter("test3"));
            }
        }
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().homePage();
        int before = app.db().contacts().iterator().next().getGroups().size();
        if (app.db().groups().size() > 1){
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName(Long.toString(now * 2))
                    .withHeader("test2")
                    .withFooter("test3"));
        app.goTo().homePage();
        app.group().selectNew(now * 2);}
        app.contact().selectContact();
        app.contact().addToGroup();
        int after = app.db().contacts().iterator().next().getGroups().size();

        assertEquals(before + 1, after);
    }

    @Test
    public void testDelContactFromGroup() {
        app.goTo().homePage();
        for (ContactData contact : app.db().contacts())
            if (contact.getGroups().size() > 0) {
                int before = contact.getGroups().size();
                String validGroup = contact.getGroups().iterator().next().getName();
                app.group().selectGroup(validGroup);
                app.contact().selectContact(contact);
                app.contact().removeFromGroup();
                app.goTo().homePage();
                int after = contact.getGroups().size();
                assertEquals(before - 1, after);
                break;
            } else {
                testAddContactToGroup();
                testDelContactFromGroup();
            }
    }

}



