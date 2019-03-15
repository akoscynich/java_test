package tests;

import data.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.security.acl.Group;

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
        for (ContactData contact : app.db().contacts()) {
            if (contact.getGroups().size() < app.db().groups().size()) {
                Groups before = contact.getGroups();
                Groups allGroup = app.db().groups();
                allGroup.remove(before);
                GroupData group = allGroup.iterator().next();
                int groupId = group.getId();
                int contactId = contact.getId();
                app.goTo().homePage();
                app.group().selectNew(groupId);
                app.contact().selectContactById(contactId);
                app.contact().addToGroup();
                Groups after = contact.getGroups();
                assertEquals(before, after.withAdded(group));
                break;
            } else {
                app.goTo().groupPage();
                app.group().create(new GroupData()
                        .withName(Long.toString(now * 2))
                        .withHeader("test2")
                        .withFooter("test3"));

                Groups before = contact.getGroups();
                Groups allGroup = app.db().groups();
                allGroup.remove(before);
                GroupData group = allGroup.iterator().next();
                int groupId = group.getId();
                int contactId = contact.getId();
                app.goTo().homePage();
                app.group().selectNew(groupId);
                app.contact().selectContactById(contactId);
                app.contact().addToGroup();
                Groups after = contact.getGroups();
                assertEquals(before, after.withAdded(group));
            }
        }
    }

    /*1. В первом тесте надо проверять, что количество групп контакта меньше общего числа групп.
    Проверять в этом тесте надо не количество групп, а сравнивать списки групп контакта после добавления в группу.*/

    /*2. Во втором тесте не надо вызывать тест добавления контакта в группу,
    надо просто добавить контакт в группу если контакт не добавлен ни в какую группу.
    Рекурсивно тест удаления контакта из группы тоже вызывать не надо.
    Сначала выполняются все необходимые проверки, а затем сам тест.
    В этом тесте тоже надо сравнивать списки групп контакта после удаления контакта из группы.*/

    @Test
    public void testDelContactFromGroup() {
        app.goTo().homePage();
        for (ContactData contact : app.db().contacts())
            if (contact.getGroups().size() > 0) {
                Groups before = contact.getGroups();
                String validGroup = contact.getGroups().iterator().next().getName();
                GroupData group = contact.getGroups().iterator().next();
                app.group().selectGroup(validGroup);
                app.contact().selectContact(contact);
                app.contact().removeFromGroup();
                app.goTo().homePage();
                Groups after = contact.getGroups();
                assertEquals(before, after.without(group));
                break;
            } else {
                app.goTo().homePage();
                app.contact().selectContact();
                app.contact().addToGroup();

                Groups before = contact.getGroups();
                String validGroup = contact.getGroups().iterator().next().getName();
                GroupData group = contact.getGroups().iterator().next();
                app.group().selectGroup(validGroup);
                app.contact().selectContact(contact);
                app.contact().removeFromGroup();
                app.goTo().homePage();
                Groups after = contact.getGroups();
                assertEquals(before, after.without(group));


            }
    }

    /*@Test
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
    }*/

}




