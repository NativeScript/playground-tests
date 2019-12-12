package nsplay.pages;

import functional.tests.core.enums.DeviceType;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;

public class QRPage extends BasePage {

    public QRPage() {
        super();
        UIElement browse = null;
        if (settings.deviceType == DeviceType.iOS) {
            browse = wait.waitForVisible(locators.findByTextLocator("Close", true));
        } else if (settings.deviceType == DeviceType.Android) {
            browse = wait.waitForVisible(locators.findByTextLocator("Close", true));
        } else if (settings.deviceType == DeviceType.Emulator) {
            browse = wait.waitForVisible(locators.findByTextLocator("Place a barcode inside the viewfinder rectangle to scan it.", true));
        }

        Assert.assertNotNull(browse, "QRPage page not loaded!");
        log.info("QRPage page loaded.");
    }

    public void navigateBack(String button) {
        navigateBack();
        log.info("Navigate Back");
    }
}
