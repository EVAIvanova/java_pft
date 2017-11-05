package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition1() {

    app.goTo().HomePage();
    if (app.contact().listС().size() == 0) {
      app.contact().createС(new Contacts()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
              .withGroup("[NONE]"), true);
    }
  }

  @Test
  public void testContactDeletion() {

    Set<Contacts> before = app.contact().allС();
    Contacts deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.getSessionHelper().closeDeletionWindow();
    Set<Contacts> after = app.contact().allС();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

  }


}
