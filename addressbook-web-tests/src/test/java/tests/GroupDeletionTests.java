package tests;

import data.GroupData;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class GroupDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePrecon(){
        app.goTo().groupPage();
        if (app.group().list().size() == 0){
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test//(enabled = false)
    public void testGroupDeletion() throws Exception {
        List<GroupData> before = app.group().list();
        int index = before.size() -1;
        app.group().delete(index);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size() -1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }

}
