package nsplay.pages;

import functional.tests.core.mobile.basepage.BasePage;

public class WebPage extends BasePage {

    public WebPage() throws InterruptedException {
        super();
        synchronized (this.wait) {
            this.wait.wait(30000);
        }

        this.log.info("Web page loaded.");
    }

    /**
     * Verify home page loaded.
     */
}
