package tests;

import data.GroupData;
import data.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {


    @BeforeMethod
    public void ensurePrecon() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
        }
    }

    @Test//(enabled = false)
    public void testGroupDeletion() throws Exception {
        Groups before = app.db().groups();;
        GroupData deletedGroup = before.iterator().next();
        //int index = before.size() -1;
        app.group().delete(deletedGroup);
        assertThat(app.group().count(), equalTo(before.size() -1));
        Groups after = app.db().groups();;

        //before.remove(deletedGroup);
        assertThat(after, equalTo(before.without(deletedGroup)));
        //Assert.assertEquals(before, after);
    }

}
