package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

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
    Contacts contactsi = contacts;
    Contactdata contact = new Contactdata();
    contact = null;
    GroupData group = new GroupData();
    Groups groups = app.db().groups();
    Contacts before = new Contacts();
    int j = 0;
    for (GroupData groupi : groups) {
      if (contact == null) {
        before = groupi.getContacts();
        Contacts beforei = before;
        for (Contactdata contacti : contacts) {
          int i = 0;
          while ( beforei.size() != 0) {
            Contactdata b = beforei.iterator().next();
            if (contacti.equals(b)) {
              beforei = beforei.withOut(b);
              if (beforei.size() == 0) {i = 10;}
              break;}
             else {
               i++;
            }
          }
           if ((beforei.size() == 0) && (i == 0)) {
            contact = contacti;
            group = groupi;
            break;
          }
        }
        if ((j + 1) == groups.size()) {
          app.goTo().GroupPage();
          GroupData group1 = new GroupData().withName("Test" +  (j+2));
          app.group().create(group1);
          contact = contacts.iterator().next();
          groups = app.db().groups();
          group = group1.withId(groups.stream().mapToInt((g) -> g.getId()).max().getAsInt());
          before = group.getContacts();
        }
        j++;
      } else {
        break;
      }
    }
    app.goTo().HomePage();
    app.contact().additionToGroup(contact, group);
    app.goTo().HomePage();
    app.contact().selectGroupPage(group);
    groups = app.db().groups();
    Contacts contactsFromGroup = app.contact().allС();
    group = groups.iterator().next().withId(groups.stream().mapToInt((g) -> g.getId()).max()
            .getAsInt()).withName(group.getName()).withFooter(group.getFooter())
            .withHeader(group.getHeader()).withContact1(contactsFromGroup);
    Contacts after = group.getContacts();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    assertThat(after, equalTo(before
            .withAdded(contact)));


  }


}



