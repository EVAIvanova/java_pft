package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;

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
    fillAddressForm(contact.getAddress());
    fillMobileForm(contact.getMobile());
    fillEmailForm(contact.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
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

  public void fillMobileForm(String mobile) {
    type(By.name("mobile"), mobile);
  }

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

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCache = null;

  public Contacts allС() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> strings = wd.findElements(By.cssSelector("tr"));
    int sizeStrings = strings.size() - 1;
    int idContact = 0;

    if (sizeStrings == 0) {
      return new Contacts(contactCache);
    }
    List<WebElement> name = wd.findElements(By.cssSelector("td"));
    int sizeName = name.size();
    int n = sizeName / sizeStrings;
    int i = 0;
    List<String> fields = new ArrayList<String>();
    for (WebElement namei : name) {

      if (i < n - 1) {
        if (i==0) {
          idContact = Integer.parseInt(namei.findElement(By.tagName("input")).getAttribute("value"));
        }
        String field = namei.getText();
        fields.add(field);
        i++;

      } else {
        Contactdata contact = new Contactdata()
                .withId(idContact).withFirstname(fields.get(2)).withLastname(fields.get(1))
                .withAddress(fields.get(3)).withMobile(fields.get(5)).withEmail(fields.get(4));
        contactCache.add(contact);
        fields.clear();
        i = 0;
      }
    }
    return new Contacts(contactCache);
  }


}



