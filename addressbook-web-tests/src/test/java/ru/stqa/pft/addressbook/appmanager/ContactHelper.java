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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    click(By.cssSelector(String.format("tbody>tr:nth-child(%d) input", index + 2)));
  }

  public void selectContactById(int id) {
    click(By.cssSelector("input[id='" + id + "']"));
  }

  public void edit(int index) {
    click(By.cssSelector(String.format("tbody>tr:nth-child(%d) a[href ^= \"edit\"]", index + 2)));
  }

  public void editById(int id) {
    click(By.cssSelector("a[href = 'edit.php?id=" + id +"']"));
  }

  public void updateContact() {
    click(By.xpath(".//*[@id='content']/form[1]/input[1]"));
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

  public void create(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    submitForm();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    editById(contact.getId());
    clearContactForm();
    fillContactForm(contact,false);
    updateContact();

  }

  public void delete(int index) {
    selectContact(index);
    deleteContact();
    acceptDeletion();
  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    acceptDeletion();
  }

  public int getContactCount() {
    return wd.findElements(By.xpath("//*[@name='selected[]']")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable>tbody>tr"));
    for (int i = 1; i < elements.size(); i++) {
      String name = elements.get(i).findElement(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(3)")).getText();
      String lastName = elements.get(i).findElement(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(2)")).getText();
      int id = Integer.parseInt(elements.get(i).findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withName(name).withLastName(lastName).withGroup(null));
    }
    return contacts;
  }

  public Set<ContactData> all() {
    Set<ContactData> contacts = new HashSet<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable>tbody>tr"));
    for (int i = 1; i < elements.size(); i++) {
      String name = elements.get(i).findElement(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(3)")).getText();
      String lastName = elements.get(i).findElement(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(2)")).getText();
      int id = Integer.parseInt(elements.get(i).findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withName(name).withLastName(lastName).withGroup(null));
    }
    return contacts;
  }

}
