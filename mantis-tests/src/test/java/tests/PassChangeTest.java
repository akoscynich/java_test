package tests;

import appmanager.HttpSession;
import model.MailMessage;
import model.UserData;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PassChangeTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPassChange() throws IOException, MessagingException {

        app.passChangeHelper().login();
        app.passChangeHelper().goToManageUsersPage();
        UserData user = app.db().users().iterator().next();
        app.passChangeHelper().selectUser(user);
        String userName = app.passChangeHelper().getUserName();
        String email = app.passChangeHelper().getUserName() + "@localhost.localdomain";
        app.passChangeHelper().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.passChangeHelper().goToUrl(confirmationLink);
        app.passChangeHelper().setNewPassword();

        HttpSession session = app.newSession();
        assertTrue(session.login(userName, "newpassword"));
        assertTrue(session.isLoggedAs(userName));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}
