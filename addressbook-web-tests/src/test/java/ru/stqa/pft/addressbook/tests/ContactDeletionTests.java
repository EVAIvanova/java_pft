package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition1() {

    app.goTo().HomePage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new Contacts()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
              .withGroup("[NONE]"), true);
    }
  }

  @Test
  public void testContactDeletion() {

    List<Contacts> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.getSessionHelper().closeDeletionWindow();
    List<Contacts> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }


}
