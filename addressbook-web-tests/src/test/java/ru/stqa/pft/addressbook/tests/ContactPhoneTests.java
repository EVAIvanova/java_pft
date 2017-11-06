package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;

public class ContactPhoneTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition1() {

    app.goTo().HomePage();
    if (app.contact().allС().size() == 0) {
      app.contact().createС(new Contactdata()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
              .withGroup("[NONE]"), true);
    }
  }

  @Test
  public void testContactPhones () {
    app.goTo().HomePage();
    Contactdata contact = app.contact().allС().iterator().next();
    Contactdata contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
  }
}
