package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object []> validContactsFromXml () throws IOException {
   try (BufferedReader reader = new BufferedReader
           (new FileReader(new File ("src/test/resources/contact.xml")))) {
     String xml = "";
     String line = reader.readLine();
     while (line != null) {
       xml += line;
       line = reader.readLine();
     }
     XStream xstream = new XStream();
     xstream.processAnnotations(Contactdata.class);
     List<Contactdata> contacts = (List<Contactdata>) xstream.fromXML(xml);
     return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
   }
  }
  @DataProvider
  public Iterator<Object []> validContactsFromJson () throws IOException {
   try  (BufferedReader reader = new BufferedReader
           (new FileReader(new File ("src/test/resources/contact.json")))) {
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<Contactdata> contacts = gson.fromJson(json,new TypeToken<List<Contactdata>>() {}.getType ());
    return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
   }
  }
  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("Test1"));

    }
  }

  @Test (dataProvider = "validContactsFromJson")
  public void ContactCreationTests(Contactdata contact) {
      Groups groups = app.db().groups();
       File photo = new File("src/test/resources/WIN_20171006_09_52_45_Pro.jpg");
       Contactdata newContact = new Contactdata().withFirstname("Katya").withLastname("Ivanova").withPhoto(photo)
               .inGroup(groups.iterator().next());
       app.goTo().HomePage();
      Contacts before = app.db().contacts();
      app.contact().createС(newContact, true);
      app.goTo().HomePage();
      assertThat(app.contact().count(), equalTo(before.size() + 1));
      Contacts after = app.db().contacts();
      assertThat( after, equalTo(before
              .withAdded(contact.withId(after.stream()
                      .mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void ContactBadCreationTests() {
    Groups groups = app.db().groups();
    app.goTo().HomePage();
    Contacts before = app.db().contacts();
    Contactdata contact = new Contactdata().withFirstname("Elena'").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withHomePhone("7472304").withMobilePhone("0966514669")
            .withWorkPhone("353748").withEmail("skyLena1@ya.ru")
            .inGroup(groups.iterator().next());
    app.contact().createС(contact, true);
    app.goTo().HomePage();
    assertThat (app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat (after, equalTo(before));
    verifyContactsListInUi();
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
