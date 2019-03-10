package tests;

import data.ContactData;
import data.ContactsInGroupsData;
import data.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddConToGrTest extends TestBase {

    private long now = System.currentTimeMillis();

    @BeforeClass
    public void ensurePrecon() {
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
        app.goTo().groupPage();
        app.group().create(new GroupData()
                .withName(Long.toString(now))
                .withHeader("test2")
                .withFooter("test3"));
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


    @Test(expectedExceptions = java.util.NoSuchElementException.class)
    public void testDelContactFromGroup() {
        app.goTo().groupPage();
        int idOfNewGroup = app.group().getIdOfNewGroup(now);
        app.goTo().homePage();
        app.group().selectGroup(now);
        app.contact().selectNew(now);
        app.contact().removeFromGroup();
        /*try {*/
            ContactsInGroupsData contactsInGroupsData = app.db().contactsInGroup(idOfNewGroup).iterator().next();
        /*}
        catch (java.util.NoSuchElementException ex){
            String success = "Success!";
        }
        assertEquals("Success!", success);*/
    }
}
