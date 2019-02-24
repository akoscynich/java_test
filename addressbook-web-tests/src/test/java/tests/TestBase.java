package tests;

import appmanager.ApplicationManager;
import data.ContactData;
import data.Contacts;
import data.GroupData;
import data.Groups;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    Logger logger = LoggerFactory.getLogger(TestBase.class);


    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m){
        logger.info("Stop test " + m.getName());
    }

    public void verifyGrListUi(){
        if (Boolean.getBoolean("verifyUI")){
        Groups db = app.db().groups();
        Groups ui = app.group().all();
        assertThat(ui, equalTo(db.stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName())).collect(Collectors.toSet())));
    }}

    public void verifyConListUi(){
        if (Boolean.getBoolean("verifyUI")){
            Contacts db = app.db().contacts();
            Contacts ui = app.contact().all();
            assertThat(ui, equalTo(db.stream().map((g) -> new ContactData().withId(g.getId()).withFirstname(g.getFirstname())).collect(Collectors.toSet())));
        }}

}
