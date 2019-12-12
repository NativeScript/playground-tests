package nsplaydev.pages;

import functional.tests.core.enums.SwipeElementDirection;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;

public class ComponentsPage extends BasePage {

    public ComponentsPage(boolean isScrolled) throws InterruptedException {
        super();
        UIElement browse = null;
        UIElement detailsElement = find.byText("Details");
        if (detailsElement != null) {
            detailsElement = null;
            navigateBack();
            waitForElement(1000);
            detailsElement = find.byText("Details");
            if (detailsElement != null) {
                detailsElement = null;
                navigateBack();
                waitForElement(1000);
                browse = find.byText("Components");
            } else {
                browse = find.byText("Components");
            }
        } else {
            browse = wait.waitForVisible(locators.findByTextLocator("Components", true));
        }

        if (isScrolled) {
            UIElement location = find.byText("Camera");
            if (location == null) {
                scrollDown();
                location = find.byText("Camera");
                if (location == null) {
                    scrollDown();
                    location = find.byText("Camera");
                    if (location == null) {
                        scrollDown();
                        location = find.byText("Camera");
                        if (location == null) {
                            scrollDown();
                            location = find.byText("Camera");
                            if (location == null) {
                                scrollDown();
                                location = find.byText("Camera");
                                if (location == null) {
                                    scrollDown();
                                    location = find.byText("Camera");
                                    if (location == null) {
                                        scrollDown();
                                        location = find.byText("Camera");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            location = find.byText("Camera");
            Assert.assertNotNull(location, "Page Not Scrolled correctly!");
        }
        Assert.assertNotNull(browse, "Components page not loaded!");
        log.info("Components page loaded.");
    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String button) throws InterruptedException {

        UIElement buttonToClick = find.byText(button);
        if (buttonToClick != null) {
            new TouchAction(client.driver).tap((new PointOption().withCoordinates((buttonToClick.getCenter().x), (buttonToClick.getCenter().y)))).perform();
            //client.driver.tap(1, buttonToClick.getCenter().x, buttonToClick.getCenter().y, 500);
            //new TouchAction((MobileDriver) client.driver).tap((buttonToClick.getCenter().x), (buttonToClick.getCenter().y)).perform();
            log.info("Navigate to " + button);
        } else {
            log.info("Element " + button + " not found! Not able to click it!");
        }

    }

    public boolean checkIfElementisShown(String elementText) {
        boolean isElementFound = false;
        UIElement element = find.byText(elementText);
        if (element != null) {
            isElementFound = true;
            log.info("Item " + elementText + " found!");
        } else {
            log.info("Item " + elementText + " not found!");
        }
        return isElementFound;
    }

    public void waitForElement(int time) throws InterruptedException {
        synchronized (wait) {
            wait.wait(time);
        }
    }

    public void scrollDown() {
        gestures.scrollToElement(SwipeElementDirection.DOWN, "Camera", 1);
        log.info("Scroll Down");
    }
}
