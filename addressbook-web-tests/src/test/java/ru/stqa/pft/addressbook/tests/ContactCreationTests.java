package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void ContactCreationTests() {

    app.getNavigationHelper().gotoHomePage();
    List<Contacts> before = app.getContactHelper().getContactList();
    Contacts contact = new Contacts("Elena", "Vasilievna", "Voskresenskaya", "Dr of Ph", "OSEU", "Lvovskaya Street, 15", "7472304", null, "skyLena1@ya.ru", null, "23", "SEPTEMBER", "2016", "[NONE]", "Lvovskaya Street, 15b", null);
    app.getContactHelper().createContact(contact, true);
    app.getNavigationHelper().gotoHomePage();
    List<Contacts> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size()+1);


    contact.setId(after.stream().max((Comparator<Contacts>) (o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add (contact);
    Assert.assertEquals(new HashSet<Object>(before),new HashSet<Object>(after));
  }


}
