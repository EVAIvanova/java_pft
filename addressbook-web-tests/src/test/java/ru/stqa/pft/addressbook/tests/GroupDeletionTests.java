package ru.stqa.pft.addressbook.tests;

import jdk.nashorn.internal.runtime.regexp.joni.Matcher;
import org.hamcrest.CoreMatchers;
import org.hamcrest.junit.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions () {
        if (app.db().groups().size() == 0) {
            app.goTo().GroupPage();
            app.group().create(new GroupData().withName("Test1"));

        }
    }

    @Test
    public void testGroupDeletion() {
        Groups before=app.db().groups();
        GroupData deletedGroup = before.iterator().next();
        app.goTo().GroupPage();
        app.group().delete(deletedGroup);
        assertThat(app.group().Count(), equalTo(before.size()-1));
        Groups after=app.db().groups();
        assertThat (after, equalTo(before.withOut(deletedGroup)));
   }



}
