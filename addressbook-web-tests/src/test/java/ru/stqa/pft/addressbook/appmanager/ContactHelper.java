package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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


  public void selectContactById(int id) {
    click(By.cssSelector("input[id='" + id + "']"));
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
    contactCache = null;
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    editById(contact.getId());
    clearContactForm();
    fillContactForm(contact,false);
    updateContact();
    contactCache = null;

  }


  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    acceptDeletion();
    contactCache = null;
  }

  public int count() {
    return wd.findElements(By.xpath("//*[@name='selected[]']")).size();
  }


  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("#maintable>tbody>tr"));
    for (int i = 1; i < elements.size(); i++) {
      String name = elements.get(i).findElement(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(3)")).getText();
      String lastName = elements.get(i).findElement(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(2)")).getText();
      int id = Integer.parseInt(elements.get(i).findElement(By.tagName("input")).getAttribute("value"));
      String allPhones = elements.get(i).findElement(By.cssSelector("#maintable>tbody>tr>td:nth-of-type(6)")).getText();
      contactCache.add(new ContactData().withId(id).withName(name).withLastName(lastName).withGroup(null)
              .withAllPhones(allPhones));
    }
    return new Contacts(contactCache);
  }

  public ContactData editFormInfo(ContactData contact) {
    editById(contact.getId());
    int id = contact.getId();
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    return new ContactData().withId(id).withName(name).withLastName(lastName)
            .withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone);
  }
}
