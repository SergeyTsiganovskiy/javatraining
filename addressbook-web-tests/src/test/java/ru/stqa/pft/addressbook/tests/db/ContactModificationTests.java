package ru.stqa.pft.addressbook.tests.db;

        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;
        import ru.stqa.pft.addressbook.model.ContactData;
        import ru.stqa.pft.addressbook.model.Contacts;
        import ru.stqa.pft.addressbook.tests.TestBase;

        import static org.hamcrest.CoreMatchers.equalTo;
        import static org.hamcrest.MatcherAssert.assertThat;
        import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){
    app.goTo().homePage();
    if (app.db().contacts().size() == 0){
      app.contact().create(new ContactData().withName("John").withLastName("Smith"));
    }
  }

  @Test
  public void testContactModification() throws InterruptedException {
    app.goTo().homePage();
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId())
            .withName("Den").withLastName("Braun");
    app.contact().modify(contact);
    app.goTo().homePage();
    assertEquals(app.contact().count(), before.size());
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }
}
