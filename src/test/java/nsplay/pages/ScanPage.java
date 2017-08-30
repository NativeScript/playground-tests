package nsplay.pages;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;

public class ScanPage extends BasePage {

    public ScanPage() {
        super();
        this.find.byText("Scan").click();
        UIElement browse = this.wait.waitForVisible(this.locators.findByTextLocator("Scan", true));
        Assert.assertNotNull(browse, "Scan page not loaded!");
        this.log.info("Scan page loaded.");
    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String button) {
        this.find.byText(button).click();
        this.log.info("Navigate to " + button);
    }
}
