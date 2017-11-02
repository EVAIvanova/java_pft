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



    before.add (contact);
    Comparator<? super Contacts> byId = (g1, g2) -> Integer.compare(g1.getId(),g2.getId());
    before.sort (byId);
    after.sort (byId);
    Assert.assertEquals(before,after);
  }


}
