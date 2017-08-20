//package ru.stqa.pft.addressbook.tests;
//
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import ru.stqa.pft.addressbook.model.ContactData;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class ContactEmailTests extends TestBase {
//  @BeforeMethod
//  public void ensurePreconditions() {
//    app.goTo().homePage();
//    app.contact().deleteAll();
//    app.contact().create(new ContactData().withName("John").withLastName("Smith")
//            .withEmail("test1@gmail.com").withEmail2("test2@gmail.com").withEmail3("test3@gmail.com").withGroup("for tests"));
//  }
//
//  @Test
//  public void testContactEmail() {
//    app.goTo().homePage();
//    ContactData contact = app.contact().all().iterator().next();
//    ContactData editFormContact = app.contact().editFormInfo(contact);
//    assertThat(contact.getAllEmails().replaceAll("\n", ""), equalTo(sumEmails(editFormContact)));
//  }
//
//  public String sumEmails(ContactData contact) {
//    return contact.getEmail() + contact.getEmail2() + contact.getEmail3();
//  }
//}
