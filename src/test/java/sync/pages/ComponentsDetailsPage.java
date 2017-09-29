package sync.pages;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;

public class ComponentsDetailsPage extends BasePage {

    public ComponentsDetailsPage() {
        super();
        UIElement browse = this.wait.waitForVisible(this.locators.findByTextLocator("Details", true));
        Assert.assertNotNull(browse, "Details page not loaded!");
        this.log.info("Details page loaded.");
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
}
