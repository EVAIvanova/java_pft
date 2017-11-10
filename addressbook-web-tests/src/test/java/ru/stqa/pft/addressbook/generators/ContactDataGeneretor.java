package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;



public class ContactDataGeneretor {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter (names = "-f", description = "Target file")
  public String file;

  public static void main (String [] args) throws IOException {
    ContactDataGeneretor generator = new ContactDataGeneretor();
    JCommander jCommander = new JCommander(generator);
    try {
    jCommander.parse(args);
    } catch (ParameterException ex){
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<Contactdata> contacts = generateContacts (count);
    save (contacts,new File (file));
  }

  private void save(List<Contactdata> contacts, File file) throws IOException {
    System.out.println(new File (".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (Contactdata contact : contacts) {
      writer.write (String.format("%s;%s;%s;%s;%s;%s\n",contact.getFirstname(),contact.getLastname(),
              contact.getAddress(),contact.getAllemails(),contact.getAllphones(),contact.getGroup()));
    }
    writer.close();
  }

  private  List<Contactdata> generateContacts(int count) {
    List<Contactdata> contacts = new ArrayList<Contactdata>();
    for (int i=0; i < count; i++) {
      contacts.add (new Contactdata().withFirstname(String.format("Ivan%s",i)).withLastname(String.format("Ivano%s",i))
              .withAddress(String.format("Lvovskaya,%s",i)).withAllemails(String.format("skyLena%s",i, "@ya.ru %s"))
              .withAllPhones(String.format("47230%s",i)).withGroup(String.format("Test%s",i)));
    }
    return contacts;
  }
  
  
}
