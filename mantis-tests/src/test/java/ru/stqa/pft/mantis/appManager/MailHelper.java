package ru.stqa.pft.mantis.appManager;

import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import org.subethamail.wiser.Wiser;
import org.subethamail.wiser.WiserMessage;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class MailHelper {

  private ApplicationManager app;
  private final Wiser wiser;


  public MailHelper(ApplicationManager app) {
    this.app = app;
    wiser = new Wiser();
  }

  public List<MailMessage> waitForMail (int count, long timeout) throws MessagingException, IOException {
    long start = System.currentTimeMillis();
    while (System.currentTimeMillis() < start + timeout) {
      if (wiser.getMessages().size() >= count) {

        return wiser.getMessages().stream().map((m) -> {
          try {
            return toModelMail(m);
          } catch (MessagingException e) {
            e.printStackTrace();
            return null;
          } catch (IOException e) {
            e.printStackTrace();
            return null;
          }
        }).collect(Collectors.toList());
      }
      try {
        Thread.sleep (1000);
        }
        catch (InterruptedException e) {
        e.printStackTrace();

      }
    }
    throw new Error("No email :(");
  }

  private static MailMessage toModelMail(WiserMessage m) throws MessagingException, IOException {
    try {
      MimeMessage mm = m.getMimeMessage ();
      return new MailMessage (mm.getAllRecipients()[0].toString(), (String) mm.getContent());
      }
      catch (MessagingException e) {
      e.printStackTrace();
      return null;
    }
    catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public void start () {
    wiser.start();
  }

  public void stop () {
    wiser.stop();
  }
}
