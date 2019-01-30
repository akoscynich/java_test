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

    public void selectContact() {
        click(By.name("selected[]"));
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

    public void createContact(ContactData data){
        initContactCreation();
        fillContactForm(data);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> rows = wd.findElements(By.name("entry"));//new ArrayList<WebElement>();
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            ContactData contact = new ContactData(firstName, null, lastName);
            contacts.add(contact);
        }
        return contacts;

        /*public List<GroupData> getGroupList() {
            List<GroupData> groups = new ArrayList<GroupData>();
            List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
            for (WebElement element : elements){
                String name = element.getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                GroupData group = new GroupData(id, name, null, null);
                groups.add(group);
            }
            return groups;*/
        /*List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData(id, name, null, null);
            contacts.add(contact);
        }
        return contacts;*/
    }

    //cells.get(2).getText()
    /*лучше сделать так:
            1) найти все строки таблицы
        2) пройти в цикле по строкам
        3) внутри цикла каждую строку разбить на ячейки
         List<WebElement> cells = row.findElements(By.tagName("td"))
        4) брать из этого списка нужные ячейки по номеру (столбца) и получать их текст*/


}
