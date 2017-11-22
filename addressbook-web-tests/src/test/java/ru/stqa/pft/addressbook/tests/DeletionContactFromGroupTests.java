package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class DeletionContactFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("Test1"));

    }
  }

  @BeforeMethod
  public void ensurePrecondition1() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    Contactdata contact = new Contactdata()
            .withFirstname("Elena").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withHomePhone("7472304").withMobilePhone("0966514669")
            .withWorkPhone("353748").withEmail("skyLena1@ya.ru")
            .inGroup(group);
    if (contacts.size() == 0) {
      app.goTo().HomePage();
      app.contact().createС(contact, true);
    } else {
      contact = contacts.iterator().next();
    }
    if (group.getContacts().size() == 0) {
      app.contact().additionToGroup(contact, group);
    }
  }


@Test

public void testDeletionContactFromGroup(){

        Groups groups=app.db().groups();
        GroupData group=groups.iterator().next();
        Contacts before=group.getContacts();
        app.goTo().HomePage();
        app.contact().selectGroupPage(group);
        Contactdata contact=before.iterator().next();
        app.contact().deleteContactFromGroup(contact);
        app.contact().selectGroupPage(group);
        assertThat(app.contact().count(),equalTo(before.size()-1));
        Contacts after=group.getContacts();
        assertThat(after,equalTo(before.withOut(contact)));

        }
        }
