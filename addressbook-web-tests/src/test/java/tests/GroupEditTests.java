package tests;

import data.GroupData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class GroupEditTests extends TestBase {

    @Test
    public void testGroupEdition() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() -1);
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().clearGroupForm();
        GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test4", "test5", "test6");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitGroupEdition();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() - 1);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
