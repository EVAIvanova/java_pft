package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object []> validContacts () throws IOException {
    List<Object []> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(new File ("src/test/resources/contact.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add (new Object[] {new Contactdata().withFirstname(split[0]).withLastname(split[1]).withAddress(split[2])
      .withEmail(split[3]).withMobilePhone(split[4]).withGroup(split[5])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test (dataProvider = "validContacts")
  public void ContactCreationTests(Contactdata contact) {
      app.goTo().HomePage();
      Contacts before = app.contact().allС();
      File photo = new File("src/test/resources/WIN_20171006_09_52_45_Pro.jpg");
      String a = contact.getGroup();
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
