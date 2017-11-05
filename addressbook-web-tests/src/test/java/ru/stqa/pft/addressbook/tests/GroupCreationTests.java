package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {


    app.goTo().GroupPage();
    Set<GroupData> before=app.group().all();
    GroupData group = new GroupData().withName("Test2");
    app.group().create(group);
    Set<GroupData> after=app.group().all();
    Assert.assertEquals(after.size(), before.size()+1);

    before.add (group);
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    Assert.assertEquals (new HashSet<Object>(before), new HashSet<Object>(after));
  }
}