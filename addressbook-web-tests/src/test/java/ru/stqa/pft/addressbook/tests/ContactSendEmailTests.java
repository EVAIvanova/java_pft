package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactSendEmailTests extends TestBase{

  @Test
  public void testContactSendEmail () {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContacts("5");
    app.getContactHelper().sendEmailContacts();
  }

}
