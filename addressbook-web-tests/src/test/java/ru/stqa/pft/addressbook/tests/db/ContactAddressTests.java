package ru.stqa.pft.addressbook.tests.db;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

public class ContactAddressTests extends TestBase {

  private ContactData testContact = new ContactData().withName("John").withLastName("Doe")
          .withAdress("USA, NewYork, st.Green,45 123");

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    app.contact().create(testContact);
  }


  @Test
  public void testContactAdress() {
    Contacts allContacts = app.db().contacts();
    app.goTo().homePage();
    Contacts allUIContacts = app.contact().all();
    Assert.assertTrue(allContacts.containsAll(allUIContacts));
  }
}
