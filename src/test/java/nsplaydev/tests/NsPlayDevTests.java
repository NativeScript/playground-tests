package nsplaydev.tests;

import functional.tests.core.mobile.basetest.MobileTest;
import org.sikuli.remoteinterfaces.entities.Command;
import org.testng.annotations.BeforeTest;
import nsplaydev.pages.ComponentsPage;
import org.testng.annotations.Test;
import nsplaydev.pages.ComponentsVisualizationPage;
import nsplaydev.pages.ComponentsDetailsPage;
import org.sikuli.script.*;

public class NsPlayDevTests extends MobileTest {
    @BeforeTest
    public void beforeTest() {

        //this.settings.
        //if(this.settings.this.settings.platformName)
            //throw new SkipException("Skip test because platform is not supported for the test");
    }

    @Test(description = "Verify home page looks OK.", groups = {"android", "ios"})
    public void test_01_components_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
    }

}
