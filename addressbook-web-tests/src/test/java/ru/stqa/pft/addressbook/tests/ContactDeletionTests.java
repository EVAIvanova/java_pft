package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    int before = app.getContactHelper().getContactCount();
      if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new Contacts("Elena", "Vasilievna", "Voskresenskaya", "Dr of Ph", "OSEU", "Lvovskaya Street, 15", "7472304", "0966514669", "skyLena1@ya.ru", "EVIvanovaRP@ya.ru", "23","SEPTEMBER","2016","Test1", "Lvovskaya Street, 15b","7472304"),true);
    }
    app.getContactHelper().selectContacts();
    app.getContactHelper().deleteContacts();
    app.getSessionHelper().closeDeletionWindow();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after,before-1);

  }

}
