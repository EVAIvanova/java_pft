package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void ContactCreationTests() {

    app.goTo().HomePage();
    Set<Contacts> before = app.contact().allС();
    Contacts contact = new Contacts().withFirstname("Elena").withLastname("Voskresenskaya")
            .withAddress("Lvovskaya Street, 15").withMobile("7472304").withEmail("skyLena1@ya.ru")
            .withGroup("[NONE]");
    app.contact().createС(contact, true);
    app.goTo().HomePage();
    Set<Contacts> after = app.contact().allС();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(contact);
    contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}
