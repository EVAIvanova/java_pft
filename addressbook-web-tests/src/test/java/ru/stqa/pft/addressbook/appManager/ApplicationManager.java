package ru.stqa.pft.addressbook.appManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  WebDriver wd;

  protected SessionHelper sessionHelper;
  private  NavigationHelper navigationHelper ;
  private   GroupHelper groupHelper;
  private ContactHelper contactHelper;
  private String browser;

  public ApplicationManager(String browser) {

    this.browser = browser;
  }

  public void init() {

    if (Objects.equals(browser, BrowserType.FIREFOX)) {
      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
    } else if (Objects.equals(browser, BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (Objects.equals(browser, BrowserType.IE))
      {wd = new InternetExplorerDriver();}
      else if (Objects.equals(browser, BrowserType.OPERA_BLINK)) {
      wd = new OperaDriver();
    }


    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper (wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }



  public void stop() {
    sessionHelper.wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public ContactHelper getContactHelper() {
    return contactHelper;
  }

  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }
}



