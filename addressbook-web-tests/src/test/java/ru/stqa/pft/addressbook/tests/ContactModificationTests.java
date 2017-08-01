package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().iaThereAContact()){
      app.getContactHelper().createContact(new ContactData("John", "Smith", "a"));
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().edit();
    app.getContactHelper().clearContactForm();
    app.getContactHelper().fillContactForm(new ContactData("Den", "Braun", null),false);
    app.getContactHelper().updateContact();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size() , before.size());
  }
}
