package sync.pages;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import io.appium.java_client.SwipeElementDirection;
import org.testng.Assert;
import org.sikuli.script.*;

public class ComponentsVisualizationPage extends BasePage {
public  Screen s;
    public ComponentsVisualizationPage() throws InterruptedException {
        super();
        this.s = new Screen();
        //ImagePath.add("TestSikuli/images");
        this.OpenSafari();
        UIElement browse = this.wait.waitForVisible(this.locators.findByTextLocator("Components", true));
        Assert.assertNotNull(browse, "Components page not loaded!");
        this.log.info("Components page loaded.");
    }


    public void OpenSafari() throws InterruptedException {
        this.wait(1000);
        s.type(Key.SPACE, KeyModifier.CMD);
        this.wait(1000);
        s.type("Safari");
        this.wait(1000);
        s.type(Key.ENTER);
        this.wait(10000);
        s.type("f", KeyModifier.CMD+KeyModifier.CTRL);
        this.wait(1000);
    }

    public void NavigateToPage(String URL) throws InterruptedException {
        s.type(URL);
        this.wait(1000);
        s.type(Key.ENTER);
        this.wait(10000);

    }
    public void wait(int time) throws InterruptedException {
        synchronized(this.s) {
            this.s.wait(time);
        }
    }
}
