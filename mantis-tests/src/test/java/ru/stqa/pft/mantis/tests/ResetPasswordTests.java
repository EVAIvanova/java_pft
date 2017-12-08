package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.testng.AssertJUnit.assertTrue;

public class ResetPasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException, MessagingException {

    UserData adminUser = new UserData().withUsername("administrator").withPassword("root").withId(1);
    int adminId = adminUser.getId();
    app.resetPassword().login(adminUser);
    app.goTo().ManageUsersPage();
    Users users = app.db().users();
    Users users1 = new Users(users.stream().filter((m) -> m.getId() != (adminId))
            .collect(Collectors.toSet()));
    UserData user = users1.iterator().next();
    app.resetPassword().clickUser(user.getId());
    app.resetPassword().clickResetPassword();
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String confirmationLink = findConfirmationLink(mailMessages, user.getEmail1());
    String newPassword = "Gena";
    app.registration().finish(confirmationLink, newPassword);
    assertTrue(app.newSession().login(user.getUsername(), newPassword));


  }


  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();

  }
}
