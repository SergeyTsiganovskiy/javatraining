package ru.stqa.pft.adressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import ru.stqa.pft.adressbook.model.ContactData;

public class ContactTest extends TestBase{

  @Test
  public void testContact() {
    app.getContactHelper().initAddressBookEntryCreation();
    app.getContactHelper().fillContactForm(new ContactData("John", "Smith", "SuperCompany", "Some text", "725-25-25", "0971111111", "test@gmail.com"));
    app.getContactHelper().submitForm();
    app.getNavigationHelper().returnToHomePage();
  }
}
