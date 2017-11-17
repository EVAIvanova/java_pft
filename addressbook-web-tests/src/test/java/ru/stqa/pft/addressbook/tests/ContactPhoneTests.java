package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

  @BeforeMethod
  public void ensurePrecondition1() {

    if (app.db().contacts().size() == 0) {
      app.goTo().HomePage();
      app.contact().createС(new Contactdata()
              .withFirstname("Elena").withLastname("Voskresenskaya")
              .withAddress("Lvovskaya Street, 15").withHomePhone("7472304").withMobilePhone("0966514669")
              .withWorkPhone("353748").withEmail("skyLena1@ya.ru")
              .withGroup("[NONE]"), true);
    }
  }

  @Test (enabled = false)
  public void testContactPhones () {
    app.goTo().HomePage();
    Contactdata contact = app.db().contacts().iterator().next();
    app.goTo().HomePage();
    Contactdata contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getAllphones(), equalTo(nergePhones(contactInfoFromEditForm)));
  }

  private String nergePhones(Contactdata contact) {
   return Arrays.asList(contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone())
           .stream().filter((s) -> !s.equals(""))
           .map(ContactPhoneTests :: cleaned )
           .collect(Collectors.joining("\n"));
    }

  public static String cleaned (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
