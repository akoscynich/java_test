package tests;

import data.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddConToGrTest extends TestBase {

    private long now = System.currentTimeMillis();

    //ContactData validContact;
    //GroupData validGroup;

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
        app.contact().selectContact();
        if (app.db().groups().size() > 1)
        app.group().selectNew(now * 2);
        app.contact().addToGroup();
        int after = app.db().contacts().iterator().next().getGroups().size();

        assertEquals(before + 1, after);
    }

    @Test
    public void testDelContactFromGroup() {
        app.goTo().groupPage();
        int idOfNewGroup = app.group().getIdOfNewGroup(now);
        int before = app.db().contactsInGroup(idOfNewGroup).size();
        app.goTo().homePage();
        app.group().selectGroup(now);
        //app.contact().selectContact(now);
        app.contact().removeFromGroup();
        int after = app.db().contactsInGroup(idOfNewGroup).size();
        assertEquals(before - 1, after);
    }

    /*private ContactData getValidContact() {
        Contacts contacts = app.db().contacts();
        Groups groups = app.db().groups();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() < groups.size()) {
                return contact;
            }
        }
        app.goTo().homePage();
        ContactData contact = new ContactData()
                .withFirstname("")
                .withMiddlename("Middle name")
                .withLastname(Long.toString(now))
                .withAddress("address")
                .withEmail("email")
                .withEmail2("email2")
                .withEmail3("email3")
                .withHomePhone("111")
                .withMobilePhone("222")
                .withWorkPhone("333");
        app.contact().create(contact);
        return contact;
    }*/


    /*private GroupData getValidGroup(ContactData contact) {
        Groups groups = app.db().groups();
        for (GroupData group : contact.getGroups()) {
            groups.remove(group);
        }
        if (groups.size() > 0) {
            return groups.iterator().next();
        }
        return null;
    }

    private ContactData getValidContactToDeleteFromGroup() {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
        ContactData contact = contacts.iterator().next();
        app.contact().addToGroup(contact, app.db().groups().iterator().next());
        return contact;
    }*/
}



