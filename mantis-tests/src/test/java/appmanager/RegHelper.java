package appmanager;

import org.openqa.selenium.WebDriver;

public class RegHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    }
}
