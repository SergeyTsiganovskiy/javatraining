package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.Test;

import ru.stqa.pft.adressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoContactPage();
    app.getContactHelper().initContactCreation();
    app.getContactHelper().fillContactForm(new ContactData("John", "Smith", "SuperCompany", "Some text", "725-25-25", "0971111111", "test@gmail.com"));
    app.getContactHelper().submitForm();
    app.getNavigationHelper().gotoHomePage();
  }
}
