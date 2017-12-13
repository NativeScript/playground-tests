package sync.tests;

import functional.tests.core.mobile.basetest.MobileTest;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.testng.Assert;
import org.testng.annotations.*;
import sync.pages.SetupClass;
import sync.pages.CodeEditorClass;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class  NSSyncTests extends MobileTest {
    SetupClass setupClass;
    public String deviceName;
    @BeforeClass
    public void beforeClass() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException {
        this.setupClass = new SetupClass(this.client,this.settings, this.device);
        String projectURL = "https://play.telerik.rocks/?template=play-"+ setupClass.typeOfProject+"&debug=true";
        this.setupClass.getScreenShot("BeforeStartOfTests_BeforeNavigateToProject");
        this.setupClass.giveFocus();
        this.setupClass.NavigateToPage(projectURL);
        this.setupClass.getScreenShot("BeforeStartOfTests_AfterNavigateToProject");
        this.setupClass.giveFocus();
        this.setupClass.GetDeviceLink();
        this.setupClass.getScreenShot("BeforeStartOfTests_AfterGetDeviceLink");
        this.setupClass.giveFocus();
        this.setupClass.startPreviewAppWithLiveSync();
        this.setupClass.giveFocus();
        this.setupClass.getScreenShot("BeforeStartOfTests_AfterLiveSync");
        this.setupClass.closeTutorial();
        this.setupClass.getScreenShot("BeforeStartOfTests_AfterCloseTutorial");
    }
    @AfterClass
    public void afterClass() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException {
        if(settings.deviceName.contains("Api25")==false)
        {
            this.setupClass.wait(2000);
            this.setupClass.CloseBrowser();
            this.setupClass.wait(2000);
        }
        else
        {
            CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
            codeEditor.save();
            this.setupClass.s.type("q", KeyModifier.CMD);
            this.setupClass.wait(2000);
            this.setupClass.s.type(Key.ENTER);
            this.setupClass.wait(2000);
        }
    }

    @BeforeMethod
    public void beforeTest() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException {
        this.setupClass.giveFocus();
        this.setupClass.getScreenShot(this.context.getTestName()+"_BeforeStart");
        this.context.shouldRestartAppOnFailure = false;
    }

    @AfterMethod
    public void afterTest() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException {
        this.setupClass.getScreenShot(this.context.getTestName()+"_AfterStart");
        if(this.context.lastTestResult == 1)
        {
            this.setupClass.s.type(Key.ENTER);
            this.setupClass.getScreenShot(this.context.getTestName()+"_AfterStart_AfterEnter");
        }
    }
    @Test(description = "Verify devices tab is showing valid data!", groups = {"android", "ios"})
    public void test_01_verify_devices_tab() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        this.setupClass.wait(7000);
        if(settings.deviceType == settings.deviceType.Emulator) {
            this.deviceName = "Android SDK built for x86";

        }
        else {
            this.deviceName = this.setupClass.getComputerName();
            if(this.settings.platformVersion.toString().contains("9."))
            {
                this.deviceName = "iPhone Simulator";

            }
        }
        String modelExpected;
        if(settings.deviceType == settings.deviceType.Emulator) {
            modelExpected = "Android SDK built for x86";

        }
        else {
            modelExpected = "Simulator";
        }
        String osVersionExpected = "";
        if(settings.deviceType == settings.deviceType.Emulator) {
            osVersionExpected ="Android ";
            osVersionExpected = osVersionExpected+this.setupClass.getIOSVersion();

        }
        else {
            osVersionExpected ="iOS ";
            osVersionExpected = osVersionExpected+String.valueOf(this.context.client.driver.getCapabilities().getCapability("platformVersion"));
        }
        String previewAppVersionExpected = "1.7.0";

        String runtimeVersionExpected="";
        if(settings.deviceType == settings.deviceType.Emulator) {

            runtimeVersionExpected ="3.3.1";
        }
        else {
            runtimeVersionExpected = "3.3.0";
        }

        String componentVersionsExpected = "{\n" +
                "  \"@angular/animations\": \"4.4.6\",\n" +
                "  \"@angular/common\": \"4.4.6\",\n" +
                "  \"@angular/compiler\": \"4.4.6\",\n" +
                "  \"@angular/core\": \"4.4.6\",\n" +
                "  \"@angular/forms\": \"4.4.6\",\n" +
                "  \"@angular/http\": \"4.4.6\",\n" +
                "  \"@angular/platform-browser\": \"4.4.6\",\n" +
                "  \"@angular/router\": \"4.4.6\",\n" +
                "  \"nativescript-angular\": \"4.4.1\",\n" +
                "  \"nativescript-accelerometer\": \"2.0.1\",\n" +
                "  \"nativescript-geolocation\": \"4.2.0\",\n" +
                "  \"nativescript-pro-ui\": \"3.2.0\",\n" +
                "  \"nativescript-theme-core\": \"1.0.4\",\n" +
                "  \"reflect-metadata\": \"0.1.10\",\n" +
                "  \"rxjs\": \"5.4.3\",\n" +
                "  \"tns-core-modules\": \"3.3.0\",\n" +
                "  \"zone.js\": \"0.8.18\"\n" +
                "}";
        codeEditor.assertDeviceTab(this.deviceName, modelExpected, osVersionExpected, previewAppVersionExpected, runtimeVersionExpected, componentVersionsExpected);
    }
    @Test(description = "Verify XML/HTML valid code change is apllied!", groups = {"android", "ios"})
    public void test_02_valid_code_change_to_xml_or_html() throws Exception {
    CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
    codeEditor.typeXMLOrHTMLCode(true);
    codeEditor.save("Test");
    this.assertScreen("nsplaydev-synced-valid-code", this.settings.shortTimeout);
    }

    @Test(description = "Verify XML/HTML invalid code change is apllied and after fix is corrected!", groups = {"android", "ios"})
    public void test_03_invalid_code_change_to_xml_or_html() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        codeEditor.typeXMLOrHTMLCode(false);
        codeEditor.save();
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            this.assertScreen("nsplaydev-synced-invalid-code-ng", this.settings.shortTimeout);

        }
        else {
            this.assertScreen("nsplaydev-synced-invalid-code", this.settings.shortTimeout);
        }
        codeEditor.typeXMLOrHTMLCode(true);
        codeEditor.save("Test");
        this.assertScreen("nsplaydev-synced-valid-code", this.settings.shortTimeout);
    }

    @Test(description = "Verify css valid code change is applied corrected!", groups = {"android", "ios"})
    public void test_04_valid_code_change_to_css() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if(this.context.lastTestResult!=1)
        {
            codeEditor.typeXMLOrHTMLCode(true);
        }
        codeEditor.openFile("appcss");
        codeEditor.typeCSSCode(true);
        codeEditor.save();
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
    }

    @Test(description = "Verify css invalid code change is applied corrected!", groups = {"android", "ios"})
    public void test_05_invalid_code_change_to_css() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        codeEditor.typeCSSCode(false);
        String errorText = codeEditor.getErrorsTextFromErrorTab();
        String expectedText = "app.css (12, 1): [css] at-rule or selector expected";
        Assert.assertEquals(errorText, expectedText,"Expected text \""+expectedText+ "\" is not equal to \""+errorText+"\" .");
        this.setupClass.wait(2000);
        codeEditor.save();
        this.setupClass.wait(2000);
        codeEditor.assertImageIsOnScreen("ErrorDialogWhenErrorInCode");
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
        this.setupClass.wait(2000);
        this.setupClass.s.type(Key.ESC);
        this.setupClass.wait(5000);
        codeEditor.typeCSSCode(true);
        codeEditor.save();
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
    }

    @Test(description = "Verify js/ts valid code change is applied corrected!", groups = {"android", "ios"})
    public void test_06_valid_code_change_to_js_ts() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if(this.context.lastTestResult!=1)
        {
            this.setupClass.s.type(Key.ESC);
            this.setupClass.wait(2000);
            codeEditor.typeCSSCode(true);
        }
        codeEditor.clearDeviceLogs();
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            if(this.setupClass.browser=="Safari")
            {
                codeEditor.openFile("angulartsSafari");
            }
            else {
                codeEditor.openFile("angularts");
            }
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            if(this.setupClass.browser=="Safari")
            {
                codeEditor.openFile("javascriptjsSafari");
            }
            else {
                codeEditor.openFile("javascriptjs");
            }
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            if(this.setupClass.browser=="Safari")
            {
                codeEditor.openFile("typescripttsSafari");
            }
            else {
                codeEditor.openFile("typescriptts");
            }
        }

        codeEditor.typeJSTSCode(true);
        codeEditor.save();
        this.setupClass.wait(3000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText;
        if(settings.deviceType == settings.deviceType.Emulator) {
            expectedText = "["+this.deviceName+"]"+": log";

        }
        else {
            expectedText = "[" + this.deviceName + "]" + ": log";
        }

        Assert.assertEquals(expectedText, deviceLog,"Expected text \""+expectedText+ "\" is not equal to \""+deviceLog+"\" .");

    }

    @Test(description = "Verify js/ts invalid code change is applied corrected!", groups = {"android", "ios"})
    public void test_07_invalid_code_change_to_js_ts() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        codeEditor.typeJSTSCode(false);
        String errorText = codeEditor.getErrorsTextFromErrorTab();
        String expectedText = "";
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            expectedText = "home/home.component.ts (17, 2): Declaration or statement expected.";
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            expectedText = "home/home-page.js (12, 1): Declaration or statement expected.";
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            expectedText = "home/home-view-model.ts (10, 1): Declaration or statement expected.";
        }

        Assert.assertEquals(errorText, expectedText,"Expected text \""+expectedText+ "\" is not equal to \""+errorText+"\" .");
        this.setupClass.wait(2000);
        codeEditor.save();
        codeEditor.assertImageIsOnScreen("ErrorDialogWhenErrorInCode");
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
        this.setupClass.s.type(Key.ESC);
        this.setupClass.wait(3000);
        codeEditor.typeJSTSCode(true);
        codeEditor.save();
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
    }

}
