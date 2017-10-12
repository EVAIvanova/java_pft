package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDetailTests extends TestBase {

  @Test
  public void testContactDetail () {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContacts("5");
    app.getContactHelper().detailContact ();
    app.getContactHelper().modifyContact ();
    app.getContactHelper().submitContactEdit();
    app.getNavigationHelper().gotoHomePage();
}
}
