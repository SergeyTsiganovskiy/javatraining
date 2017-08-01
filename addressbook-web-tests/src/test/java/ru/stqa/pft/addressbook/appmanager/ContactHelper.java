package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitForm() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData usersInfo, boolean creation) {
    type(By.name("firstname"), usersInfo.getName());
    type(By.name("lastname"), usersInfo.getLastName());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(usersInfo.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContact(int index) {
    click(By.cssSelector(String.format("tbody>tr:nth-child(%d) input", index + 1)));
  }

//  public void selectContact() {
//    click(By.cssSelector("tbody>tr:nth-child(2) input"));
//  }

  public void edit() {
    click(By.cssSelector("tbody>tr:nth-child(2) a[href ^= \"edit\"]"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void clearContactForm() {
    clear(By.name("firstname"));
    clear(By.name("lastname"));
  }

  public void deleteContact() {
    click(By.xpath("//input[@value=\"Delete\"]"));
  }


  public void acceptDeletion() {
    wd.switchTo().alert().accept();
  }

  public boolean iaThereAContact() {
    return isElementPresent(By.cssSelector("tbody>tr:nth-child(2)"));
  }

  public void createContact(ContactData contactData) {
    initContactCreation();
    fillContactForm(new ContactData("John", "Smith", "a"), true);
    submitForm();
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//*[@name='selected[]']")).size();
  }

  public List<ContactData> getContactList() {
    List<ContactData> groups = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable>tbody>tr"));
    elements.remove(0);
    for (WebElement element:elements) {
      String name = element.findElement(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(3)")).getText();
      String lastName = element.findElement(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(2)")).getText();
      groups.add(new ContactData(name, lastName , null));
    }
    return groups;
  }
}
