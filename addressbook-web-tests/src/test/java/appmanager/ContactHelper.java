package appmanager;

import data.ContactData;
import data.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static java.lang.Integer.parseInt;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoHomePage(){
        click(By.linkText("home"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("middlename"), contactData.getMiddlename());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
//        type(By.name("photo"), contactData.getPhoto().getAbsolutePath());
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void submitContactCreation() {
        click(By.name("submit"));
        find(By.cssSelector("div.msgbox"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void editContactById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        find(By.cssSelector("div.msgbox"));
    }

    public void clearContactForm() {
        clear(By.name("firstname"));
        clear(By.name("middlename"));
        clear(By.name("lastname"));
        clear(By.name("address"));
        clear(By.name("email"));
        clear(By.name("email2"));
        clear(By.name("email3"));
        clear(By.name("home"));
        clear(By.name("mobile"));
        clear(By.name("work"));
    }

    public void submitContactEdit() {
        click(By.name("update"));
    }

    public void create(ContactData data) {
        initContactCreation();
        fillContactForm(data);
        submitContactCreation();
        contactCache = null;
        gotoHomePage();
    }

    public void modify(/*int index, */ContactData contact) {
        editContactById(/*index, */contact.getId());
        clearContactForm();
        fillContactForm(contact);
        submitContactEdit();
        contactCache = null;
        gotoHomePage();

    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    private Contacts contactCache = null;


    public Contacts all() {
        if (contactCache != null)
            return new Contacts(contactCache);
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));//new ArrayList<WebElement>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String emails = cells.get(4).getText();//.split("\n");
            String phones = cells.get(5).getText();//.split("\n");

            int id = parseInt(row.findElement(By.cssSelector("input")).getAttribute("id"));
            ContactData contact = new ContactData()
                    .withFirstname(firstName)
                    .withLastname(lastName)
                    .withId(id)
                    .withAddress(address)
                    .withPhones(phones)
                    .withEmails(emails);
            contactCache.add(contact);
        }
        return contactCache;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        contactCache = null;
        gotoHomePage();
    }

    public ContactData infoFromEditForm(ContactData contact) {
        editContactById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String middlename = wd.findElement(By.name("middlename")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withMiddlename(middlename)
                .withAddress(address)
                .withEmail(email)
                .withEmail2(email2)
                .withEmail3(email3)
                .withHomePhone(home)
                .withMobilePhone(mobile)
                .withWorkPhone(work);
    }

    public void addToGroup() {
        wd.findElement(By.name("add")).click();
    }

    public void selectContact() {
        wd.findElement(By.cssSelector("input[type='checkbox']")).click();
        // wd.findElement(By.cssSelector("input[title='Select ( " + validContact.getLastname() + ")']")).click();
    }
    public void selectContact(ContactData valid) {
        //wd.findElement(By.cssSelector("input[type='checkbox']")).click();
        wd.findElement(By.cssSelector("input[title='Select ( " + valid.getLastname() + ")']")).click();
    }

    public int getIdOfNewContact(long now) {
        return parseInt(wd.findElement(By.cssSelector("input[type='Select ( " + now + ")']")).getAttribute("id"));
    }

    public void removeFromGroup() {
        wd.findElement(By.cssSelector("input[name='remove']")).click();
    }
}
