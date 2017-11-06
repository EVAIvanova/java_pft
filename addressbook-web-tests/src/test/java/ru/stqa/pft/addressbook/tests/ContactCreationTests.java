package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void ContactCreationTests() {

    app.goTo().HomePage();
    Contacts before = app.contact().allС();
    Contactdata contact = new Contactdata().withFirstname("Elena").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
            .withGroup("[NONE]");
    app.contact().createС(contact, true);
    app.goTo().HomePage();
    assertThat (app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().allС();
    assertThat (after, equalTo(before
            .withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test
  public void ContactBadCreationTests() {

    app.goTo().HomePage();
    Contacts before = app.contact().allС();
    Contactdata contact = new Contactdata().withFirstname("Elena'").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
            .withGroup("[NONE]");
    app.contact().createС(contact, true);
    app.goTo().HomePage();
    assertThat (app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().allС();
    assertThat (after, equalTo(before));
  }
}
