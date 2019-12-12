package nsplay.pages;

import functional.tests.core.enums.SwipeElementDirection;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;

public class InfoPage extends BasePage {

    public InfoPage() {
        super();
        find.byText("Info").click();
        UIElement browse = wait.waitForVisible(locators.findByTextLocator("Info", true));
        Assert.assertNotNull(browse, "Info page not loaded!");
        log.info("Info page loaded.");

    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String place) {
        find.byText(place).click();
        log.info("Navigate to " + place);
    }

    public void scrollDown() {
        UIElement scroll = wait.waitForVisible(locators.findByTextLocator("Sample QR Code", true));
        scroll.scrollInElement(SwipeElementDirection.DOWN, 2);
        log.info("Scroll Down");
    }
}
