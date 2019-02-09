package tests;

import data.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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

        Set<GroupData> before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        //int index = before.size() -1;
        GroupData group = new GroupData()
                .withId(modifiedGroup.getId()).withName("test4").withHeader("test5").withFooter("test6");
        app.group().modify(group);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        //Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        //before.sort(byId);
        //after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
