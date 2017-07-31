package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().iaThereAContact()){
      app.getContactHelper().createContact(new ContactData("John", "Smith", "a"));
    }
    int before = app.getContactHelper().getContactCount();
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact(before - 1);
    app.getContactHelper().deleteContact();
    app.getContactHelper().acceptDeletion();
    app.getNavigationHelper().gotoHomePage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after , before - 1);

  }
}
