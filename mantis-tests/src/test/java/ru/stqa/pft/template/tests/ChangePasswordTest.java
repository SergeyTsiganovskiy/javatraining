package ru.stqa.pft.template.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.template.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

  private String user;
  private String password;
  private String newPassword = "newPassword";
  private String email;

  @BeforeMethod
  public void startMailServer() throws IOException, MessagingException {
    app.mail().start();
    int random = new Random().nextInt(1000);
    user = "user" + random;
    password = "password";
    email = "user" + random + "@localhost.localdomain";
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.mail().waitForMessage(2, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
  }

  @Test
  public void testChangePassword() throws IOException, MessagingException {
    app.login().login("administrator", "root");
    app.login().resetPassword(user);
    List<MailMessage> mailMessages = app.mail().waitForMessage(3, 10000);
    String confirmationLink = findChangePawwordConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, newPassword);
    assertTrue(app.newSession().login(user, newPassword));
  }

  private String findChangePawwordConfirmationLink(List<MailMessage> mailMessages, String email) {
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessages.get(2).text);
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
