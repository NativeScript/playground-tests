package sync.tests;

import functional.tests.core.mobile.basetest.MobileTest;
import sync.pages.ComponentsDetailsPage;
import sync.pages.ComponentsPage;
import sync.pages.ComponentsVisualizationPage;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NSSyncTests extends MobileTest {
    @BeforeTest
    public void beforeTest() {
        //this.settings.
        //if(this.settings.this.settings.platformName)
        //throw new SkipException("Skip test because platform is not supported for the test");
    }

    @Test(description = "Verify home page looks OK.", groups = {"android", "ios"})
    public void test_01_navigate_to_javascript_project() throws Exception {
        //ComponentsPage componentsPage = new ComponentsPage(false);
        ComponentsVisualizationPage componentsPage = new ComponentsVisualizationPage();
        componentsPage.NavigateToPage("https://play.telerik.rocks/?template=play-js&debug=true");
    }

}
