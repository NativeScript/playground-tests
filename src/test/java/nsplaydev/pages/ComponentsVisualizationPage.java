package nsplaydev.pages;

import functional.tests.core.enums.SwipeElementDirection;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;

public class ComponentsVisualizationPage extends BasePage {

    public ComponentsVisualizationPage(String component) {
        super();
        UIElement browse = wait.waitForVisible(locators.findByTextLocator(component, true));
        Assert.assertNotNull(browse, "Components Visualization page not loaded!");
        log.info("Components Visualization page loaded.");
    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String button) {
        find.byText(button).click();
        log.info("Navigate to " + button);
    }

    public void navigateBackPage() {
        navigateBack();
    }

    public void scrollDown() {
        UIElement scroll = wait.waitForVisible(locators.findByTextLocator("DatePicker", true));
        scroll.scrollInElement(SwipeElementDirection.DOWN, 1);
        log.info("Scroll Down");
    }

    public boolean checkIfElementisShown(String elementText) {
        boolean isElementFound = false;
        UIElement element = find.byText(elementText);
        if(element != null)
        {
            isElementFound = true;
            log.info("Item " + elementText + " found!");
        }
        else
        {
            log.info("Item " + elementText + " not found!");
        }
        return isElementFound;
    }

    public void waitForElement(int time) throws InterruptedException {
        synchronized(wait) {
            wait.wait(time);
        }
    }
}
