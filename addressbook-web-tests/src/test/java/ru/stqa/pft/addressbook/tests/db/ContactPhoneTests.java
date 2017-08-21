package ru.stqa.pft.addressbook.tests.db;//package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.AssertJUnit.assertTrue;

public class ContactPhoneTests extends TestBase {

  ContactData testContact = new ContactData().withName("Name" + System.currentTimeMillis()).withLastName("LastName")
          .withHomePhone("+7457892467").withMobilePhone("(068)4578921").withWorkPhone("755-12-45");

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    app.contact().create(testContact);
  }

  @Test
  public void testContactPhone() {
    Contacts allContacts = app.db().contacts();
    app.goTo().homePage();
    Contacts allUIContacts = app.contact().all();
    String DbPhones = "";
    Iterator<ContactData> iterator = allContacts.iterator();
    while (iterator.hasNext()) {
      ContactData nextDbContact = iterator.next();
      if (nextDbContact.getName().equals(testContact.getName())) {
        String DbPhonesTemp = nextDbContact.getHomePhone() + nextDbContact.getMobilePhone() + nextDbContact.getWorkPhone();
        DbPhones = cleaned(DbPhonesTemp);
        break;
      }
    }
    String UIPhones = "";
    Iterator<ContactData> iteratorUI = allUIContacts.iterator();
    while (iteratorUI.hasNext()) {
      ContactData nextUIContact = iteratorUI.next();
      if (nextUIContact.getName().equals(testContact.getName())) {
        String UIPhonesTemp = nextUIContact.getAllPhones();
        UIPhones = UIPhonesTemp.replaceAll("\\s","");
        break;
      }
    }

    assertTrue(DbPhones.length() > 0);
    assertThat(DbPhones, equalTo(UIPhones));
  }

  private static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}

