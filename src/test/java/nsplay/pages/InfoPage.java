package nsplay.pages;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import io.appium.java_client.SwipeElementDirection;
import org.testng.Assert;

public class InfoPage extends BasePage {

    public InfoPage() {
        super();
        this.find.byText("Info").click();
        UIElement browse = this.wait.waitForVisible(this.locators.findByTextLocator("Info", true));
        Assert.assertNotNull(browse, "Info page not loaded!");
        this.log.info("Info page loaded.");

    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String place) {
        this.find.byText(place).click();
        this.log.info("Navigate to " + place);
    }

    public void scrollDown() {
        UIElement scroll = this.wait.waitForVisible(this.locators.findByTextLocator("Sample QR Code", true));
        scroll.scrollInElement(SwipeElementDirection.DOWN, 2);
        this.log.info("Scroll Down");
    }
}
