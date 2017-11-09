package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object []> validContacts () {
    List<Object []> list = new ArrayList<Object[]>();
    list.add(new Object[] {new Contactdata().withFirstname("Elena").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya, 15").withEmail("skyLena1@ya.ru").withMobilePhone("0966514669")});
    list.add(new Object[] {new Contactdata().withFirstname("Ekaterina").withLastname("Ivanova")
            .withAddress("Vilyamsa, 46").withEmail("skyKatya1@ya.ru").withMobilePhone("0986514669")});
    list.add(new Object[] {new Contactdata().withFirstname("Aleksandra").withLastname("Ivanova")
            .withAddress("Glushko, 3").withEmail("skyAlex1@ya.ru").withMobilePhone("0666514669")});
    return list.iterator();
  }

  @Test (dataProvider = "validContacts")
  public void ContactCreationTests(Contactdata contact) {
      app.goTo().HomePage();
      Contacts before = app.contact().allС();
      File photo = new File("src/test/resources/WIN_20171006_09_52_45_Pro.jpg");
      app.contact().createС(contact, true);
      app.goTo().HomePage();
      assertThat(app.contact().count(), equalTo(before.size() + 1));
      Contacts after = app.contact().allС();
      assertThat( after, equalTo(before
              .withAdded(contact.withId(after.stream()
                      .mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void ContactBadCreationTests() {

    app.goTo().HomePage();
    Contacts before = app.contact().allС();
    Contactdata contact = new Contactdata().withFirstname("Elena'").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withHomePhone("7472304").withMobilePhone("0966514669")
            .withWorkPhone("353748").withEmail("skyLena1@ya.ru")
            .withGroup("[NONE]");
    app.contact().createС(contact, true);
    app.goTo().HomePage();
    assertThat (app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().allС();
    assertThat (after, equalTo(before));
  }

  @Test (enabled = false)
  public void testCurrentDir () {
    File currentDir = new File (".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File ("src/test/resources/WIN_20171006_09_52_45_Pro.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }
}
