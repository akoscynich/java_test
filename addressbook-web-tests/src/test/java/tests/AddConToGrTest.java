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
        for (ContactData contact : app.db().contacts()) {
            if (contact.getGroups().size() < app.db().groups().size()) {
                break;
            } else {
                app.goTo().groupPage();
                app.group().create(new GroupData()
                        .withName(Long.toString(now))
                        .withHeader("test2")
                        .withFooter("test3"));
            }
        }
    }

    @Test
    public void testAddContactToGroup() {
        ContactData validContact = null;
        for (ContactData contact : app.db().contacts())
            if (contact.getGroups().size() < app.db().groups().size())
                validContact = contact;
        Groups before = validContact.getGroups();
        Groups allGroup = app.db().groups();
        allGroup.removeAll(before);
        GroupData group = allGroup.iterator().next();
        app.goTo().homePage();
        app.group().selectNew(group.getId());
        app.contact().selectContactById(validContact.getId());
        app.contact().addToGroup();
        Groups after = app.db().contactById(validContact.getId()).iterator().next().getGroups();
        assertEquals(before.withAdded(group), after);
    }

    @Test
    public void testDelContactFromGroup() {
        ContactData validContact = null;
        GroupData validGroup = null;
        Groups before = null;
        int groupId = 0;
        for (ContactData contact : app.db().contacts())
            if (contact.getGroups().size() == 0) {
                app.goTo().homePage();
                app.contact().selectContactById(contact.getId());
                app.contact().addToGroup();
                validContact = contact;
                validGroup = app.db().contactById(validContact.getId()).iterator().next().getGroups().iterator().next();
                groupId = validGroup.getId();
                before = contact.getGroups();
            } else {
                validContact = contact;
                validGroup = contact.getGroups().iterator().next();
                groupId = validGroup.getId();
                before = contact.getGroups();
            }
        app.goTo().homePage();
        app.group().selectGroupById(groupId);
        app.contact().selectContactById(validContact.getId());
        app.contact().removeFromGroup();
        Groups after = app.db().contactById(validContact.getId()).iterator().next().getGroups();
        assertEquals(before.without(validGroup), after);
    }
    
}




