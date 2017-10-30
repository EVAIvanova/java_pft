package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactEditTests extends TestBase {

  @Test
  public void testContactEdit() {

    app.getNavigationHelper().gotoHomePage();

    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new Contacts("Elena", "Vasilievna", "Voskresenskaya", "Dr of Ph", "OSEU", "Lvovskaya Street, 15", "7472304", "0966514669", "skyLena1@ya.ru", "EVIvanovaRP@ya.ru", "23","SEPTEMBER","2016","Test1", "Lvovskaya Street, 15b","7472304"),true);
    }
    List<Contacts> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContacts(before.size()-1);
    app.getContactHelper().editContact ();
    app.getContactHelper().ContactsForm(new Contacts("Elena", "Vasilievna", "Voskresenskaya", "Dr of Ph", "OSEU", "Lvovskaya Street, 15", "7472304", "0966514669", "skyLena1@ya.ru", "EVIvanovaRP@ya.ru", "23","SEPTEMBER", "2016","Test1","Lvovskaya Street, 15b", "7472304"),false);
    app.getContactHelper().submitContactEdit();
    app.getNavigationHelper().gotoHomePage();
    List<Contacts> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(),before.size());
  }
}
