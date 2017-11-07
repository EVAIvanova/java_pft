package ru.stqa.pft.addressbook.model;

public class Contactdata {
  private int id = Integer.MAX_VALUE;;
  private String firstname;
  private String lastname;
  private String address;
  private String homePhone;
  private String mobilePhone;
  private String workPhone;
  private String email;
  private String group;
  private String allphones;
  public Contactdata withId(int id) {
    this.id = id;
    return this;
  }
  public Contactdata withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public Contactdata withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public Contactdata withAddress(String address) {
    this.address = address;
    return this;
  }

  public Contactdata withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public Contactdata withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public Contactdata withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public Contactdata withEmail(String email) {
    this.email = email;
    return this;
  }

  public Contactdata withGroup(String group) {
    this.group = group;
    return this;
  }
  public Contactdata withAllPhones(String allphones) {
    this.allphones = allphones;
    return this;
  }

  public String getAllphones() { return allphones;  }

  public int getId() {    return id;  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {return lastname;  }

  public String getAddress() {
    return address;
  }

  public String getHomePhone() { return homePhone;  }

  public String getMobilePhone() { return mobilePhone;  }

  public String getWorkPhone() { return workPhone;  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }


  @Override
  public String toString() {
    return "Contactdata{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", email='" + email + '\'' +
            ", allphones='" + allphones + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contactdata that = (Contactdata) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    if (address != null ? !address.equals(that.address) : that.address != null) return false;
    if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
    if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
    if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
    if (email != null ? !email.equals(that.email) : that.email != null) return false;
    return allphones != null ? allphones.equals(that.allphones) : that.allphones == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
    result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
    result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
    result = 31 * result + (email != null ? email.hashCode() : 0);
    result = 31 * result + (allphones != null ? allphones.hashCode() : 0);
    return result;
  }


}
