package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void ContactsForm(Contacts contact, boolean creation) {
    fillFMLForm(contact.getFirstname(), contact.getMiddlename(), contact.getLastname());
    fillTitleForm(contact.getDr_of_ph());
    fillCompanyForm(contact.getOseu());
    fillAddressForm(contact.getAddress());
    fillHomePhoneForm(contact.getHomephone());
    fillMobileForm(contact.getMobile());
    fillEmailForm(contact.getEmail());
    fillEmail2Form(contact.getEmail2());
    fillBirthdayForm(contact);

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contact.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

    fillAddress2Form(contact.getAddress2());
    fillPhone2Form(contact.getPhone2());
  }

  public void submitCreationContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillPhone2Form(String phone2) {
    type(By.name("phone2"), phone2);

  }

  public void fillAddress2Form(String address2) {
    type(By.name("address2"), address2);

  }

  public void fillBirthdayForm(Contacts contact)
  //String bday,String bmonth,String byear)

  {

    new Select(wd.findElement((By.name("bday")))).selectByVisibleText(contact.getBirthday());
    new Select(wd.findElement((By.name("bmonth")))).selectByVisibleText(contact.getBirthmonth());
    type(By.name("byear"), contact.getBirthyear());
  }

  public void fillEmail2Form(String email2) {
    type(By.name("email2"), email2);

  }

  public void fillEmailForm(String email) {
    type(By.name("email"), email);
  }

  public void fillMobileForm(String mobile) {
    type(By.name("mobile"), mobile);
  }

  public void fillHomePhoneForm(String homephone) {
    type(By.name("home"), homephone);
  }

  public void fillAddressForm(String address) {
    type(By.name("address"), address);
  }

  public void fillCompanyForm(String company) {
    type(By.name("company"), company);
  }

  public void fillTitleForm(String title) {
    type(By.name("title"), title);
  }

  public void fillFMLForm(String firstname, String middlename, String lastname) {
    type(By.name("firstname"), firstname);
    type(By.name("middlename"), middlename);
    type(By.name("lastname"), lastname);
  }

  public void selectContacts(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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

  public void detailContact(int id) {
    wd.findElement(By.cssSelector("a[href='view.php?id="+id+"']")).click();
  }

  public void modifyContact() {
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

  public void createContact(Contacts contact, boolean b) {
    gotoNewContactCreationPage();
    ContactsForm(contact, b);
    submitCreationContact();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<Contacts> getContactList() {
    List<Contacts> contacts = new ArrayList<Contacts>();
    List<WebElement> strings = wd.findElements(By.cssSelector("tr"));
    int sizeStrings = strings.size() - 1;
    int idContact = 0;

     if (sizeStrings == 0) {
      return contacts;
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
        Contacts contact = new Contacts(idContact, fields.get(2), null, fields.get(1), null, null, fields.get(3), fields.get(5), null, fields.get(4), null, null, null, null, null, null, null);
        contacts.add(contact);
        fields.clear();
        i = 0;
      }
    }
    return contacts;
  }


}



