package appmanager;

import model.UserData;
import org.openqa.selenium.By;

public class PassChangeHelper extends HelperBase {

    public PassChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void login() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.cssSelector("input[type='submit']"));
    }

    public void goToManageUsersPage() {
        wd.findElement(By.linkText("Manage Users")).click();
    }

    public void selectUser(UserData user) {
        click(By.linkText(user.getUsername()));
    }

    public String getUserName() {
        return wd.findElement(By.cssSelector("input[name='username']")).getAttribute("value");
    }

    public void resetPassword() {
        wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
    }

    public void goToUrl(String url) {
        wd.get(url);
    }


    public void setNewPassword() {
        type(By.name("password"), "newpassword");
        type(By.name("password_confirm"), "newpassword");
        click(By.cssSelector("input[value='Update User']"));
    }

}
