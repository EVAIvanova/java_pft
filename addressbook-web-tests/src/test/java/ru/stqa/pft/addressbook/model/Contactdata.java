package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;

@XStreamAlias("contact")

public class Contactdata {
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;;
  @Expose
  private String firstname;
  @Expose
  private String lastname;
  @Expose
  private String address;
  @Expose
  private String homePhone;
  @Expose
  private String mobilePhone;
  @Expose
  private String workPhone;
  @Expose
  private String email;
  @Expose
  private String email2;
  @Expose
  private String email3;
  @Expose
  private String group;
  @Expose
  private String allphones;
  @Expose
  private String allemails;
  @Expose
  private File photo;

  public File getPhoto() {
    return photo;
  }

  public Contactdata withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

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

  public Contactdata withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public Contactdata withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public Contactdata withAllemails(String allemails) {
    this.allemails = allemails;
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

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getEmail() {
    return email;
  }

  public String getAllemails() { return allemails;  }

  public String getGroup() {
    return group;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Contactdata that = (Contactdata) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
    return address != null ? address.equals(that.address) : that.address == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {
    return "Contactdata{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", address='" + address + '\'' +
            '}';
  }

}
