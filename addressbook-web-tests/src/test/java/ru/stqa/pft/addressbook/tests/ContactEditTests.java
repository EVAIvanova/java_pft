package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEditTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition() {

    app.goTo().HomePage();
    if (app.contact().allС().size() == 0) {
      app.contact().createС(new Contactdata()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
              .withGroup("[NONE]"), true);
    }
  }

  @Test
  public void testContactEdit() {
    Contacts before = app.contact().allС();
    Contactdata modifiedContact = before.iterator().next();
    Contactdata contact = new Contactdata().withId(modifiedContact.getId())
            .withFirstname("Elena").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
            .withGroup("[NONE]");
    app.contact().modifyС(contact, false);
    app.goTo().HomePage();
    assertThat (app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().allС();
    assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
  }


}
