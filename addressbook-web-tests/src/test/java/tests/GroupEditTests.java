package tests;

import data.GroupData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupEditTests extends TestBase {

    @BeforeMethod
    public void ensurePrecon(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test//(enabled = false)
    public void testGroupEdition() throws Exception {

        List<GroupData> before = app.group().list();
        int index = before.size() -1;
        GroupData group = new GroupData()
                .withId(before.get(index).getId()).withName("test4").withHeader("test5").withFooter("test6");
        app.group().modify(index, group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
