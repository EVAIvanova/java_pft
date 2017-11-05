package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactDetailTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().HomePage();
    if (app.contact().listС().size() == 0) {
      app.contact().createС(new Contacts()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
              .withGroup("[NONE]"), true);
    }
  }

  @Test
  public void testContactDetail() {

    Set<Contacts> before = app.contact().allС();
    Contacts detailedContact = before.iterator().next();
    int index = before.size() - 1;
    Contacts contact = new Contacts().withId(detailedContact.getId())
            .withFirstname("Elena").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
            .withGroup("[NONE]");
    app.contact().detail( contact, false);
    app.goTo().HomePage();
    List<Contacts> after = app.contact().listС();
    Assert.assertEquals(after.size(), before.size());

    before.remove(detailedContact);
    before.add(contact);
    Assert.assertEquals(before, after);
  }


}
