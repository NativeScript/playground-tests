package sync.tests;

import functional.tests.core.mobile.basetest.MobileTest;
import org.openqa.selenium.logging.LogEntries;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.KeyModifier;
import org.sikuli.script.Pattern;
import org.testng.Assert;
import org.testng.annotations.*;
import sync.pages.SetupClass;
import sync.pages.CodeEditorClass;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import static org.sikuli.script.Button.WHEEL_DOWN;
import static org.sikuli.script.Button.WHEEL_UP;

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
            this.setupClass.s.type(Key.ESC);
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
        String previewAppVersionExpected = "1.8.0";

        String runtimeVersionExpected="";
        if(settings.deviceType == settings.deviceType.Emulator) {

            runtimeVersionExpected ="3.4.1";
        }
        else {
            runtimeVersionExpected = "3.4.1";
        }

        String componentVersionsExpected = "{\n" +
                "  \"@angular/animations\": \"5.2.0\",\n" +
                "  \"@angular/common\": \"5.2.0\",\n" +
                "  \"@angular/compiler\": \"5.2.0\",\n" +
                "  \"@angular/core\": \"5.2.0\",\n" +
                "  \"@angular/forms\": \"5.2.0\",\n" +
                "  \"@angular/http\": \"5.2.0\",\n" +
                "  \"@angular/platform-browser\": \"5.2.0\",\n" +
                "  \"@angular/platform-browser-dynamic\": \"5.2.0\",\n" +
                "  \"@angular/router\": \"5.2.0\",\n" +
                "  \"kinvey-nativescript-sdk\": \"3.9.7\",\n" +
                "  \"nativescript-accelerometer\": \"2.0.1\",\n" +
                "  \"nativescript-angular\": \"5.2.0\",\n" +
                "  \"nativescript-geolocation\": \"4.2.2\",\n" +
                "  \"nativescript-pro-ui\": \"3.3.0\",\n" +
                "  \"nativescript-theme-core\": \"1.0.4\",\n" +
                "  \"reflect-metadata\": \"0.1.12\",\n" +
                "  \"rxjs\": \"5.5.6\",\n" +
                "  \"tns-core-modules\": \"3.4.0\",\n" +
                "  \"zone.js\": \"0.8.20\"\n" +
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
            //remove after bug in {N}
            //this.assertScreen("nsplaydev-synced-invalid-code", this.settings.shortTimeout);
            if(settings.deviceType == settings.deviceType.Simulator) {
                this.setupClass.wait(5000);
                this.context.client.driver.launchApp();
                this.setupClass.wait(4000);
            }
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
        codeEditor.openFile(new Pattern("appcss").similar(0.70f));
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
            if(this.setupClass.browser.equals("Safari"))
            {
                this.setupClass.s.hover("angulartsSafari");
                this.setupClass.wait(1000);
                this.setupClass.s.wheel(WHEEL_UP,3);
                codeEditor.openFile(new Pattern("home.png").targetOffset(75,88));
            }
            else {
                this.setupClass.s.hover("angularts");
                this.setupClass.wait(1000);
                this.setupClass.s.wheel(WHEEL_UP,3);
                codeEditor.openFile(new Pattern("home.png").targetOffset(75,88));
            }
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            if(this.setupClass.browser.equals("Safari"))
            {
                codeEditor.openFile(new Pattern("javascriptjsSafari").similar(0.88f));
            }
            else {
                codeEditor.openFile(new Pattern("javascriptjs").similar(0.88f));
            }
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            if(this.setupClass.browser.equals("Safari"))
            {
                codeEditor.openFile(new Pattern("typescripttsSafari").similar(0.88f));
            }
            else {
                codeEditor.openFile(new Pattern("typescriptts").similar(0.88f));
            }
        }

        codeEditor.typeJSTSCode(true);
        this.setupClass.getScreenShot(this.context.getTestName()+"_AfterEnterCode");
        this.setupClass.wait(1000);
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

        Assert.assertEquals(expectedText.trim(), deviceLog.trim(),"Expected text \""+expectedText.trim()+ "\" is not equal to \""+deviceLog.trim()+"\" .");

    }

    @Test(description = "Verify js/ts invalid code change is applied corrected!", groups = {"android", "ios"})
    public void test_07_invalid_code_change_to_js_ts() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        codeEditor.typeJSTSCode(false);
        this.setupClass.getScreenShot(this.context.getTestName()+"_AfterEnterErrorCode");
        String errorText = codeEditor.getErrorsTextFromErrorTab();
        String expectedText = "";
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            expectedText = "home/home.component.ts";
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            expectedText = "home/home-page.js";
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            expectedText = "home/home-view-model.ts";
        }

        Assert.assertTrue(errorText.contains(expectedText),"Expected text \""+expectedText+ "\" does not contains \""+errorText+"\" .");
        this.setupClass.wait(2000);
        codeEditor.save();
        codeEditor.assertImageIsOnScreen("ErrorDialogWhenErrorInCode");
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
        this.setupClass.s.type(Key.ESC);
        this.setupClass.wait(3000);
        codeEditor.typeJSTSCode(true);
        codeEditor.save();
        this.setupClass.wait(3000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
    }

    @Test(description = "Verify javascript error is handle correctly!", groups = {"android", "ios"})
    public void test_08_javascript_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if(this.context.lastTestResult!=1)
        {
            codeEditor.typeJSTSCode(true);
        }
        codeEditor.clearDeviceLogs();
        if(this.setupClass.browser.equals("Safari"))
        {
            setupClass.s.click("ErrorsNotSelectedSafari");
        }
        else {
            setupClass.s.click("ErrorsNotSelected");
        }
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            if(this.setupClass.browser.equals("Safari"))
            {
                codeEditor.openFile(new Pattern("home.png").targetOffset(75,88));
            }
            else {
                codeEditor.openFile(new Pattern("home.png").targetOffset(75,88));
            }
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            if(this.setupClass.browser.equals("Safari"))
            {
                codeEditor.openFile(new Pattern("javascriptjsSafari").similar(0.88f));
            }
            else {
                codeEditor.openFile(new Pattern("javascriptjs").similar(0.88f));
            }
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            if(this.setupClass.browser.equals("Safari"))
            {
                codeEditor.openFile(new Pattern("typescripttsSafari").similar(0.88f));
            }
            else {
                codeEditor.openFile(new Pattern("typescriptts").similar(0.88f));
            }
        }
        this.setupClass.wait(2000);
        codeEditor.typeJSTSCodeWithThrowError();
        this.setupClass.getScreenShot(this.context.getTestName()+"_AfterEnterErrorCode");
        codeEditor.save();
        if(settings.deviceType == settings.deviceType.Simulator) {
            this.setupClass.wait(7000);
            this.context.client.driver.launchApp();
            this.setupClass.wait(4000);
        }
        else
        {
            this.setupClass.wait(4000);
        }
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            this.assertScreen("nsplaydev-synced-javascript-error-ng", this.settings.shortTimeout,20);
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            this.assertScreen("nsplaydev-synced-javascript-error-js", this.settings.shortTimeout,20);
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            this.assertScreen("nsplaydev-synced-javascript-error-tsc", this.settings.shortTimeout,20);
        }
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText=null;
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            if(settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "ERROR: ERROR Error";
            }
            else {
                expectedText = "Error: Error";
            }
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            if(settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "JS ERROR Error: Error";
            }
            else {
                expectedText = "Error: Error";
            }
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            if(settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "nsplaydev";
            }
            else {
                expectedText = "Error: Error";
            }
        }


        Assert.assertTrue(deviceLog.contains(expectedText),"Actual log \""+deviceLog+ "\" does not cointains the expected text \""+expectedText+"\" .");

        codeEditor.typeJSTSCode(true);
        codeEditor.save();
        this.setupClass.wait(3000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);

    }

    @Test(description = "Verify java error is handle correctly!", groups = {"android", "ios"})
    public void test_09_java_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if(this.context.lastTestResult!=1)
        {
            codeEditor.typeJSTSCode(true);
            codeEditor.save();
            this.setupClass.wait(3000);
        }
        codeEditor.clearDeviceLogs();
        this.setupClass.wait(1000);

        if(this.setupClass.browser.equals("Safari"))
        {
            setupClass.s.click("ErrorsNotSelectedSafari");
        }
        else {
            setupClass.s.click("ErrorsNotSelected");
        }
        this.setupClass.wait(1000);
        codeEditor.typeJSTSCodeWithThrowJavaError();
        this.setupClass.getScreenShot(this.context.getTestName()+"_AfterEnterErrorCode");
        codeEditor.save();
        if(settings.deviceType == settings.deviceType.Simulator) {
            this.setupClass.wait(7000);
            this.context.client.driver.launchApp();
            this.setupClass.wait(4000);
        }
        else
        {
            this.setupClass.wait(4000);
        }
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            this.assertScreen("nsplaydev-synced-java-error-ng", this.settings.shortTimeout,20);
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            this.assertScreen("nsplaydev-synced-java-error-js", this.settings.shortTimeout,20);
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            this.assertScreen("nsplaydev-synced-java-error-tsc", this.settings.shortTimeout,20);
        }
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText=null;
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            if(settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "Can't find variable: java";
            }
            else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if(settings.deviceName.contains("Api23")||settings.deviceName.contains("Api22")||settings.deviceName.contains("Api21")||settings.deviceName.contains("Api19"))
                {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }
            }
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            if(settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "Can't find variable: java";
            }
            else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if(settings.deviceName.contains("Api23")||settings.deviceName.contains("Api22")||settings.deviceName.contains("Api21")||settings.deviceName.contains("Api19"))
                {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }

            }
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            if(settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "nsplaydev";
            }
            else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if(settings.deviceName.contains("Api23")||settings.deviceName.contains("Api22")||settings.deviceName.contains("Api21")||settings.deviceName.contains("Api19"))
                {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }

            }
        }


        Assert.assertTrue(deviceLog.contains(expectedText),"Actual log \""+deviceLog+ "\" does not cointains the expected text \""+expectedText+"\" .");

        codeEditor.typeJSTSCode(true);
        codeEditor.save();
        this.setupClass.wait(5000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
    }

    @Test(description = "Verify ios error is handle correctly!", groups = {"android", "ios"})
    public void test_10_ios_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if(this.context.lastTestResult!=1)
        {
            codeEditor.typeJSTSCode(true);
            codeEditor.save();
            this.setupClass.wait(3000);
        }
        codeEditor.clearDeviceLogs();
        if(this.setupClass.browser.equals("Safari"))
        {
            setupClass.s.click("ErrorsNotSelectedSafari");
        }
        else {
            setupClass.s.click("ErrorsNotSelected");
        }
        codeEditor.typeJSTSCodeWithThrowiOSError();
        this.setupClass.getScreenShot(this.context.getTestName()+"_AfterEnterErrorCode");
        codeEditor.save();
        if(settings.deviceType == settings.deviceType.Simulator) {
            this.setupClass.wait(7000);
            this.context.client.driver.launchApp();
            this.setupClass.wait(4000);
        }
        else
        {
            this.setupClass.wait(4000);
        }
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            this.assertScreen("nsplaydev-synced-ios-error-ng", this.settings.shortTimeout,20);
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            this.assertScreen("nsplaydev-synced-ios-error-js", this.settings.shortTimeout,20);
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            this.assertScreen("nsplaydev-synced-ios-error-tsc", this.settings.shortTimeout,20);
        }
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText=null;
        if(this.setupClass.typeOfProject.equals("ng"))
        {
            if(settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "not-existing-path";
            }
            else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            if(settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "not-existing-path";
            }
            else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            if(settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "nsplaydev";
            }
            else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        }


        Assert.assertTrue(deviceLog.contains(expectedText),"Actual log \""+deviceLog+ "\" does not cointains the expected text \""+expectedText+"\" .");

        codeEditor.typeJSTSCode(true);
        codeEditor.save();
        this.setupClass.wait(3000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);

    }

    @Test(description = "Verify ios cocoa error is handle correctly!", groups = {"android", "ios"})
    public void test_11_ios_cocoa_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if(this.context.lastTestResult!=1)
        {
            codeEditor.typeJSTSCode(true);
            codeEditor.save();
            this.setupClass.wait(3000);
        }
        if(settings.deviceType == settings.deviceType.Simulator) {
            codeEditor.clearDeviceLogs();
            this.device.cleanConsoleLog();
            if(this.setupClass.browser.equals("Safari"))
            {
                setupClass.s.click("ErrorsNotSelectedSafari");
            }
            else {
                setupClass.s.click("ErrorsNotSelected");
            }
            codeEditor.typeJSTSCodeWithThrowiOSCocoaError();
            codeEditor.save();
            if (settings.deviceType == settings.deviceType.Simulator) {
                this.setupClass.wait(4000);
                this.context.client.driver.launchApp();
                this.setupClass.wait(4000);
            } else {
                this.setupClass.wait(4000);
            }
            if (this.setupClass.typeOfProject.equals("ng")) {
                this.assertScreen("nsplaydev-synced-ios-cocoa-error-ng", this.settings.shortTimeout, 20);
            } else if (this.setupClass.typeOfProject.equals("js")) {
                this.assertScreen("nsplaydev-synced-ios-cocoa-error-js", this.settings.shortTimeout, 20);
            } else if (this.setupClass.typeOfProject.equals("tsc")) {
                this.assertScreen("nsplaydev-synced-ios-cocoa-error-tsc", this.settings.shortTimeout, 20);
            }
            String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
            this.setupClass.getScreenShot(this.context.getTestName()+"_AfterEnterErrorCode");
            String expectedText = null;
            if (this.setupClass.typeOfProject.equals("ng")) {
                if (settings.deviceType == settings.deviceType.Simulator) {
                    expectedText = "The Preview app has terminated unexpectedly";
                } else {
                    expectedText = "Error: Error";
                }
            } else if (this.setupClass.typeOfProject.equals("js")) {
                if (settings.deviceType == settings.deviceType.Simulator) {
                    expectedText = "Uncaught Exception";
                } else {
                    expectedText = "Error: Error";
                }
            } else if (this.setupClass.typeOfProject.equals("tsc")) {
                if (settings.deviceType == settings.deviceType.Simulator) {
                    expectedText = "nsplaydev";
                } else {
                    expectedText = "Error: Error";
                }
            }

            Assert.assertTrue(deviceLog.contains(expectedText), "Actual log \"" + deviceLog + "\" does not cointains the expected text \"" + expectedText + "\" .");

            codeEditor.typeJSTSCode(true);
            codeEditor.save();
            this.setupClass.wait(3000);
            this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.shortTimeout);
        }
    }

    @Test(description = "Verify saved session is loaded correctly!", groups = {"android", "ios"})
    public void test_12_open_saved_session() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        this.setupClass.wait(6000);
        if(this.context.lastTestResult!=1)
        {
            codeEditor.typeJSTSCode(true);
            codeEditor.save();
            this.setupClass.wait(6000);
        }
        //shit appium ios 10 and 9
        if(settings.deviceType == settings.deviceType.Simulator)
        {
            this.setupClass.changeIosDriverToWebView();
            this.setupClass.wait(6000);
        }
        else
        {
            this.setupClass.refreshAndroidDriver();
            this.setupClass.wait(6000);
        }


        if(this.setupClass.typeOfProject.equals("ng"))
        {
            this.setupClass.openURL("https://play.telerik.rocks/?template=play-ng&id=MGByvi");
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            this.setupClass.openURL("https://play.telerik.rocks/?template=play-js&id=oRM07v");
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            this.setupClass.openURL("https://play.telerik.rocks/?template=play-tsc&id=Si8yCG");
        }
        this.setupClass.wait(10000);
        this.setupClass.navigateToSavedSession("Tap to open the saved");
        this.setupClass.wait(7000);

        this.assertScreen("nsplaydev-synced-saved-session", this.settings.shortTimeout,10);

        //shit appium ios 10 and 9
        if(settings.deviceType == settings.deviceType.Simulator)
        {
            this.setupClass.restoreIosDriver();
        }
    }
}

