package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;

public class ContactEditTests extends TestBase {

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
  public void testContactEdit() {
    List<Contacts> before = app.contact().list();
    int index = before.size() - 1;
    Contacts contact = new Contacts().withId(before.get(index).getId())
            .withFirstname("Elena").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
            .withGroup("[NONE]");
    app.contact().modify(before, index, contact, false);
    app.goTo().HomePage();
    List<Contacts> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super Contacts> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }


}
