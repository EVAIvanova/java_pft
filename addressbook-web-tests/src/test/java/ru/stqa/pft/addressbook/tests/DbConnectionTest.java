package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DbConnectionTest {

  @Test
  public void testDbConnection () {
    Connection conn = null;

    try {
      conn =
              DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?user=root&password=");
      Statement st = conn.createStatement();
      Statement stC = conn.createStatement();
      Statement stC_Gr = conn.createStatement();
      ResultSet rs = st.executeQuery("select group_id,group_name,group_header,group_footer from group_list");
      ResultSet rsC = stC.executeQuery("select id,firstname,lastname,address,home,mobile,work,email from addressbook");
      Contacts contacts = new Contacts();
      while (rsC.next()) {
        contacts.add(new Contactdata().withId(rsC.getInt("id")).withFirstname(rsC.getString("firstname"))
                .withLastname(rsC.getString("lastname")).withAddress(rsC.getString("address"))
                .withHomePhone(rsC.getString("home")).withMobilePhone(rsC.getString("mobile"))
                .withWorkPhone(rsC.getString("work")).withEmail(rsC.getString("email")));
      }
      Groups groups = new Groups();
      while (rs.next()) {
        groups.add(new GroupData().withId(rs.getInt("group_id")).withName(rs.getString("group_name"))
                .withHeader(rs.getString("group_header"))
                .withFooter(rs.getString("group_footer")));
      }
      rs.close();
      rsC.close();
      st.close();
      conn.close();
      System.out.println(contacts);
      System.out.println(groups);


    } catch (SQLException ex) {
      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
