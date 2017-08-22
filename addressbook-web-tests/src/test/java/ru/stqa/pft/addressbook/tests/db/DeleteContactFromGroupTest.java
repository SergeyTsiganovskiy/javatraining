package ru.stqa.pft.addressbook.tests.db;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;

public class DeleteContactFromGroupTest extends TestBase {

  private ContactData testContact = new ContactData().withName("Name" + System.currentTimeMillis()).withLastName("LastName");

  private String groupName1;
  private String groupName2;

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homePage();
    app.contact().create(testContact);
    if (app.db().groups().size() < 2) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("GroupTestName1")
              .withHeader("GroupTestHeader1").withFooter("GroupTestFooter1"));
      app.group().create(new GroupData().withName("GroupTestName2")
              .withHeader("GroupTestHeader2").withFooter("GroupTestFooter2"));
    }
    app.goTo().homePage();
    groupName1 = app.contact().getAllGroup().get(0);
    groupName2 = app.contact().getAllGroup().get(1);
    // добавляем тестовый контакт в одну группу
    app.contact().clickContactByName(testContact.getName());
    app.contact().chooseGroup(groupName1);
    app.contact().addToGroup();
    // добавляем тестовый контакт в другую группу
    app.goTo().homePage();
    app.contact().clickContactByName(testContact.getName());
    app.contact().chooseGroup(groupName2);
    app.contact().addToGroup();
  }

  @Test
  public void testDeleteContactFromGroup() {
    // удаляем тестовый котакт из первой группы
    app.goTo().homePage();
    app.contact().selectGroupToRemove(groupName1);
    app.contact().clickContactByName(testContact.getName());
    app.contact().remove();

    // удаляем тестовый котакт из второй группы
    app.goTo().homePage();
    app.contact().selectGroupToRemove(groupName2);
    app.contact().clickContactByName(testContact.getName());
    app.contact().remove();

    // проверяем, что контакт не добавлен в  группы
    Contacts allDbContacts = app.db().contacts();
    Iterator<ContactData> iterator = allDbContacts.iterator();
    StringBuilder groupNames = new StringBuilder();
    while (iterator.hasNext()) {
      ContactData contact = iterator.next();
      if (contact.getName().equals(testContact.getName())) {
        Groups groups = contact.getGroups();
        for (GroupData group : groups) {
          groupNames.append(group.getName());
        }
        assertThat(groupNames.toString(), equalTo(""));
        return;
      }
    }
    assertFalse(false);
  }
}
