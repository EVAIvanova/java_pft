package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class ContactEditTests extends TestBase {

  @Test
  public void testContactEdit() {

    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new Contacts("Elena", "Vasilievna", "Voskresenskaya", "Dr of Ph", "OSEU", "Lvovskaya Street, 15", "7472304", null, "skyLena1@ya.ru", null, "23","SEPTEMBER","2016","Test1", "Lvovskaya Street, 15b",null),true);
    }
    List<Contacts> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContacts(before.size()-1);
    app.getContactHelper().editContact (before.get(before.size()-1).getId());
    Contacts contact = new Contacts (before.get(before.size()-1).getId(),"Elena", "Vasilievna", "Voskresenskaya", "Dr of Ph", "OSEU", "Lvovskaya Street, 15", "7472304", null, "skyLena1@ya.ru", null, "23","SEPTEMBER", "2016","Test1","Lvovskaya Street, 15b", null);
    app.getContactHelper().ContactsForm(contact,false);
    app.getContactHelper().submitContactEdit();
    app.getNavigationHelper().gotoHomePage();
    List<Contacts> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());

    before.remove(before.size()-1);
    before.add (contact);
    Assert.assertEquals (new HashSet<Object>(before),new HashSet<Object>(after));
  }
}
