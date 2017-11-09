package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups () {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new GroupData().withName("Test1").withHeader("Header 1").withFooter("Footer 1")});
    list.add(new Object[] {new GroupData().withName("Test2").withHeader("Header 2").withFooter("Footer 2")});
    list.add(new Object[] {new GroupData().withName("Test3").withHeader("Header 3").withFooter("Footer 3")});
    return list.iterator();
  }

  @Test (dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) {
      app.goTo().GroupPage();
      Groups before = app.group().all();

      app.group().create(group);
      assertThat(app.group().Count(), equalTo(before.size() + 1));
      Groups after = app.group().all();
      assertThat(after, equalTo(before
              .withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test (enabled = false)
  public void testGroupBadCreation() {
    app.goTo().GroupPage();
    Groups before=app.group().all();
    GroupData group = new GroupData().withName("Test");
    app.group().create(group);
    assertThat(app.group().Count(), equalTo(before.size()));
    Groups after=app.group().all();
    assertThat(after, equalTo(before));
  }

}