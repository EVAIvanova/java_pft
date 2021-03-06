package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions () {
    if (app.db().groups().size()== 0){
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("Test1"));
   }
  }

  @Test
  public void testGroupModification () {

   // Groups before=app.group().all();
    Groups before=app.db().groups();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData()
            .withId(modifiedGroup.getId()).withName("Test1").withHeader("Test2").withFooter("Test3");
    app.goTo().GroupPage();
    app.group().modify(group);
    assertThat(app.group().Count(), equalTo(before.size()));
    Groups after=app.db().groups();
    assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    verifyGroupListInUI();
  }



}
