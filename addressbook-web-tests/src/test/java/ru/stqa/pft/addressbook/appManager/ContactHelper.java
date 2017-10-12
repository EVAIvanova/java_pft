package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.Contacts;

public class ContactHelper extends HelperBase{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);}




  public void ContactsForm(Contacts contact) {
    fillFMLForm(contact.getFirstname(), contact.getMiddlename(), contact.getLastname());
    fillTitleForm(contact.getDr_of_ph());
    fillCompanyForm(contact.getOseu());
    fillAddressForm(contact.getAddress());
    fillHomePhoneForm(contact.getHomephone());
    fillMobileForm(contact.getMobile());
    fillEmailForm(contact.getEmail());
    fillEmail2Form(contact.getEmail2());
    fillBirthdayForm();
    fillAddress2Form(contact.getAddress2());
    fillPhone2Form(contact.getPhone2());
  }

  public void submitCreationContact() {
   click(By.xpath("//div[@id='content']/form/input[21]"));
     }

  public void fillPhone2Form(String phone2) {
    type(By.name("phone2"),phone2);

  }

  public void fillAddress2Form(String address2) {
    type (By.name("address2"),address2);

  }

  public void fillBirthdayForm() {
    if (!isSelected(By.xpath("//div[@id='content']/form/select[1]//option[25]"))) {
      click(By.xpath("//div[@id='content']/form/select[1]//option[25]"));
          }
    if (!isSelected(By.xpath("//div[@id='content']/form/select[2]//option[10]"))) {
      click(By.xpath("//div[@id='content']/form/select[2]//option[10]"));
    }
   type(By.name("byear"),"1980");
     }

  public void fillEmail2Form(String email2) {
    type(By.name("email2"),email2);

  }

  public void fillEmailForm(String email) {
    type(By.name("email"),email);
         }

  public void fillMobileForm(String mobile) {
    type(By.name("mobile"),mobile);
        }

  public void fillHomePhoneForm(String homephone) {
    type(By.name("home"),homephone);
      }

  public void fillAddressForm(String address) {
   type(By.name("address"),address);
      }

  public void fillCompanyForm(String company) {
   type(By.name("company"),company);
      }

  public void fillTitleForm(String title) {
    type(By.name("title"),title);
     }

  public void fillFMLForm(String firstname, String middlename, String lastname) {
    type(By.name("firstname"),firstname);
    type(By.name("middlename"),middlename);
    type(By.name("lastname"),lastname);
     }

  public void selectContacts(String numberContact) {
    if (!isSelected(By.id(numberContact))) {
      click(By.id(numberContact));
    }
  }


  public void deleteContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));

  }

  public void sendEmailContacts() {
    click(By.xpath("//div[@id='content']/form[2]/div[1]/input"));
  }

  public void editContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[5]/td[8]/a/img"));
  }

  public void submitContactEdit() {
    click(By.xpath("//div[@id='content']/form[1]/input[22]"));
  }
}
