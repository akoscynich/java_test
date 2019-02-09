package tests;

import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.*;
import data.GroupData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import org.hamcrest.CoreMatchers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test//(enabled = false)
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        Set<GroupData> after = app.group().all();
        assertThat(after.size(), equalTo(before.size() + 1));

        //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        assertThat(before.withAdded(group), equalTo(after));
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}
