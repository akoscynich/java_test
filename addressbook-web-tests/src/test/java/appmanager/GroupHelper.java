package appmanager;

import data.GroupData;
import data.Groups;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static java.lang.Integer.parseInt;

public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteGroup() {
        click(By.name("delete"));
    }

    /*public void selectGroup(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }*/
    public void selectGroupById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void editSelectedGroup() {
        click(By.name("edit"));
    }

    public void submitGroupEdition() {
        click(By.name("update"));
    }

    public void clearGroupForm() {
        clear(By.name("group_name"));
        clear(By.name("group_header"));
        clear(By.name("group_footer"));
    }

    public void create(GroupData data){
        initGroupCreation();
        fillGroupForm(data);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectGroupById(group.getId());
        editSelectedGroup();
        clearGroupForm();
        fillGroupForm(group);
        submitGroupEdition();
        groupCache = null;
        returnToGroupPage();
    }


    /*public void delete(int index) {
        selectGroup(index);
        deleteGroup();
        returnToGroupPage();
    }*/


    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    /*public List<GroupData> list() {
        List<GroupData> groups = new ArrayList<GroupData>();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groups.add(new GroupData().withId(id).withName(name));
        }
        return groups;
    }*/

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null)
            return new Groups(groupCache);
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements){
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            groupCache.add(new GroupData().withId(id).withName(name));
        }
        return new Groups(groupCache);
    }

    public void delete(GroupData group) {

        selectGroupById(group.getId());
        deleteGroup();
        groupCache = null;
        returnToGroupPage();

    }

    public void selectNew(long now) {
        Select select = new Select(wd.findElement(By.name("to_group")));
        select.selectByVisibleText(Long.toString(now));
    }

    public int getIdOfNewGroup(long now) {
        return parseInt(wd.findElement(By.cssSelector("input[title='Select (" + now + ")']")).getAttribute("value"));
    }
}
