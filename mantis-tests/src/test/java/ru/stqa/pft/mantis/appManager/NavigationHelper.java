package ru.stqa.pft.mantis.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void ManageUsersPage () {
   wd.findElement(By.cssSelector("a[href='/mantisbt-1.2.20/manage_user_page.php']")).click();
   }

}
