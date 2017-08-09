package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

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
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData().withId(before.get(index).getId())
            .withName("Den").withLastName("Den").withGroup(null);
    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size() , before.size());

    before.remove(index);
    before.add(contact);


    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }
}
