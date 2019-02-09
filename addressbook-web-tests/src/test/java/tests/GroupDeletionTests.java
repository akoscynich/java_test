package tests;

import data.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Set;

public class GroupDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePrecon(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test//(enabled = false)
    public void testGroupDeletion() throws Exception {
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        //int index = before.size() -1;
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(deletedGroup);
        Assert.assertEquals(before, after);
    }

}
