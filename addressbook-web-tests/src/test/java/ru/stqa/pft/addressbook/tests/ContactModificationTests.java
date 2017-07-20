package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase{

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContact();
    app.getContactHelper().edit();
    app.getContactHelper().clearContactForm();
    app.getContactHelper().fillContactForm(new ContactData("Den", "Braun", "SuperCompany", "Some text", "725-25-25", "0971111111", "test@gmail.com"));
    app.getContactHelper().updateContact();
  }
}
