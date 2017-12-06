package sync.tests;

import functional.tests.core.mobile.basetest.MobileTest;
import org.sikuli.script.FindFailed;
import org.testng.annotations.*;
import sync.pages.SetupClass;
import sync.pages.CodeEditorClass;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class  NSSyncTests extends MobileTest {
    SetupClass setupClass;
    @BeforeClass
    public void beforeClass() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException {
        this.setupClass = new SetupClass(this.client,this.settings, this.device);
        String projectURL = "https://play.telerik.rocks/?template=play-"+ setupClass.typeOfProject+"&debug=true";
        this.setupClass.getScreenShot("BeforeStartOfTests_BeforeNavigateToProject");
        this.setupClass.NavigateToPage(projectURL);
        this.setupClass.getScreenShot("BeforeStartOfTests_AfterNavigateToProject");
        this.setupClass.GetDeviceLink();
        this.setupClass.getScreenShot("BeforeStartOfTests_AfterGetDeviceLink");
        this.setupClass.startPreviewAppWithLiveSync();
        this.setupClass.getScreenShot("BeforeStartOfTests_AfterLiveSync");

    }
    @AfterClass
    public void afterClass() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException {
        this.setupClass.wait(2000);
        this.setupClass.CloseBrowser();
        this.setupClass.wait(2000);
    }

    @BeforeMethod
    public void beforeTest() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException {
        this.setupClass.giveFocus();
        this.setupClass.getScreenShot(this.context.getTestName()+"_BeforeStart");
    }

    @AfterMethod
    public void afterTest() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException {
        this.setupClass.getScreenShot(this.context.getTestName()+"_AfterStart");
    }

    @Test(description = "Verify home page looks OK.", groups = {"android", "ios"})
    public void test_01_navigate_to_javascript_project() throws Exception {
    CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
    codeEditor.typeValidCode();
    codeEditor.save("Test");
    this.assertScreen("nsplaydev-synced-valid-code", this.settings.shortTimeout);
    }

}
