package nsplaydev.pages;

import functional.tests.core.enums.SwipeElementDirection;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;

public class ComponentsVisualizationPage extends BasePage {

    public ComponentsVisualizationPage(String component) {
        super();
        UIElement browse = this.wait.waitForVisible(this.locators.findByTextLocator(component, true));
        Assert.assertNotNull(browse, "Components Visualization page not loaded!");
        this.log.info("Components Visualization page loaded.");
    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String button) {
        this.find.byText(button).click();
        this.log.info("Navigate to " + button);
    }

    public void navigateBackPage() {
        this.navigateBack();
    }

    public void scrollDown() {
        UIElement scroll = this.wait.waitForVisible(this.locators.findByTextLocator("DatePicker", true));
        scroll.scrollInElement(SwipeElementDirection.DOWN, 1);
        this.log.info("Scroll Down");
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
}
