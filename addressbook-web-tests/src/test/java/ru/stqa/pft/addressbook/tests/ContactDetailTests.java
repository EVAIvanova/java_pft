package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDetailTests extends TestBase {

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
  public void testContactDetail() {

    Contacts before = app.contact().allС();
    Contactdata detailedContact = before.iterator().next();
    int index = before.size() - 1;
    Contactdata contact = new Contactdata().withId(detailedContact.getId())
            .withFirstname("Elena").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withMobilePhone("7472304").withEmail("skyLena1@ya.ru")
            .withGroup("[NONE]");
    app.contact().detail( contact, false);
    app.goTo().HomePage();
    assertThat (app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().allС();
    assertThat(after, equalTo(before.withOut(detailedContact).withAdded(contact)));
  }


}
