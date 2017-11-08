package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.Contactdata;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.tests.TestBase;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;



public class ContactDataGeneretor extends TestBase{

  public static void main (String [] args) throws IOException {
    int count = Integer.parseInt(args [0]);
    File file = new File(args[1]);

    List<Contactdata> contacts = generateContacts (count);
    save (contacts,file);

  }

  private static void save(List<Contactdata> contacts, File file) throws IOException {
    System.out.println(new File (".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (Contactdata contact : contacts) {
      writer.write (String.format("%s;%s;%s;%s;%s\n",contact.getFirstname(),contact.getLastname(),
              contact.getAddress(),contact.getAllemails(),contact.getAllphones()));
    }
    writer.close();
  }

  private static List<Contactdata> generateContacts(int count) {
    List<Contactdata> contacts = new ArrayList<Contactdata>();
    for (int i=0; i < count; i++) {
      contacts.add (new Contactdata().withFirstname(String.format("Ivan%s",i)).withLastname(String.format("Ivano%s",i))
              .withAddress(String.format("Lvovskaya,  %s",i)).withAllemails(String.format("skyLena%s",i, "@ya.ru %s"))
              .withAllPhones(String.format("47230%s",i)));
    }
    return contacts;
  }
  
  
}
