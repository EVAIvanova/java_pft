package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {


  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void gotoGroupPage() {

    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    } else {
      click(By.linkText("GROUPS"));
    }
  }


  public void gotoHomePage() {

    if (isElementPresent(By.id("maintable"))) {
      return;
    } else {
      wd.findElement(By.linkText("HOME")).click();
    }
  }
}
