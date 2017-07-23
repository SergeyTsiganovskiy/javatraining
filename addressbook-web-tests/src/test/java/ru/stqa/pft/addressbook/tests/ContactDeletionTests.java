package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase{

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().iaThereAContact()){
      app.getContactHelper().createContact(new ContactData("John", "Smith", "a"));
    }
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteContact();
    app.getContactHelper().acceptDeletion();

  }
}
