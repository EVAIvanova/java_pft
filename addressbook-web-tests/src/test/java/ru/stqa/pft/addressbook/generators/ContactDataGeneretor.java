package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.Contactdata;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class ContactDataGeneretor {

  @Parameter(names = "-c", description = "Group count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGeneretor generator = new ContactDataGeneretor();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();

  }

  private void run() throws IOException {
    List<Contactdata> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<Contactdata> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private void saveAsXml(List<Contactdata> contacts, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(Contactdata.class);
    String xml = xStream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsCsv(List<Contactdata> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (Contactdata contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(),
              contact.getAddress(), contact.getAllemails(), contact.getAllphones(), contact.getGroup()));
    }
    writer.close();
  }

  private List<Contactdata> generateContacts(int count) {
    List<Contactdata> contacts = new ArrayList<Contactdata>();
    for (int i = 0; i < count; i++) {
      contacts.add(new Contactdata().withFirstname(String.format("Ivan%s", i)).withLastname(String.format("Ivano%s", i))
              .withAddress(String.format("Lvovskaya,%s", i)).withAllemails(String.format("skyLena%s", i, "@ya.ru %s"))
              .withAllPhones(String.format("47230%s", i)).withGroup(String.format("Test%s", i)));
    }
    return contacts;
  }


}
