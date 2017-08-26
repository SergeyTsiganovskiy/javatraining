package ru.stqa.pft.template.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
  private ApplicationManager app;
  private WebDriver wd;

  public RegistrationHelper(ApplicationManager app) {
    this.app = app;
    wd = app.getDriver();
  }

  public void start(String userName, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
  }
}
