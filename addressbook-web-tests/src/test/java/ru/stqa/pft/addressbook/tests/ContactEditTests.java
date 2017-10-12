package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactEditTests extends TestBase {

  @Test
  public void testContactEdit() {

    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContacts("8");
    app.getContactHelper().editContact ();
    app.getContactHelper().submitContactEdit();
    app.getNavigationHelper().gotoHomePage();
  }
}
