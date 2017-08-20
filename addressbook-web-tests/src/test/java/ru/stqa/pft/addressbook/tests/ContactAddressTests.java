//package ru.stqa.pft.addressbook.tests;
//
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import ru.stqa.pft.addressbook.model.ContactData;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class ContactAddressTests extends TestBase {
//  @BeforeMethod
//  public void ensurePreconditions() {
//    app.goTo().homePage();
//    app.contact().deleteAll();
//    app.contact().create(new ContactData().withName("John").withLastName("Smith")
//            .withAdress("USA, NewYork, st.Green,45 123").withGroup("for tests"));
//  }
//
//  @Test
//  public void testContactAdress() {
//    app.goTo().homePage();
//    ContactData contact = app.contact().all().iterator().next();
//    ContactData editFormContact = app.contact().editFormInfo(contact);
//    assertThat(contact.getAdress(), equalTo(editFormContact.getAdress()));
//  }
//}
