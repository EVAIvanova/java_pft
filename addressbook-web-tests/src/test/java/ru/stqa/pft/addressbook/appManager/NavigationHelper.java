package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase {



  public NavigationHelper(WebDriver wd) {
    super (wd);
  }

  public void gotoGroupPage() {
    click(By.linkText("GROUPS"));
  }

  public void gotoNewContactCreationPage() {
    wd.findElement(By.linkText("ADD_NEW")).click();
  }

  public void gotoHomePage() {
    wd.findElement(By.linkText("HOME")).click();
  }
}
