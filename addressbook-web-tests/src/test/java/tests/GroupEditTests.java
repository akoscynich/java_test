package tests;

import data.GroupData;
import org.testng.annotations.Test;

public class GroupEditTests extends TestBase {

    @Test
    public void testGroupEdition() throws Exception {
        app.getNavigationHelper().gotoGroupPage();
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editSelectedGroup();
        app.getGroupHelper().clearGroupForm();
        app.getGroupHelper().fillGroupForm(new GroupData("test4", "test5", "test6"));
        app.getGroupHelper().submitGroupEdition();
        app.getGroupHelper().returnToGroupPage();
    }

}
