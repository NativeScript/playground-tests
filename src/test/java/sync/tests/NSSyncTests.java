package sync.tests;

import functional.tests.core.mobile.basetest.MobileTest;
import org.sikuli.script.FindFailed;
import sync.pages.SetupClass;
import sync.pages.CodeEditorClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class NSSyncTests extends MobileTest {
    SetupClass setupClass;
    @BeforeClass
    public void beforeTest() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException {
        setupClass = new SetupClass(this.client,this.settings, this.device);
        String projectURL = "https://play.telerik.rocks/?template=play-"+ setupClass.typeOfProject+"&debug=true";
        setupClass.NavigateToPage(projectURL);
        setupClass.GetDeviceLink();
        setupClass.startPreviewAppWithLiveSync();
    }

    @Test(description = "Verify home page looks OK.", groups = {"android", "ios"})
    public void test_01_navigate_to_javascript_project() throws Exception {
    CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
    codeEditor.typeValidCode();
    codeEditor.save("Test");
    this.assertScreen("nsplaydev-synced-valid-code", this.settings.shortTimeout);
    }

}
