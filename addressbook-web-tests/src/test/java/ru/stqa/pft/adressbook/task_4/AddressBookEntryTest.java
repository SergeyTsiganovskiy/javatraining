package ru.stqa.pft.adressbook.task_4;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;

public class AddressBookEntryTest {
  FirefoxDriver wd;

  @BeforeMethod
  public void setUp() throws Exception {
    System.setProperty("webdriver.gecko.driver", "D:\\geckodriver.exe");
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/edit.php");
    login("admin", "secret");
  }

  private void login(String userName, String password) {
    wd.findElement(By.name("user")).sendKeys(userName);
    wd.findElement(By.id("LoginForm")).click();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.cssSelector("html")).click();
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  @Test
  public void testAddressBookEntry() {
    initAddressBookEntryCreation();
    fillEntryForm(new UsersInfo("John", "Smith", "SuperCompany", "Some text", "725-25-25", "0971111111", "test@gmail.com"));
    submitForm();
    returnToHomePage();
  }

  private void returnToHomePage() {
    wd.findElement(By.linkText("home")).click();
  }

  private void submitForm() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillEntryForm(UsersInfo usersInfo) {
    wd.findElement(By.name("firstname")).sendKeys(usersInfo.getName());
    wd.findElement(By.name("lastname")).sendKeys(usersInfo.getLastName());
    wd.findElement(By.name("company")).sendKeys(usersInfo.getCompany());
    wd.findElement(By.name("address")).sendKeys(usersInfo.getText());
    wd.findElement(By.name("home")).sendKeys(usersInfo.getHomePhone());
    wd.findElement(By.name("mobile")).sendKeys(usersInfo.getMobile());
    wd.findElement(By.name("email")).sendKeys(usersInfo.getEmail());
  }

  private void initAddressBookEntryCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod
  public void tearDown() {
    wd.quit();
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
