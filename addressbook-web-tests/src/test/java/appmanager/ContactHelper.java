package appmanager;

import data.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoHomePage(){
        click(By.linkText("home"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), (contactData.getFirstname()));
        type(By.name("middlename"), (contactData.getMiddlename()));
        type(By.name("lastname"), (contactData.getLastname()));
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

    /*public void initContactEdit(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }*/

    public void clearContactForm() {
        clear(By.name("firstname"));
        clear(By.name("middlename"));
        clear(By.name("lastname"));
    }

    public void submitContactEdit() {
        click(By.name("update"));
    }

    public void create(ContactData data) {
        initContactCreation();
        fillContactForm(data);
        submitContactCreation();
        gotoHomePage();
    }

    public void modify(/*int index, */ContactData contact) {
        editContactById(/*index, */contact.getId());
        clearContactForm();
        fillContactForm(contact);
        submitContactEdit();
        gotoHomePage();

    }

    /*public void delete(int index) {
        selectContact(index);
        deleteContact();
        gotoHomePage();
    }*/

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    /*public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));//new ArrayList<WebElement>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();

            int id = Integer.parseInt(row.findElement(By.cssSelector("input")).getAttribute("id"));
            ContactData contact = new ContactData().withFirstname(firstName).withLastname(lastName).withId(id);
            contacts.add(contact);
        }
        return contacts;
    }*/

    public Set<ContactData> all() {
        Set<ContactData> contacts = new HashSet<>();
        List<WebElement> rows = wd.findElements(By.name("entry"));//new ArrayList<WebElement>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();

            int id = Integer.parseInt(row.findElement(By.cssSelector("input")).getAttribute("id"));
            ContactData contact = new ContactData().withFirstname(firstName).withLastname(lastName).withId(id);
            contacts.add(contact);
        }
        return contacts;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteContact();
        gotoHomePage();
    }

}
