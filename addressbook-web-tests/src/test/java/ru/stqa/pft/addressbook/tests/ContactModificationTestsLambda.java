package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTestsLambda extends TestBase{

  @Test
  public void testContactModification() throws InterruptedException {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().iaThereAContact()){
      app.getContactHelper().createContact(new ContactData("John", "Smith", "a"));
    }
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().edit(before.size() - 1);
    app.getContactHelper().clearContactForm();
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Den", "Den", null);
    app.getContactHelper().fillContactForm(contact,false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHomePage();

    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size() , before.size());

    before.remove(before.size() - 1);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId() , c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
