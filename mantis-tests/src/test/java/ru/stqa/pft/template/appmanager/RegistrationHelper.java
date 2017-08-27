package ru.stqa.pft.template.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

  public RegistrationHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String userName, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
    type(By.name("username"), userName);
    type(By.name("email"), email);
    click((By.cssSelector("input[type='submit']")));

  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click((By.cssSelector("button[type='submit']")));
    click((By.cssSelector("button")));
  }
}