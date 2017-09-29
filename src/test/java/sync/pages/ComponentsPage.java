package sync.pages;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import io.appium.java_client.SwipeElementDirection;
import org.testng.Assert;

public class ComponentsPage extends BasePage {

    public ComponentsPage(boolean isScrolled) throws InterruptedException {
        super();
        UIElement browse = this.wait.waitForVisible(this.locators.findByTextLocator("Components", true));
        if(browse==null)
        {
            this.navigateBack();
            this.waitForElement(500);
            browse = this.find.byText("Components");
        }
        if(browse==null)
        {
            this.navigateBack();
            this.waitForElement(500);
            browse = this.find.byText("Components");
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
    public void navigate(String button) {
        this.find.byText(button).click();
        this.log.info("Navigate to " + button);
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
        UIElement scroll = this.wait.waitForVisible(this.locators.findByTextLocator("DatePicker", true));
        scroll.scrollInElement(SwipeElementDirection.DOWN, 1);
        this.log.info("Scroll Down");
    }
}
