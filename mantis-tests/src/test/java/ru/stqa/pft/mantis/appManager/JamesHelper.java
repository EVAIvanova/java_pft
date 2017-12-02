package ru.stqa.pft.mantis.appManager;

import com.sun.imageio.plugins.common.ReaderUtil;
import org.apache.commons.net.telnet.TelnetClient;

import javax.mail.Session;
import javax.mail.Store;
import java.io.InputStream;
import java.io.PrintStream;

public class JamesHelper {

  private ApplicationManager app;
  private TelnetClient telnet;
  private InputStream in;
  private PrintStream out;

  private Session mailSession;
  private Store store;
  private String mailserver;

  public JamesHelper () {
    this.app = app;
    telnet = new TelnetClient();
    mailSession = Session.getDefaultInstance(System.getProperties());
  }

  public boolean doesUserExist (String name) {
    initTelnetSession();
    write ("verify " + name);
    String result = readUntil ("exist");
    closeTelnetSession ();
    return result.trim().equals("User" + name + " exist");
  }

  private void closeTelnetSession() {
  }

  private String readUntil(String exist) {
  }

  private void initTelnetSession() {
  }

  public void createUser () {

  }

}
