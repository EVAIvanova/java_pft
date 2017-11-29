package ru.stqa.pft.mantis.appManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FtpHelper {

  private final ApplicationManager app;
  private FTPClient ftp;


  public FtpHelper(ApplicationManager app) {
    this.app = app;
    ftp = new FTPClient();
  }

  public void upload (File file, String target, String backup) throws FileNotFoundException {
  ftp.connect(app.getProperty("ftp.host"));
  ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
  ftp.deleteFile(backup);
  ftp.rename(target,backup);
  ftp.enterLocalPassiveMode();
  ftp.storeFile(target,new FileInputStream(file));
  ftp.disconnect();
  }

  public void restore (String backup, String target) {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"),app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();
  }
}
