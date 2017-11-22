package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {


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
  public void testContactEmails() {

    app.goTo().HomePage();
    Contactdata contact = app.db().contacts().iterator().next();
    app.goTo().HomePage();
    Contactdata contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllemails(), equalTo(nergeEmails(contactInfoFromEditForm)));
  }

  private String nergeEmails(Contactdata contact) {
   return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream()
            .filter((s -> !s.equals(""))).collect(Collectors.joining("\n"));
  }
}
