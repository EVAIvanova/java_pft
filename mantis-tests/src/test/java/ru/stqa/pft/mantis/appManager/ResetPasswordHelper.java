package ru.stqa.pft.mantis.appManager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;

public class ResetPasswordHelper extends HelperBase{

  public ResetPasswordHelper(ApplicationManager app) {
    super(app);
  }

  public void login(UserData user)
  {
    wd.get(app.getProperty("web.baseUrl")+"/login_page.php");
    type(By.name("username"),user.getUsername());
    type(By.name("password"),user.getPassword());
    click(By.cssSelector("input[value='Login']"));

  }

  public void clickUser(int id) {
    wd.findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id="+id+"']")).click();
  }

  public void clickResetPassword() {
    wd.findElement(By.cssSelector("input[value='Reset Password']")).click();
  }
}
