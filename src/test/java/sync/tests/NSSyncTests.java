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
    public void test_01_components_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Button Visualization page looks OK.", groups = {"android", "ios"})
    public void test_02_button_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        componentsPage.navigate("Button");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Button");
        this.assertScreen("nsplaydev-button-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Button Deitals page looks OK.", groups = {"android", "ios"})
    public void test_03_buttons_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Button");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-button-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Button");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Image Visualization page looks OK.", groups = {"android", "ios"})
    public void test_04_image_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
        componentsPage.navigate("Image");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Image");
        this.assertScreen("nsplaydev-image-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Image Deitals page looks OK.", groups = {"android", "ios"})
    public void test_05_image_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Image");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-image-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Image");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Label Visualization page looks OK.", groups = {"android", "ios"})
    public void test_06_image_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
        componentsPage.navigate("Label");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Label");
        this.assertScreen("nsplaydev-label-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Label Deitals page looks OK.", groups = {"android", "ios"})
    public void test_07_image_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Label");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-label-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Label");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Switch Visualization page looks OK.", groups = {"android", "ios"})
    public void test_08_switch_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
        componentsPage.navigate("Switch");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Switch");
        this.assertScreen("nsplaydev-switch-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Switch Deitals page looks OK.", groups = {"android", "ios"})
    public void test_09_switch_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Switch");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-switch-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Switch");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Slider Visualization page looks OK.", groups = {"android", "ios"})
    public void test_10_slider_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
        componentsPage.navigate("Slider");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Slider");
        this.assertScreen("nsplaydev-slider-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Slider Deitals page looks OK.", groups = {"android", "ios"})
    public void test_11_slider_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Slider");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-slider-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Slider");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify TextField Visualization page looks OK.", groups = {"android", "ios"})
    public void test_12_textfield_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
        componentsPage.navigate("TextField");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("TextField");
        this.assertScreen("nsplaydev-textfield-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify TextField Deitals page looks OK.", groups = {"android", "ios"})
    public void test_13_textfield_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("TextField");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-textfield-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("TextField");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify TextView Visualization page looks OK.", groups = {"android", "ios"})
    public void test_14_textview_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
        componentsPage.navigate("TextView");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("TextView");
        this.assertScreen("nsplaydev-textview-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify TextView Deitals page looks OK.", groups = {"android", "ios"})
    public void test_15_textview_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("TextView");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-textview-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("TextView");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify DatePicker Visualization page looks OK.", groups = {"android", "ios"})
    public void test_16_DatePicker_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
        componentsPage.navigate("DatePicker");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("DatePicker");
        this.assertScreen("nsplaydev-datepicker-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify DatePicker Deitals page looks OK.", groups = {"android", "ios"})
    public void test_17_DatePicker_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("DatePicker");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-datepicker-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("DatePicker");
        componentsVisualizationPage2.navigateBackPage();
    }


    @Test(description = "Verify Scrolled Home page looks OK.", groups = {"android", "ios"})
    public void test_18_scrolled_home_page_looks_ok() throws Exception {
        this.assertScreen("nsplaydev-home-view", this.settings.shortTimeout);
        ComponentsPage componentsPage = new ComponentsPage(true);
        this.assertScreen("nsplaydev-scrolled-home-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Chart Visualization page looks OK.", groups = {"android", "ios"})
    public void test_19_chart_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(true);
        this.assertScreen("nsplaydev-scrolled-home-view", this.settings.shortTimeout);
        componentsPage.navigate("Chart");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Chart");
        this.assertScreen("nsplaydev-chart-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Chart Deitals page looks OK.", groups = {"android", "ios"})
    public void test_20_chart_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Chart");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-chart-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Chart");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify ListView Visualization page looks OK.", groups = {"android", "ios"})
    public void test_21_listview_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(true);
        this.assertScreen("nsplaydev-scrolled-home-view", this.settings.shortTimeout);
        componentsPage.navigate("ListView");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("ListView");
        componentsPage.waitForElement(4000);
        this.assertScreen("nsplaydev-listview-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify ListView Deitals page looks OK.", groups = {"android", "ios"})
    public void test_22_listview_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("ListView");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-listview-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("ListView");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Accelerometer Visualization page looks OK.", groups = {"android", "ios"})
    public void test_23_accelerometer_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(true);
        this.assertScreen("nsplaydev-scrolled-home-view", this.settings.shortTimeout);
        componentsPage.navigate("Accelerometer");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Accelerometer");
        this.assertScreen("nsplaydev-accelerometer-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Accelerometer is working.", groups = {"android", "ios"})
    public void test_24_accelerometer_is_working() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Accelerometer");
        if(settings.deviceType == settings.deviceType.Simulator)
        {
            this.assertScreen("nsplaydev-accelerometer-view", this.settings.shortTimeout, 10);
        }
        else
        {
            componentsVisualizationPage.navigate("Start Accelerometer");
            this.assertScreen("nsplaydev-accelerometer-working-view", this.settings.shortTimeout, 10);
        }
    }

    @Test(description = "Verify Accelerometer Deitals page looks OK.", groups = {"android", "ios"})
    public void test_25_accelerometer_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Accelerometer");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-accelerometer-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Accelerometer");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Location Visualization page looks OK.", groups = {"android", "ios"})
    public void test_26_location_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(true);
        this.assertScreen("nsplaydev-scrolled-home-view", this.settings.shortTimeout);
        componentsPage.navigate("Location");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Location");
        this.assertScreen("nsplaydev-location-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify Location is working.", groups = {"android", "ios"})
    public void test_27_location_is_working() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Location");
        componentsVisualizationPage.navigate("Show Location");
        if(componentsVisualizationPage.checkIfElementisShown("Allow")) {
            componentsVisualizationPage.navigate("Allow");
        }
        componentsVisualizationPage.waitForElement(4000);
        this.assertScreen("nsplaydev-location-working-view", this.settings.shortTimeout, 20);
    }

    @Test(description = "Verify Location Deitals page looks OK.", groups = {"android", "ios"})
    public void test_28_location_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Location");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        this.assertScreen("nsplaydev-location-details-view", this.settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Location");
        componentsVisualizationPage2.navigateBackPage();
    }
}
