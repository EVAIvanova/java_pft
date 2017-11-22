package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactDetailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("Test1"));

    }
  }

  @BeforeMethod
  public void ensurePrecondition() {

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
  public void testContactDetail() {
    Groups groups = app.db().groups();
    Contacts before = app.db().contacts();
    Contactdata detailedContact = before.iterator().next();
    int index = before.size() - 1;
    Contactdata contact = new Contactdata().withId(detailedContact.getId())
            .withFirstname("Elena").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withHomePhone("7472304").withMobilePhone("0966514669")
            .withWorkPhone("353748").withEmail("skyLena1@ya.ru")
            .inGroup(groups.iterator().next());
    app.goTo().HomePage();
    app.contact().detail( contact, false);
    app.goTo().HomePage();
    assertThat (app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.withOut(detailedContact).withAdded(contact)));
    verifyContactsListInUi();
  }


}
