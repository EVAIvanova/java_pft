package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;

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

  public void selectContacts() {
    click(By.name("selected[]"));

  }


  public void deleteContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));

  }

  public void sendEmailContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[1]/input"));
  }

  public void editContact() {
    click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[8]/a/img"));
  }

  public void submitContactEdit() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }

  public void detailContact() {
    click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[7]/a/img"));
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
}


