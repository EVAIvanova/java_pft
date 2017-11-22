package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void ContactsForm(Contactdata contact, boolean creation) {
    fillFMLForm(contact.getFirstname(), contact.getLastname());
    attach(By.name("photo"), contact.getPhoto());
    fillAddressForm(contact.getAddress());
    fillHomeForm(contact.getHomePhone());
    fillMobileForm(contact.getMobilePhone());
    fillWorkForm(contact.getWorkPhone());
    fillEmailForm(contact.getEmail());

    if (creation) {
      if (contact.getGroups().size() > 0) {
        Assert.assertTrue(contact.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }


  }




  public void submitCreationContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillEmailForm(String email) {
    type(By.name("email"), email);
  }

  public void fillHomeForm(String homePhone) {type(By.name("home"), homePhone); }

  public void fillMobileForm(String mobilePhone) {
    type(By.name("mobile"), mobilePhone);
  }

  public void fillWorkForm(String workPhone) { type(By.name("work"), workPhone); }

  public void fillAddressForm(String address) {
    type(By.name("address"), address);
  }



  public void fillFMLForm(String firstname, String lastname) {
    type(By.name("firstname"), firstname);
    type(By.name("lastname"), lastname);
  }

  public void selectContacts(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectContactsById(int id) {
    wd.findElement(By.cssSelector("input[value='"+id+"']")).click();
  }

  public void deleteContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));

  }

  public void sendEmailContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[1]/input"));
  }

  public void editContact(int id) {

    wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();

  }

  private void initContactModificationById(int id) {
    wd.findElement(By.cssSelector("a[href='edit.php?id="+id+"']")).click();

   // wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
   // wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click();

   // WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[@value='%s']",id)));
   // WebElement row = wd.findElement(By.xpath("./../.."));
   // List<WebElement> cells = row.findElements(By.tagName("td"));
    //cells.get(7).findElement (By.tagName("a")).click();
  }

  public void submitContactEdit() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void modifyС(Contactdata contact, boolean creation) {
    selectContactsById(contact.getId());
    editContact (contact.getId());
    ContactsForm(contact,creation);
    submitContactEdit();
    contactCache=null;
      }

  public void detailContact(int id) {
    wd.findElement(By.cssSelector("a[href='view.php?id="+id+"']")).click();
  }

  public void detail(Contactdata contact, boolean creation) {
    selectContactsById(contact.getId());
    detailContact (contact.getId());
    clickModify();
    ContactsForm(contact,creation);
    submitContactEdit();
    contactCache=null;
  }

  public void clickModify() {
    click(By.name("modifiy"));
  }

  public void gotoNewContactCreationPage() {
    if (isElementPresent(By.name("firstname"))
            && wd.findElement(By.name("firstname")).getText().equals("FIRSTNAME")) {
      return;
    } else {
      wd.findElement(By.linkText("ADD_NEW")).click();
    }
  }

  public void createС(Contactdata contact, boolean b) {
    gotoNewContactCreationPage();
    ContactsForm(contact, b);
    submitCreationContact();
    contactCache=null;
  }

  public void delete(Contactdata deletedContact) {
    selectContactsById (deletedContact.getId());
    deleteContacts();
    contactCache=null;
  }




  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    if (isElementPresent(By.name("selected[]")) == true) {
    return wd.findElements(By.name("selected[]")).size();}
    else {
      return 0;
    }
  }

  private Contacts contactCache = null;

  public Contacts allС() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> rows = wd.findElements(By.name("entry"));
    for (WebElement row : rows) {
    List<WebElement> cells = row.findElements(By.tagName("td"));
    int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cells.get(1).getText();
      String firstname = cells.get(2).getText();
      String address = cells.get(3).getText();
      String allemails = cells.get(4).getText();
      String Allphones = cells.get(5).getText();
      contactCache.add (new Contactdata().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAddress(address).withAllPhones(Allphones).withAllemails(allemails));

      }
      return new Contacts(contactCache);

  }


  public Contactdata infoFromEditForm(Contactdata contact) {
  initContactModificationById (contact.getId());
  String firstname = wd. findElement(By.name("firstname")).getAttribute("value");
  String lastname = wd. findElement(By.name("lastname")).getAttribute("value");
  String address = wd. findElement(By.name("address")).getAttribute("value");
  String email = wd. findElement(By.name("email")).getAttribute("value");
    String email2 = wd. findElement(By.name("email2")).getAttribute("value");
    String email3 = wd. findElement(By.name("email3")).getAttribute("value");
  String home = wd. findElement(By.name("home")).getAttribute("value");
  String mobile = wd. findElement(By.name("mobile")).getAttribute("value");
  String work = wd. findElement(By.name("work")).getAttribute("value");
  wd.navigate().back();
  return new Contactdata().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
          .withAddress(address).withEmail(email).withEmail2(email2).withEmail3(email3)
          .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
  }


  public void submitAddToGroup() {
    wd.findElement(By.name("add")).click();
  }

  public void selectGroup(GroupData group) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
  }

  public GroupData selectGroupPage(GroupData group) {
   // String[] selectGroup = wd.findElement(By.xpath("//form[@id='right']")).getText().split("\n");
         new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
      return group;
    }


  public Contactdata contactForGroup(Contacts contacts, Contactdata contact, Contacts before) {
    Contacts contacts1 = new Contacts();
    contacts1=contacts;
    for (Contactdata contactBefore : before) {
      for (Contactdata contactNoGroup : contacts) {
        if (contactBefore.equals(contactNoGroup)) {
          contacts1 = contacts1.withOut(contactBefore);
          contact = contacts1.iterator().next();
        }
      }
    }
    return contact;
  }

  public void additionToGroup(Contactdata contact, GroupData group) {
   selectContactsById(contact.getId());
   selectGroup(group);
   submitAddToGroup();
    contactCache=null;
  }

  public void deleteContactFromGroup(Contactdata contact) {
    selectContactsById (contact.getId());
    deleteContactsFromGroup();
    contactCache=null;
  }

  private void deleteContactsFromGroup() {
    wd.findElement(By.cssSelector("input[value='DELETE']")).click();
  }
}



