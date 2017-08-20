//package ru.stqa.pft.addressbook.tests;
//
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import ru.stqa.pft.addressbook.model.ContactData;
//
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.hamcrest.MatcherAssert.assertThat;
//
//public class ContactPhoneTests extends TestBase{
//
//  @BeforeMethod
//  public void ensurePreconditions(){
//    app.goTo().homePage();
//    app.contact().deleteAll();
//    app.contact().create(new ContactData().withName("John").withLastName("Smith")
//            .withHomePhone("+7457892467").withMobilePhone("(068)4578921").withGroup("for tests"));
//
//  }
//
//  @Test
//  public void testContactPhone() {
//    app.goTo().homePage();
//    ContactData contact = app.contact().all().iterator().next();
//    ContactData editFormContact = app.contact().editFormInfo(contact);
//    assertThat(contact.getAllPhones(), equalTo(mergePhones(editFormContact)));
//  }
//
//  private String mergePhones(ContactData editFormContact) {
//    return Arrays.asList(editFormContact.getHomePhone(), editFormContact.getMobilePhone(), editFormContact.getWorkPhone())
//            .stream().filter((s) -> !s.equals("")).map(ContactPhoneTests::cleaned ).collect(Collectors.joining("\n"));
//  }
//
//  private static String cleaned(String phone){
//    return phone.replaceAll("\\s","").replaceAll("[-()]","");
//  }
//}
