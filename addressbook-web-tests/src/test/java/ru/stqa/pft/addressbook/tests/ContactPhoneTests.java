package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{

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
  public void testContactPhones () {
    app.goTo().HomePage();
    Contactdata contact = app.contact().allС().iterator().next();
    Contactdata contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
    assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
    assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
  }

  public String cleaned (String phone) {
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
