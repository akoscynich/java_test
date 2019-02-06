package appmanager;

import data.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        find(By.cssSelector("div.msgbox"));
    }

    public void initContactEdit() {
        click(By.xpath("//img[@alt='Edit']"));
    }

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

    public void modify(int index, ContactData contact) {
        //selectContact(index);
        initContactEdit();
        clearContactForm();
        fillContactForm(contact);
        submitContactEdit();
        gotoHomePage();

    }

    public void delete(int index) {
        selectContact(index);
        deleteContact();
        gotoHomePage();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
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
    }

}
