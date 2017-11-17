package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
import org.hibernate.mapping.Collection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Groups extends ForwardingSet<GroupData> {

private Set<GroupData> delegate;

  public Groups(Groups groups) {
    this.delegate=new HashSet<GroupData>(groups.delegate);
  }

  public Groups() {
    this.delegate = new HashSet<GroupData>();
  }

  public Groups(java.util.Collection<GroupData> group) {
    this.delegate=new HashSet<GroupData>(group);
  }


  @Override
  protected Set<GroupData> delegate() {
    return delegate;
  }

  public Groups withAdded (GroupData group) {
    Groups groups = new Groups(this);
    groups.add(group);
    return groups;
  }

  public Groups withOut (GroupData group) {
    Groups groups = new Groups(this);
    groups.remove(group);
    return groups;
  }
}
