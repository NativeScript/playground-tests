package sync.pages;

import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import io.appium.java_client.SwipeElementDirection;
import org.sikuli.script.*;
import org.testng.Assert;

public class CodeEditorClass extends BasePage {
    public Screen s = new Screen();

    public CodeEditorClass() throws InterruptedException {
        super();
        String currentPath = System.getProperty("user.dir");
        ImagePath.add(currentPath+"/src/test/java/sync/pages/images.sikuli");
    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String button) {
        this.find.byText(button).click();
        this.log.info("Navigate to " + button);
    }

    public void typeCode(String code) {
        this.typeCode(code, null);
    }

    public void typeCode(String code, String whereToType) {
        if (whereToType != null) {
            try {
                s.click(whereToType);
            } catch (FindFailed findFailed) {
                findFailed.printStackTrace();
            }
            try {
                this.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        s.type(code);
    }

    public void deleteAllCode()
    {
        try {
            s.click(new Pattern("NativescriptLogo.png").targetOffset(330,119));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        s.type("a", KeyModifier.CMD);
        s.type(Key.DELETE);

    }

    public void typeValidCode() {
        this.deleteAllCode();
        this.typeCode("<Page loaded=\"pageLoaded\" class=\"page\" xmlns=\"http://www.nativescript.org/tns.xsd\">");
        s.type(Key.ENTER);
        this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\">");
        s.type(Key.ENTER);
        this.typeCode("</ActionBar>");
        s.type(Key.ENTER);
        this.typeCode("<ScrollView>");
        s.type(Key.ENTER);
        this.typeCode("<StackLayout class=\"home-panel\">");
        s.type(Key.ENTER);
        this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />");
        s.type(Key.ENTER);
        this.typeCode("</StackLayout>");
        s.type(Key.ENTER);
        this.typeCode("</ScrollView>");
        s.type(Key.ENTER);
        this.typeCode("</Page>");
    }

    public void save() {
        this.save(null);
    }

    public void save(String waitForChanges) {
        s.type("s", KeyModifier.CMD);
        if(waitForChanges!=null) {
            try {
                this.waitPreviewAppToLoad(30, waitForChanges);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void waitPreviewAppToLoad(int numberOfTries, String waitForChanges) throws InterruptedException {
        while (true)
        {
            UIElement home = this.find.byText(waitForChanges);
            if(home!=null || numberOfTries<=0)
            {
                break;
            }
            else {
                numberOfTries = numberOfTries-1;
                this.wait(1000);
            }
        }
        UIElement home = this.find.byText(waitForChanges);
        Assert.assertNotNull(home, "Preview app not synced with "+waitForChanges);
        this.log.info("Preview app synced with "+waitForChanges);
    }

    public void waitForElement(int time) throws InterruptedException {
        synchronized(this.wait) {
            this.wait.wait(time);
        }
    }

    public void scrollDown() {
        UIElement scroll = this.wait.waitForVisible(this.locators.findByTextLocator("DatePicker", true));
        scroll.scrollInElement(SwipeElementDirection.DOWN, 1);
        this.log.info("Scroll Down");
    }
}
