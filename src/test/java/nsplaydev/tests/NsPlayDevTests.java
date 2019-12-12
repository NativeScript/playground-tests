package nsplaydev.tests;

import functional.tests.core.enums.DeviceType;
import functional.tests.core.enums.SwipeElementDirection;
import functional.tests.core.mobile.basetest.MobileTest;
import functional.tests.core.mobile.element.UIElement;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import nsplaydev.pages.ComponentsDetailsPage;
import nsplaydev.pages.ComponentsPage;
import nsplaydev.pages.ComponentsVisualizationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NsPlayDevTests extends MobileTest {


    @Test(description = "Verify home page looks OK.", groups = {"android", "ios"})
    public void test_01_components_page_looks_ok() throws Exception {
        synchronized (wait) {
            wait.wait(15000);
        }

        ComponentsPage componentsPage = new ComponentsPage(false);
        assertScreen("nsplaydev-home-view", settings.shortTimeout);
    }

    @Test(description = "Verify Button Visualization page looks OK.", groups = {"android", "ios"})
    public void test_02_button_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        componentsPage.navigate("Button");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Button");
        assertScreen("nsplaydev-button-view", settings.shortTimeout);
    }

    @Test(description = "Verify Button Deitals page looks OK.", groups = {"android", "ios"})
    public void test_03_buttons_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Button");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-button-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Button");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Image Visualization page looks OK.", groups = {"android", "ios"})
    public void test_04_image_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        assertScreen("nsplaydev-home-view", settings.shortTimeout);
        componentsPage.navigate("Image");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Image");
        if (settings.deviceName.contains("Api19")) {
            assertScreen("nsplaydev-image-view_api19", settings.shortTimeout);

        } else {
            assertScreen("nsplaydev-image-view", settings.shortTimeout);
        }
    }

    @Test(description = "Verify Image Deitals page looks OK.", groups = {"android", "ios"})
    public void test_05_image_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Image");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-image-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Image");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Label Visualization page looks OK.", groups = {"android", "ios"})
    public void test_06_label_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        assertScreen("nsplaydev-home-view", settings.shortTimeout);
        componentsPage.navigate("Label");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Label");
        assertScreen("nsplaydev-label-view", settings.shortTimeout);
    }

    @Test(description = "Verify Label Deitals page looks OK.", groups = {"android", "ios"})
    public void test_07_label_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Label");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-label-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Label");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Switch Visualization page looks OK.", groups = {"android", "ios"})
    public void test_08_switch_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        assertScreen("nsplaydev-home-view", settings.shortTimeout);
        componentsPage.navigate("Switch");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Switch");
        assertScreen("nsplaydev-switch-view", settings.shortTimeout);
    }

    @Test(description = "Verify Switch Deitals page looks OK.", groups = {"android", "ios"})
    public void test_09_switch_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Switch");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-switch-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Switch");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Slider Visualization page looks OK.", groups = {"android", "ios"})
    public void test_10_slider_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        assertScreen("nsplaydev-home-view", settings.shortTimeout);
        componentsPage.navigate("Slider");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Slider");
        assertScreen("nsplaydev-slider-view", settings.shortTimeout);
    }

    @Test(description = "Verify Slider Deitals page looks OK.", groups = {"android", "ios"})
    public void test_11_slider_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Slider");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-slider-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Slider");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify TextField Visualization page looks OK.", groups = {"android", "ios"})
    public void test_12_textfield_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        assertScreen("nsplaydev-home-view", settings.shortTimeout);
        componentsPage.navigate("TextField");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("TextField");
        assertScreen("nsplaydev-textfield-view", settings.shortTimeout);
    }

    @Test(description = "Verify TextField Deitals page looks OK.", groups = {"android", "ios"})
    public void test_13_textfield_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("TextField");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-textfield-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("TextField");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify TextView Visualization page looks OK.", groups = {"android", "ios"})
    public void test_14_textview_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(false);
        assertScreen("nsplaydev-home-view", settings.shortTimeout);
        componentsPage.navigate("TextView");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("TextView");
        assertScreen("nsplaydev-textview-view", settings.shortTimeout);
    }

    @Test(description = "Verify TextView Deitals page looks OK.", groups = {"android", "ios"})
    public void test_15_textview_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("TextView");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-textview-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("TextView");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify DatePicker Visualization page looks OK.", groups = {"android", "ios"})
    public void test_16_DatePicker_page_looks_ok() throws Exception {
        ComponentsPage componentsPage;
        if (settings.deviceName.contains("Api26")) {
            componentsPage = new ComponentsPage(true);
            assertScreen("nsplaydev-scrolled-home-view", settings.shortTimeout, 20);
        } else {
            componentsPage = new ComponentsPage(false);
            assertScreen("nsplaydev-home-view", settings.shortTimeout);
        }
        componentsPage.navigate("DatePicker");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("DatePicker");
        assertScreen("nsplaydev-datepicker-view", settings.shortTimeout, 0.1);
    }

    @Test(description = "Verify DatePicker Deitals page looks OK.", groups = {"android", "ios"})
    public void test_17_DatePicker_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("DatePicker");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-datepicker-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("DatePicker");
        componentsVisualizationPage2.navigateBackPage();
    }


    @Test(description = "Verify Scrolled Home page looks OK.", groups = {"android", "ios"})
    public void test_18_scrolled_home_page_looks_ok() throws Exception {
        if (settings.deviceName.contains("Api26")) {
            assertScreen("nsplaydev-scrolled-home-view", settings.shortTimeout, 20);
        } else {
            assertScreen("nsplaydev-home-view", settings.shortTimeout);
        }
        ComponentsPage componentsPage = new ComponentsPage(true);
        assertScreen("nsplaydev-scrolled-home-view", settings.shortTimeout, 20);
    }

    @Test(description = "Verify Chart Visualization page looks OK.", groups = {"android", "ios"})
    public void test_19_chart_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(true);
        assertScreen("nsplaydev-scrolled-home-view", settings.shortTimeout, 20);
        componentsPage.navigate("Chart");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Chart");
        assertScreen("nsplaydev-chart-view", settings.shortTimeout);
    }

    @Test(description = "Verify Chart Deitals page looks OK.", groups = {"android", "ios"})
    public void test_20_chart_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Chart");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-chart-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Chart");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify ListView Visualization page looks OK.", groups = {"android", "ios"})
    public void test_21_listview_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(true);
        assertScreen("nsplaydev-scrolled-home-view", settings.shortTimeout, 20);
        componentsPage.navigate("ListView");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("ListView");
        componentsPage.waitForElement(8000);
        if (settings.deviceName.contains("Api19")) {
            assertScreen("nsplaydev-listview-view_api19", settings.shortTimeout);

        } else {
            assertScreen("nsplaydev-listview-view", settings.shortTimeout);
        }
    }

    @Test(description = "Verify ListView Deitals page looks OK.", groups = {"android", "ios"})
    public void test_22_listview_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("ListView");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-listview-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("ListView");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Accelerometer Visualization page looks OK.", groups = {"android", "ios"})
    public void test_23_accelerometer_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(true);
        assertScreen("nsplaydev-scrolled-home-view", settings.shortTimeout, 20);
        componentsPage.navigate("Accelerometer");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Accelerometer");
        assertScreen("nsplaydev-accelerometer-view", settings.shortTimeout);
    }

    @Test(description = "Verify Accelerometer is working.", groups = {"android", "ios"})
    public void test_24_accelerometer_is_working() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Accelerometer");
        if (settings.deviceType == DeviceType.Simulator) {
            assertScreen("nsplaydev-accelerometer-view", settings.shortTimeout, 10);
        } else {
            componentsVisualizationPage.navigate("Start Accelerometer");
            assertScreen("nsplaydev-accelerometer-working-view", settings.shortTimeout, 10);
        }
    }

    @Test(description = "Verify Accelerometer Deitals page looks OK.", groups = {"android", "ios"})
    public void test_25_accelerometer_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Accelerometer");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-accelerometer-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Accelerometer");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Location Visualization page looks OK.", groups = {"android", "ios"})
    public void test_26_location_page_looks_ok() throws Exception {
        ComponentsPage componentsPage = new ComponentsPage(true);
        assertScreen("nsplaydev-scrolled-home-view", settings.shortTimeout, 20);
        componentsPage.navigate("Location");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Location");
        assertScreen("nsplaydev-location-view", settings.shortTimeout);
    }

    @Test(description = "Verify Location is working.", groups = {"android", "ios"})
    public void test_27_location_is_working() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Location");
        componentsVisualizationPage.navigate("Show location");
        componentsVisualizationPage.waitForElement(9000);
        if (componentsVisualizationPage.checkIfElementisShown("Allow")) {
            componentsVisualizationPage.navigate("Allow");
        } else {
            if (settings.deviceType == DeviceType.Simulator) {
                if(componentsVisualizationPage.checkIfElementisShown("Allow While Using App"))
                {
                    componentsVisualizationPage.navigate("Allow While Using App");
                }
                else {
                    try {
                        if (ExpectedConditions.alertIsPresent() != null) {
                            client.driver.switchTo().alert().accept();
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage());
                    }
                }
            }
        }
        if (find.byText("Allow only while using the app") != null) {
            componentsVisualizationPage.navigate("Allow only while using the app");
        }
        componentsVisualizationPage.waitForElement(2000);
        if (componentsVisualizationPage.checkIfElementisShown("OK")) {
            componentsVisualizationPage.navigate("OK");

        }
        componentsVisualizationPage.waitForElement(4000);
        assertScreen("nsplaydev-location-working-view", settings.shortTimeout, 20);
    }

    @Test(description = "Verify Location Deitals page looks OK.", groups = {"android", "ios"})
    public void test_28_location_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Location");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        if (settings.deviceName.contains("Api24")) {
            // Temporary image check for api24 till find workaround for Google Play services
            assertScreen("nsplaydev-location-details-view_temp", settings.shortTimeout);
        } else {
            assertScreen("nsplaydev-location-details-view", settings.shortTimeout);
        }

        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Location");
        componentsVisualizationPage2.navigateBackPage();
    }

    @Test(description = "Verify Camera Visualization page looks OK.", groups = {"android", "ios"})
    public void test_29_camera_page_looks_ok() throws Exception {
        gestures.scrollToElement(SwipeElementDirection.DOWN, "Camera", 1);
        ComponentsPage componentsPage = new ComponentsPage(true);
        assertScreen("nsplaydev-scrolled-home-view", settings.shortTimeout, 20);
        componentsPage.navigate("Camera");
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Camera");
        assertScreen("nsplaydev-camera-view", settings.shortTimeout);
    }

    @Test(description = "Verify Camera is working.", groups = {"android", "ios"})
    public void test_30_camera_is_working() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Camera");
        componentsVisualizationPage.navigate("Request permissions");
        componentsVisualizationPage.waitForElement(5000);
        if (settings.deviceType == DeviceType.Simulator) {
            try {
                if (ExpectedConditions.alertIsPresent() != null) {
                    client.driver.switchTo().alert().accept();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            componentsVisualizationPage.waitForElement(10000);
            try {
                if (ExpectedConditions.alertIsPresent() != null) {
                    client.driver.switchTo().alert().accept();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            if (find.byText("Allow") != null) {
                componentsVisualizationPage.navigate("Allow");
            }
            componentsVisualizationPage.waitForElement(2000);
            if (find.byText("Allow") != null) {
                componentsVisualizationPage.navigate("Allow");
            }
        }
        componentsVisualizationPage.waitForElement(2000);
        componentsVisualizationPage.navigate("Check for camera");
        componentsVisualizationPage.waitForElement(2000);
        if (settings.deviceType == DeviceType.Simulator) {
            Assert.assertEquals(client.driver.switchTo().alert().getText(), "Alert\n" + "Is camera hardware available: false");

            try {
                if (ExpectedConditions.alertIsPresent() != null) {
                    client.driver.switchTo().alert().accept();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        } else {
            UIElement alert = find.byTextContains("camera hardware available");
            Assert.assertEquals(alert.getText(), "Is camera hardware available: true");
            componentsVisualizationPage.navigate("Ok");
        }
        if (!settings.deviceName.contains("Api29")) {
            componentsVisualizationPage.waitForElement(4000);
            componentsVisualizationPage.navigate("Take Photo");
            componentsVisualizationPage.waitForElement(6000);
            if (settings.deviceType == DeviceType.Simulator) {
                if(!settings.platformVersion.toString().contains("13.")) {
                    componentsVisualizationPage.navigate("Camera Roll");
                }
                else{
                    find.byText("All Photos").click();
                }
                componentsVisualizationPage.waitForElement(4000);
                UIElement photos = find.byText("Photos");
                new TouchAction(client.driver).tap((new PointOption().withCoordinates((photos.getCenter().x + 40), (photos.getCenter().y + 40)))).perform();
                componentsVisualizationPage.waitForElement(2000);

            } else {
                if (find.byText("Allow") != null) {
                    componentsVisualizationPage.navigate("Allow");
                }
                if (find.byText("Allow all the time") != null) {
                    componentsVisualizationPage.navigate("Allow all the time");
                }
                componentsVisualizationPage.waitForElement(2000);
                if (find.byText("Next") != null) {
                    componentsVisualizationPage.navigate("Next");
                }
                componentsVisualizationPage.waitForElement(2000);
                componentsVisualizationPage.navigateBackPage();
                componentsVisualizationPage.waitForElement(3000);
                componentsVisualizationPage.navigate("Ok");
                componentsVisualizationPage.waitForElement(2000);
                componentsVisualizationPage.navigate("Take Photo");
                componentsVisualizationPage.waitForElement(3000);
                if (client.driver.findElements(By.xpath("//*[@content-desc='Shutter']")).size() > 0) {
                    client.driver.findElements(By.xpath("//*[@content-desc='Shutter']")).get(0).click();
                }
                if (client.driver.findElements(By.xpath("//*[@content-desc='Shutter button']")).size() > 0) {
                    client.driver.findElements(By.xpath("//*[@content-desc='Shutter button']")).get(0).click();
                }
                componentsVisualizationPage.waitForElement(3000);
                if (client.driver.findElements(By.xpath("//*[@content-desc='Done']")).size() > 0) {
                    client.driver.findElements(By.xpath("//*[@content-desc='Done']")).get(0).click();
                }
                if (client.driver.findElements(By.xpath("//*[@resource-id=\"com.android.camera:id/btn_done\"]")).size() > 0) {
                    client.driver.findElements(By.xpath("//*[@resource-id=\"com.android.camera:id/btn_done\"]")).get(0).click();
                }
                componentsVisualizationPage.waitForElement(2000);
            }
        }
        if (settings.deviceType == DeviceType.Simulator) {
            assertScreen("nsplaydev-camera-working-view-ios", settings.shortTimeout);
        } else {
            if (!settings.deviceName.contains("Api29")) {
                assertScreen("nsplaydev-camera-working-view-android", settings.shortTimeout, 44);
            }

        }
    }

    @Test(description = "Verify Camera Deitals page looks OK.", groups = {"android", "ios"})
    public void test_31_camera_details_page_looks_ok() throws Exception {
        ComponentsVisualizationPage componentsVisualizationPage = new ComponentsVisualizationPage("Camera");
        componentsVisualizationPage.navigate("Details");
        ComponentsDetailsPage componentsDetailsPage = new ComponentsDetailsPage();
        assertScreen("nsplaydev-camera-details-view", settings.shortTimeout);
        componentsDetailsPage.navigateBackPage();
        ComponentsVisualizationPage componentsVisualizationPage2 = new ComponentsVisualizationPage("Camera");
        componentsVisualizationPage2.navigateBackPage();
    }
}
