package ru.stqa.pft.mantis.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//@XStreamAlias("")
@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose

    @Column(name = "username")
 //   @Type(type = "text")
    private String username;

    @Expose
    @Column(name = "realname")
 //   @Type(type = "text")
    private String realname;


  @Expose
  @Column(name = "email")
 // @Type(type = "text")
  private String email1;

  @Expose
  @Column(name = "password")
 // @Type(type = "text")
  private String password;

  public int getId() {
    return id;
  }

  public UserData withId(int id) {
    this.id = id;
    return this;
  }

  @Override
  public String toString() {
    return "UserData{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    UserData userData = (UserData) o;

    if (id != userData.id) return false;
    if (username != null ? !username.equals(userData.username) : userData.username != null) return false;
    return password != null ? password.equals(userData.password) : userData.password == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (username != null ? username.hashCode() : 0);
    result = 31 * result + (password != null ? password.hashCode() : 0);
    return result;
  }

  public String getUsername() {
    return username;
  }

  public UserData withUsername(String username) {
    this.username = username;
    return this;
  }

  public String getRealname() {
    return realname;
  }

  public UserData withRealname(String realname) {
    this.realname = realname;
    return this;
  }

  public String getEmail1() {
    return email1;
  }

  public UserData withEmail1(String email1) {
    this.email1 = email1;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public UserData withPassword(String password) {
    this.password = password;
    return this;
  }

}
