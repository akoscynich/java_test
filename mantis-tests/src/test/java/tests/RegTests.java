package tests;

import org.testng.annotations.Test;

public class RegTests extends TestBase  {

    @Test
    public  void testReg(){
        app.registration().start("user1", "user1@localhost.localadmin");
    }
}
