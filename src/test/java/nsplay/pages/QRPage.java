package nsplay.pages;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;

public class QRPage extends BasePage {

    public QRPage() {
        super();
        UIElement browse = null;
        if(settings.deviceType == settings.deviceType.iOS  ) {
            browse = this.wait.waitForVisible(this.locators.findByTextLocator("Close", true));
        }
        else if(settings.deviceType == settings.deviceType.Android)
        {
            browse = this.wait.waitForVisible(this.locators.findByTextLocator("Close", true));
        }
        else if(settings.deviceType == settings.deviceType.Emulator)
        {
            browse = this.wait.waitForVisible(this.locators.findByTextLocator("Place a barcode inside the viewfinder rectangle to scan it.", true));
        }

        Assert.assertNotNull(browse, "QRPage page not loaded!");
        this.log.info("QRPage page loaded.");
    }

    public void navigateBack(String button) {
        this.navigateBack();
        this.log.info("Navigate Back");
    }
}
