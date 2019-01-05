package appmanager;

import data.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactHelper {

    private WebDriver wd;

    public ContactHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void fillContactForm(ContactData contactData) {
        wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    public void submitContactCreation() {
        wd.findElement(By.name("submit")).click();
    }


}
