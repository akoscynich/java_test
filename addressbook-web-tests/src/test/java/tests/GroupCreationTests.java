package tests;

import data.GroupData;
import data.Groups;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

    @Test//(enabled = false)
    public void testGroupCreation() throws Exception {
        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        //group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        //before.add(group);
        assertThat(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt())), equalTo(after));
        //Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}
