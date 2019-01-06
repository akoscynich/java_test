package appmanager;

import data.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

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
}
