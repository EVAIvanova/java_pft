package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class AdditionContactToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
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
  public void testAdditionContactToGroup() {

    app.goTo().HomePage();
    Contacts contacts = app.db().contacts();
    Contactdata contact = contacts.iterator().next();
    Groups groups = app.db().groups();
    GroupData group = groups.iterator().next();
    Contacts before = group.getContacts();
    contact = app.contact().contactForGroup(contacts, contact, before);

    app.contact().additionToGroup(contact, group);
    app.goTo().HomePage();
    app.contact().selectGroupPage(group);
    groups = app.db().groups();
    group = groups.iterator().next();
    Contacts after = group.getContacts();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before
            .withAdded(contact)));


  }






}



