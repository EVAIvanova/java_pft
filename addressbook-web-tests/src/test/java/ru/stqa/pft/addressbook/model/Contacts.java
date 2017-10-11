package ru.stqa.pft.addressbook.model;

public class Contacts {
  private final String firstname;
  private final String middlename;
  private final String lastname;
  private final String dr_of_ph;
  private final String oseu;
  private final String address;
  private final String homephone;
  private final String mobile;
  private final String email;
  private final String email2;
  private final String address2;
  private final String phone2;

  public Contacts(String firstname, String middlename, String lastname,String dr_of_ph, String oseu, String address, String homephone, String mobile, String email, String email2, String address2, String phone2) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.lastname = lastname;
    this.dr_of_ph = dr_of_ph;
    this.oseu = oseu;
    this.address = address;
    this.homephone = homephone;
    this.mobile = mobile;
    this.email = email;
    this.email2 = email2;
    this.address2 = address2;
    this.phone2 = phone2;
  }
  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getLastname() {return lastname;  }
  public String getDr_of_ph() {
    return dr_of_ph;
  }

  public String getOseu() {
    return oseu;
  }

  public String getAddress() {
    return address;
  }

  public String getHomephone() {
    return homephone;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getAddress2() {
    return address2;
  }

  public String getPhone2() {
    return phone2;
  }
}
