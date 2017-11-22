package ru.stqa.pft.addressbook.tests;

import org.hamcrest.junit.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("Test1"));

    }
  }
  @BeforeMethod
  public void ensurePrecondition1() {
    if (app.db().contacts().size() == 0) {
       app.goTo().HomePage();
      Groups groups = app.db().groups();
       app.contact().createС(new Contactdata()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withHomePhone("7472304").withMobilePhone("0966514669")
              .withWorkPhone("353748").withEmail("skyLena1@ya.ru")
               .inGroup(groups.iterator().next()), true);
    }
  }

  @Test
  public void testContactDeletion() {

    Contacts before = app.db().contacts();
    Contactdata deletedContact = before.iterator().next();
    app.goTo().HomePage();
    app.contact().delete(deletedContact);
    app.getSessionHelper().closeDeletionWindow();
    assertThat (app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo (before.withOut(deletedContact)));
    verifyContactsListInUi();
  }


}
