package tests;

import data.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddConToGrTest extends TestBase {

    private long now = System.currentTimeMillis();

    //ContactData validContact = getValidContact();
    //GroupData validGroup = getValidGroup();

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
    }

    @Test
    public void testAddContactToGroup() {
        app.goTo().homePage();
        app.contact().selectNew(now);
        int idOfNewContact = app.contact().getIdOfNewContact(now);
        app.group().selectNew(now);
        app.contact().addToGroup();
        app.goTo().groupPage();
        int idOfNewGroup = app.group().getIdOfNewGroup(now);

        ContactsInGroupsData contactsInGroupsData = app.db().contactsInGroup(idOfNewGroup).iterator().next();

        assertEquals(contactsInGroupsData.getContactId(), idOfNewContact);
        assertEquals(contactsInGroupsData.getGroupId(), idOfNewGroup);
    }

    @Test
    public void testDelContactFromGroup() {
        app.goTo().groupPage();
        int idOfNewGroup = app.group().getIdOfNewGroup(now);
        int before = app.db().contactsInGroup(idOfNewGroup).size();
        app.goTo().homePage();
        app.group().selectGroup(now);
        app.contact().selectNew(now);
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
    }

    private GroupData getValidGroup() {
        Groups groups = app.db().groups();
        for (GroupData group : contact.getGroups()) {
            groups.remove(group);
        }
        if (groups.size() > 0) {
            return groups.iterator().next();

        }
        return null;
    }

    private ContactData getAvailableContactToDeleteFromGroup() {
        Contacts contacts = app.db().contacts();
        for (ContactData contact : contacts) {
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
    }*/
}



