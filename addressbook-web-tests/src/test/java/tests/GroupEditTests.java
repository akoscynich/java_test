package tests;

import data.GroupData;
import data.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.CoderResult;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecon(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test//(enabled = false)
    public void testGroupEdition() throws Exception {

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        //int index = before.size() -1;
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test4").withHeader("test5").withFooter("test6");
        app.group().modify(group);
        assertEquals(app.group().count(), before.size());
        Groups after = app.group().all();

        //before.remove(modifiedGroup);
        //before.add(group);
        //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(byId);
        //after.sort(byId);
        //assertEquals(before, after);
        assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));
    }

}
