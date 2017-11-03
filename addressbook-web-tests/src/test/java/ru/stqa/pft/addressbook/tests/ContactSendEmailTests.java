package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactSendEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().HomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new Contacts()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
              .withGroup("[NONE]"), true);
    }
  }
  @Test
  public void testContactSendEmail() {

    List<Contacts> before = app.contact().list();
    app.contact().selectContacts(before.size() - 1);
    app.contact().sendEmailContacts();
    List<Contacts> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());
  }

}
