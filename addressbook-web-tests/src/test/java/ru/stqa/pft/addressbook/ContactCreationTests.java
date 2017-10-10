package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests {
  FirefoxDriver wd;

  @BeforeMethod
  public void setUp() throws Exception {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  private void login(String name, String password) {
    wd.get("http://localhost/addressbook/group.php");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(name);
    wd.findElement(By.name("pass")).click();
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
  }

  @Test
  public void ContactCreationTests() {

    wd.findElement(By.linkText("HOME")).click();
    gotoNewContactCreationPage();
    ContactsForm(new Contacts("Elena", "Vasilievna", "Voskresenskaya", "Dr of Ph", "OSEU", "Lvovskaya Street, 15", "7472304", "0966514669", "skyLena1@ya.ru", "EVIvanovaRP@ya.ru", "Lvovskaya Street, 15b", "7472304"));
    submitCreationContact();
  }

  private void ContactsForm(Contacts contact) {
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

  private void submitCreationContact() {
    wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
  }

  private void fillPhone2Form(String phone2) {
    wd.findElement(By.name("phone2")).click();
    wd.findElement(By.name("phone2")).clear();
    wd.findElement(By.name("phone2")).sendKeys(phone2);
  }

  private void fillAddress2Form(String address2) {
    wd.findElement(By.name("address2")).click();
    wd.findElement(By.name("address2")).clear();
    wd.findElement(By.name("address2")).sendKeys(address2);
  }

  private void fillBirthdayForm() {
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[25]")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[25]")).click();
    }
    if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[10]")).isSelected()) {
      wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[10]")).click();
    }
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys("1980");
  }

  private void fillEmail2Form(String email2) {
    wd.findElement(By.name("email2")).click();
    wd.findElement(By.name("email2")).clear();
    wd.findElement(By.name("email2")).sendKeys(email2);
  }

  private void fillEmailForm(String email) {
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(email);
  }

  private void fillMobileForm(String mobile) {
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(mobile);
  }

  private void fillHomePhoneForm(String homephone) {
    wd.findElement(By.name("home")).click();
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(homephone);
  }

  private void fillAddressForm(String address) {
    wd.findElement(By.name("address")).click();
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(address);
  }

  private void fillCompanyForm(String company) {
    wd.findElement(By.name("company")).click();
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(company);
  }

  private void fillTitleForm(String title) {
    wd.findElement(By.name("title")).click();
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys(title);
  }

  private void fillFMLForm(String firstname, String middlename, String lastname) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(firstname);
    wd.findElement(By.name("middlename")).click();
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys(middlename);
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(lastname);
  }

  private void gotoNewContactCreationPage() {
    wd.findElement(By.linkText("ADD_NEW")).click();
  }

  @AfterMethod
  public void tearDown() {
    wd.quit();
  }

  public static boolean isAlertPresent(FirefoxDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
