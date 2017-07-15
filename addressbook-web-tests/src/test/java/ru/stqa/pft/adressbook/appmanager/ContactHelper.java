package ru.stqa.pft.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
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

  public void initAddressBookEntryCreation() {
    click(By.linkText("add new"));
  }
}
