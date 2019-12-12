package nsplay.pages;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;

public class WebPage extends BasePage {

    public WebPage() throws InterruptedException {
        super();
        synchronized(wait) {
            wait.wait(30000);
        }

        log.info("Web page loaded.");
    }

    /**
     * Verify home page loaded.
     */
}
