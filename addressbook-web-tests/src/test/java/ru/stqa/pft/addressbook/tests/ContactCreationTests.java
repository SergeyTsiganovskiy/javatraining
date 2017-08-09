package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    app.goTo().gotoContactPage();
    ContactData contact = new ContactData().withName("John").withLastName("Smith").withGroup("a");
    app.contact().create(contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size() , before.size() + 1);

    int max = 0;
    for (ContactData contactData: after) {
      if (contactData.getId() > max) {
        max = contactData.getId();
      }
    }

    contact.withId(max);

    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
