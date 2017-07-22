package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData usersInfo) {
    wd.findElement(By.name("firstname")).sendKeys(usersInfo.getName());
    wd.findElement(By.name("lastname")).sendKeys(usersInfo.getLastName());
    wd.findElement(By.name("company")).sendKeys(usersInfo.getCompany());
    wd.findElement(By.name("address")).sendKeys(usersInfo.getText());
    wd.findElement(By.name("home")).sendKeys(usersInfo.getHomePhone());
    wd.findElement(By.name("mobile")).sendKeys(usersInfo.getMobile());
    wd.findElement(By.name("email")).sendKeys(usersInfo.getEmail());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    click(By.cssSelector("tbody>tr:nth-child(2) input"));
  }

  public void edit() {
    click(By.cssSelector("tbody>tr:nth-child(2) a[href ^= \"edit\"]"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void clearContactForm() {
    clear(By.name("firstname"));
    clear(By.name("middlename"));
    clear(By.name("lastname"));
    clear(By.name("nickname"));
    clear(By.name("company"));
    clear(By.name("home"));
    clear(By.name("mobile"));
    clear(By.name("email"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value=\"Delete\"]"));
  }


  public void acceptDeletion() {
    wd.switchTo().alert().accept();
  }
}
