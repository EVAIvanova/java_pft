package ru.stqa.pft.addressbook.model;

public class Contacts {
  private int id = Integer.MAX_VALUE;;
  private String firstname;
  private String lastname;
  private String address;
  private String mobile;
  private String email;
  private String group;

  public Contacts withId(int id) {
    this.id = id;
    return this;
  }
  public Contacts withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public Contacts withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public Contacts withAddress(String address) {
    this.address = address;
    return this;
  }

  public Contacts withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public Contacts withEmail(String email) {
    this.email = email;
    return this;
  }

  public Contacts withGroup(String group) {
    this.group = group;
    return this;
  }

  public int getId() {    return id;  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {return lastname;  }

  public String getAddress() {
    return address;
  }

  public String getMobile() { return mobile;  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
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
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", mobile='" + mobile + '\'' +
            ", email='" + email + '\'' +
            '}';
  }
}
