package nsplaydev.pages;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import io.appium.java_client.SwipeElementDirection;
import org.testng.Assert;

public class ComponentsPage extends BasePage {

    public ComponentsPage(boolean isScrolled) throws InterruptedException {
        super();
        UIElement browse = null;
        UIElement detailsElement = this.find.byText("Details");
        if(detailsElement!=null)
        {
            detailsElement=null;
            this.navigateBack();
            this.waitForElement(1000);
            detailsElement = this.find.byText("Details");
            if(detailsElement!=null)
            {
                detailsElement=null;
                this.navigateBack();
                this.waitForElement(1000);
                browse = this.find.byText("Components");
            }
            else
            {
                browse = this.find.byText("Components");
            }
        }
        else
        {
            browse = this.wait.waitForVisible(this.locators.findByTextLocator("Components", true));
        }

        if(isScrolled) {
            UIElement location = this.find.byText("Location");
            if (location == null) {
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
                this.scrollDown();
            }
            location = this.find.byText("Location");
            Assert.assertNotNull(location, "Page Not Scrolled correctly!");
        }
        Assert.assertNotNull(browse, "Components page not loaded!");
        this.log.info("Components page loaded.");
    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String button) throws InterruptedException {

        UIElement buttonToClick = this.find.byText(button);
        if(buttonToClick!=null) {
            buttonToClick.click();
            this.log.info("Navigate to " + button);
            this.waitForElement(2000);
        }
        else {
            this.log.info("Element " + button + " not found! Not able to click it!");
            this.waitForElement(1000);
        }

    }

    public boolean checkIfElementisShown(String elementText) {
        boolean isElementFound = false;
        UIElement element = this.find.byText(elementText);
        if(element != null)
        {
            isElementFound = true;
            this.log.info("Item " + elementText + " found!");
        }
        else
        {
            this.log.info("Item " + elementText + " not found!");
        }
        return isElementFound;
    }

    public void waitForElement(int time) throws InterruptedException {
        synchronized(this.wait) {
            this.wait.wait(time);
        }
    }

    public void scrollDown() {
        UIElement scroll;
        if(settings.deviceName.contains("Api26"))
        {
             scroll = this.wait.waitForVisible(this.locators.findByTextLocator("TextView", true));
        }
        else
        {
             scroll = this.wait.waitForVisible(this.locators.findByTextLocator("DatePicker", true));
        }

        scroll.scrollInElement(SwipeElementDirection.DOWN, 1);
        this.log.info("Scroll Down");
    }
}
