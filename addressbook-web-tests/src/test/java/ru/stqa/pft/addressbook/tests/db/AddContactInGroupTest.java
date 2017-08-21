package ru.stqa.pft.addressbook.tests.db;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Iterator;

import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class AddContactInGroupTest extends TestBase {

  ContactData testContact = new ContactData().withName("Name" + System.currentTimeMillis()).withLastName("LastName");

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    app.contact().create(testContact);
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("GroupTestName")
              .withHeader("GroupTestHeader").withFooter("GroupTestFooter"));
    }
  }

  @Test
  public void testAddContactInGroup() {
    app.goTo().homePage();
    app.contact().clickContactByName(testContact.getName());
    String groupName = app.contact().getAllGroup().iterator().next();
    app.contact().chooseGroup(groupName);
    app.contact().addToGroup();
    Contacts allDbContacts = app.db().contacts();
    Iterator<ContactData> iterator = allDbContacts.iterator();
    while (iterator.hasNext()){
      ContactData contact = iterator.next();
      if (contact.getName().equals(testContact.getName())){
        assertTrue((contact.getGroups().iterator().next().getName()).equals(groupName));
        return;
      }
    }
    assertFalse(false);
  }

}
