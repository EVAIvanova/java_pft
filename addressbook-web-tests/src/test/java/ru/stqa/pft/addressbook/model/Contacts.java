package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<Contactdata>{

private Set<Contactdata> delegate;

  public Contacts(Contacts contacts) {
    this.delegate = new HashSet<Contactdata>(contacts.delegate);
  }

  public Contacts() {
    this.delegate = new HashSet<Contactdata>();
  }

  @Override
  protected Set<Contactdata> delegate() {
    return delegate;
  }

  public Contacts withAdded (Contactdata contact) {
    Contacts contacts = new Contacts(this);
    contacts.add(contact);
    return contacts;
  }

  public Contacts withOut (Contactdata contact) {
    Contacts contacts = new Contacts(this);
    contacts.remove(contact);
    return contacts;
  }
}
