package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;

import java.util.Set;

public class ContactSendEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().HomePage();
    if (app.contact().allС().size() == 0) {
      app.contact().createС(new Contactdata()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withMobilePhone("7472304").withEmail("skyLena1@ya.ru")
              .withGroup("[NONE]"), true);
    }
  }
  @Test
  public void testContactSendEmail() {

    Set<Contactdata> before = app.contact().allС();
    app.contact().selectContacts(before.size() - 1);
    app.contact().sendEmailContacts();
    Set<Contactdata> after = app.contact().allС();
    Assert.assertEquals(after.size(), before.size());
  }

}
