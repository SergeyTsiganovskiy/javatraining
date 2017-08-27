package ru.stqa.pft.template.tests;

import org.junit.Assert;
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

public class RegistrationTest extends TestBase{

//  @BeforeMethod
  public void startMailServer(){
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    int random = new Random().nextInt(200);
    String user = "user" + random;
    String password = "password";
    String email = "user" + random + "@localhost";
    app.james().createUser(user, password);
    app.registration().start(user, email);
//    List<MailMessage> mailMessages = app.mail().waitForMessage(2, 10000);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 20000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(user, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

//  @AfterMethod (alwaysRun = true)
  public void stopMailServer(){
    app.mail().stop();
  }
}
