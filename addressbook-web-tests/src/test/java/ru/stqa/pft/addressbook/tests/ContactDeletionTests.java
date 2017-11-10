package ru.stqa.pft.addressbook.tests;

import org.hamcrest.junit.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePrecondition1() {

    app.goTo().HomePage();
    if (app.contact().allС().size() == 0) {
      app.contact().createС(new Contactdata()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withHomePhone("7472304").withMobilePhone("0966514669")
              .withWorkPhone("353748").withEmail("skyLena1@ya.ru")
              .withGroup("[NONE]"), true);
    }
  }

  @Test
  public void testContactDeletion() {

    Contacts before = app.contact().allС();
    Contactdata deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.getSessionHelper().closeDeletionWindow();
    assertThat (app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().allС();
    assertThat(after, equalTo (before.withOut(deletedContact)));
  }


}
