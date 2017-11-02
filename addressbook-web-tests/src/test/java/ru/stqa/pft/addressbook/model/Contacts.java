package ru.stqa.pft.addressbook.model;

public class Contacts {
  private int id;
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
  private final String birthday;
  private final String birthmonth;
  private final String birthyear;
  private final String group;
  private final String address2;
  private final String phone2;

  public Contacts(int id, String firstname, String middlename, String lastname, String dr_of_ph, String oseu, String address, String homephone, String mobile, String email, String email2, String birthday, String birthmonth, String birthyear,String group, String address2, String phone2) {
    this.id = id;
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
    this.birthday = birthday;
    this.birthmonth = birthmonth;
    this.birthyear = birthyear;
    this.group = group;
    this.address2 = address2;
    this.phone2 = phone2;
  }
  public Contacts(String firstname, String middlename, String lastname, String dr_of_ph, String oseu, String address, String homephone, String mobile, String email, String email2, String birthday, String birthmonth, String birthyear,String group, String address2, String phone2) {
    this.id = Integer.MAX_VALUE;
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
    this.birthday = birthday;
    this.birthmonth = birthmonth;
    this.birthyear = birthyear;
    this.group = group;
    this.address2 = address2;
    this.phone2 = phone2;
  }

  public int getId() {    return id;  }

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

  public String getGroup() {
    return group;
  }

  public String getBirthday() {
    return birthday;
  }

  public String getBirthmonth() {
    return birthmonth;
  }

  public String getBirthyear() {
    return birthyear;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contacts contacts = (Contacts) o;

    if (firstname != null ? !firstname.equals(contacts.firstname) : contacts.firstname != null) return false;
    if (lastname != null ? !lastname.equals(contacts.lastname) : contacts.lastname != null) return false;
    if (address != null ? !address.equals(contacts.address) : contacts.address != null) return false;
    if (mobile != null ? !mobile.equals(contacts.mobile) : contacts.mobile != null) return false;
    return email != null ? email.equals(contacts.email) : contacts.email == null;
  }

  @Override
  public int hashCode() {
    int result = firstname != null ? firstname.hashCode() : 0;
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Contacts{" +
            "id='" + id + '\'' +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", homephone='" + homephone + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
