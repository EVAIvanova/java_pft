package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void ContactCreationTests() {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().createContact(new Contacts("Elena", "Vasilievna", "Voskresenskaya", "Dr of Ph", "OSEU", "Lvovskaya Street, 15", "7472304", "0966514669", "skyLena1@ya.ru", "EVIvanovaRP@ya.ru", "23", "SEPTEMBER", "2016", "[NONE]", "Lvovskaya Street, 15b", "7472304"), true);

  }


}
