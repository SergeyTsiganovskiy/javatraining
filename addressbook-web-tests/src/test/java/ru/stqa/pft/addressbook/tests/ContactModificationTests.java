package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactModificationTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size() == 0){
      app.contact().create(new ContactData().withName("John").withLastName("Smith").withGroup("a"));
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {
    app.goTo().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withName("Den").withLastName("Den").withGroup(null);
    app.contact().modify(contact);
    app.goTo().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size() , before.size());

    before.remove(modifiedContact);
    before.add(contact);


    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
