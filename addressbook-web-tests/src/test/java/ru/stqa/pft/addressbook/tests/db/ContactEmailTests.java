package ru.stqa.pft.addressbook.tests.db;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertTrue;

public class ContactEmailTests extends TestBase {

  ContactData testContact = new ContactData().withName("Name" + System.currentTimeMillis()).withLastName("LastName")
          .withEmail("test1@gmail.com").withEmail2("test2@gmail.com").withEmail3("test3@gmail.com");

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    app.contact().create(testContact);
  }

  @Test
  public void testContactEmail() {
    Contacts allContacts = app.db().contacts();
    app.goTo().homePage();
    Contacts allUIContacts = app.contact().all();
    String DbEmails = "";
    Iterator<ContactData> iterator = allContacts.iterator();
    while (iterator.hasNext()) {
      ContactData nextDbContact = iterator.next();
      if (nextDbContact.getName().equals(testContact.getName())) {
        DbEmails = nextDbContact.getEmail() + nextDbContact.getEmail2() + nextDbContact.getEmail3();
        break;
      }
    }
    String UIEmails = "";
    Iterator<ContactData> iteratorUI = allUIContacts.iterator();
    while (iteratorUI.hasNext()) {
      ContactData nextUIContact = iteratorUI.next();
      if (nextUIContact.getName().equals(testContact.getName())) {
        UIEmails = nextUIContact.getAllEmails().replaceAll("\n", "");
        break;
      }
    }

    assertTrue(DbEmails.length() > 0);
    assertThat(DbEmails, equalTo(UIEmails));
  }
}
