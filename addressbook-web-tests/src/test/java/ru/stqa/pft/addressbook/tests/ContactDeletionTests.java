package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
public class ContactDeletionTests extends TestBase {


  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContacts("4");
    app.getContactHelper().deleteContacts();
    app.getSessionHelper().closeDeletionWindow();

  }

}
