package sync.tests;

import functional.tests.core.mobile.basetest.MobileTest;
import org.openqa.selenium.By;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.*;
import sync.pages.SetupClass;
import sync.pages.CodeEditorClass;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class  NSSyncTests extends MobileTest {
    SetupClass setupClass;
    public String deviceName;
    CodeEditorClass codeEditor;

    @BeforeClass
    public void beforeClass() throws IOException, InterruptedException, FindFailed, UnsupportedFlavorException, AWTException {
        this.setupClass = new SetupClass(this.client, this.settings, this.device);
        String projectURL = "https://play.nativescript.org/?template=play-" + setupClass.typeOfProject + "&debug=true";

        if(setupClass.isHMREnabled.contains("false"))
        {
            projectURL = projectURL + "&enableHMR=false";
        }

        if (projectURL.contains("play.nativescript.org")) {
            this.setupClass.isLive = true;
        }

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
        this.codeEditor = new CodeEditorClass(this.setupClass);
        this.setupClass.wait(5000);
        if(this.setupClass.driver.findElements(By.cssSelector("iframe[title='Intercom Live Chat']")).size() != 0) {
            this.setupClass.driver.switchTo().frame(this.setupClass.driver.findElement(By.cssSelector("iframe[title='Intercom Live Chat']")));
            if (this.setupClass.driver.findElements(By.xpath("//*[@class='intercom-note-close intercom-anchor']")).size() != 0) {
                    this.setupClass.driver.findElements(By.xpath("//*[@class='intercom-note-close intercom-anchor']")).get(0).click();
            }
            this.setupClass.driver.switchTo().defaultContent();
            this.setupClass.wait(5000);
        }
    }

    @AfterClass
    public void afterClass() {
        setupClass.driver.quit();
    }

    @BeforeMethod
    public void beforeTest() {
        this.setupClass.getScreenShot(this.context.getTestName() + "_BeforeStart");
        this.context.shouldRestartAppOnFailure = false;
    }

    @AfterMethod
    public void afterTest() {
        if (this.context.lastTestResult != 1) {
            this.codeEditor.pressButton(KeyEvent.VK_ESCAPE);
            this.setupClass.getScreenShot(this.context.getTestName() + "_AfterStart_Fail");
        }
        else
        {
            this.setupClass.getScreenShot(this.context.getTestName() + "_AfterStart_Success");
        }
    }

    @Test(description = "Verify devices tab is showing valid data!", groups = {"android", "ios"})
    public void test_01_verify_devices_tab() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);

        if (settings.deviceType == settings.deviceType.Emulator) {
            this.deviceName = "Android SDK built for x86";

        } else {
            this.deviceName = this.setupClass.getComputerName();
            if (this.settings.platformVersion.toString().contains("9.")) {
                this.deviceName = "iPhone Simulator";

            }
            if (this.settings.platformVersion.toString().contains("12.")) {
                this.deviceName = "iPhone 7 12";

            }
        }
        String modelExpected;
        if (settings.deviceType == settings.deviceType.Emulator) {
            modelExpected = "Android SDK built for x86";

        } else {
            modelExpected = "Simulator";
        }
        String osVersionExpected = "";
        if (settings.deviceType == settings.deviceType.Emulator) {
            osVersionExpected = "Android ";
            osVersionExpected = osVersionExpected + String.valueOf(this.context.client.driver.getCapabilities().getCapability("platformVersion"));
        } else {
            osVersionExpected = "iOS ";
            osVersionExpected = osVersionExpected + this.setupClass.getIOSVersion();
        }

        String previewAppVersionExpected = "";
        if (settings.deviceType == settings.deviceType.Emulator) {

            previewAppVersionExpected = "1.23.0";
        } else {
            previewAppVersionExpected = "1.23.0";
        }

        String runtimeVersionExpected = "";
        if (settings.deviceType == settings.deviceType.Emulator) {

            runtimeVersionExpected = "6.0.0";
        } else {
            runtimeVersionExpected = "6.0.0";
        }

        String componentVersionsExpected = "{\n" +
                "  \"@angular/animations\": \"8.0.3\",\n" +
                "  \"@angular/common\": \"8.0.3\",\n" +
                "  \"@angular/compiler\": \"8.0.3\",\n" +
                "  \"@angular/core\": \"8.0.3\",\n" +
                "  \"@angular/forms\": \"8.0.3\",\n" +
                "  \"@angular/http\": \"8.0.0-beta.10\",\n" +
                "  \"@angular/platform-browser\": \"8.0.3\",\n" +
                "  \"@angular/platform-browser-dynamic\": \"8.0.3\",\n" +
                "  \"@angular/router\": \"8.0.3\",\n" +
                "  \"@progress-nativechat/nativescript-nativechat\": \"2.0.4\",\n" +
                "  \"kinvey-nativescript-sdk\": \"4.2.0\",\n" +
                "  \"nativescript-accelerometer\": \"2.0.1\",\n" +
                "  \"nativescript-angular\": \"8.0.0\",\n" +
                "  \"nativescript-background-http\": \"3.4.1\",\n" +
                "  \"nativescript-camera\": \"4.5.0\",\n" +
                "  \"nativescript-geolocation\": \"5.1.0\",\n" +
                "  \"nativescript-image\": \"2.1.4\",\n" +
                "  \"nativescript-imagepicker\": \"6.2.0\",\n" +
                "  \"nativescript-intl\": \"3.0.0\",\n" +
                "  \"nativescript-iqkeyboardmanager\": \"1.5.1\",\n" +
                "  \"nativescript-social-share\": \"1.5.2\",\n" +
                "  \"nativescript-theme-core\": \"1.0.6\",\n" +
                "  \"nativescript-ui-autocomplete\": \"5.0.0\",\n" +
                "  \"nativescript-ui-calendar\": \"5.0.0\",\n" +
                "  \"nativescript-ui-chart\": \"5.0.0\",\n" +
                "  \"nativescript-ui-dataform\": \"5.0.0\",\n" +
                "  \"nativescript-ui-gauge\": \"5.0.0\",\n" +
                "  \"nativescript-ui-listview\": \"7.0.0\",\n" +
                "  \"nativescript-ui-sidedrawer\": \"7.0.0\",\n" +
                "  \"nativescript-vue\": \"2.3.0\",\n" +
                "  \"reflect-metadata\": \"0.1.13\",\n" +
                "  \"rxjs\": \"6.5.2\",\n" +
                "  \"rxjs-compat\": \"6.5.2\",\n" +
                "  \"tns-core-modules\": \"6.0.1\",\n" +
                "  \"zone.js\": \"0.9.1\"\n" +
                "}";

        codeEditor.assertDeviceTab(this.deviceName, modelExpected, osVersionExpected, previewAppVersionExpected, runtimeVersionExpected, componentVersionsExpected);
    }

    @Test(description = "Verify XML/HTML valid code change is apllied!", groups = {"android", "ios"})
    public void test_02_valid_code_change_to_xml_or_html() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        codeEditor.typeXMLOrHTMLCode(true, false);
        codeEditor.save("Test");
        this.assertScreen("nsplaydev-synced-valid-code", this.settings.defaultTimeout);
    }

    @Test(description = "Verify XML/HTML invalid code change is apllied and after fix is corrected!", groups = {"android", "ios"})
    public void test_03_invalid_code_change_to_xml_or_html() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        codeEditor.typeXMLOrHTMLCode(false, false);
        codeEditor.save();
        if (setupClass.waitUntilWebElementIsPresentByXpath("//button[contains(.,'Not now')]")) {
            setupClass.driver.findElements(By.xpath("//button[contains(.,'Not now')]")).get(0).click();
        }

        this.setupClass.wait(2000);
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.assertScreen("nsplaydev-synced-invalid-code-ng", this.settings.defaultTimeout);
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            this.assertScreen("nsplaydev-synced-valid-code", this.settings.defaultTimeout);
        } else {
            //remove after bug in {N}
            //this.assertScreen("nsplaydev-synced-invalid-code", this.settings.defaultTimeout);
            if (settings.deviceType == settings.deviceType.Simulator) {
                this.setupClass.wait(9000);
                this.context.client.driver.launchApp();
                this.setupClass.wait(10000);
            }
        }
        codeEditor.typeXMLOrHTMLCode(true, false);
        if (this.setupClass.typeOfProject.equals("vue")) {
            codeEditor.save();
        } else {
            codeEditor.save("Test");
        }
        this.assertScreen("nsplaydev-synced-valid-code", this.settings.defaultTimeout);
    }

    @Test(description = "Verify css valid code change is applied corrected!", groups = {"android", "ios"})
    public void test_04_valid_code_change_to_css() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if (this.context.lastTestResult != 1) {
            codeEditor.typeXMLOrHTMLCode(true, false);
        }
        codeEditor.openFile("app.css");
        codeEditor.typeCSSCode(true, false);
        codeEditor.save();
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.defaultTimeout);
    }

    @Test(description = "Verify css invalid code change is applied corrected!", groups = {"android", "ios"})
    public void test_05_invalid_code_change_to_css() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        codeEditor.typeCSSCode(false, false);
        String errorText = codeEditor.getErrorsTextFromErrorTab();
        String expectedText = "app.css (12, 1): [css] at-rule or selector expected";
        Assert.assertEquals(errorText, expectedText, "Expected text \"" + expectedText + "\" is not equal to \"" + errorText + "\" .");
        this.setupClass.wait(2000);
        codeEditor.save();
        this.setupClass.wait(2000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.defaultTimeout);
        this.setupClass.wait(2000);
        this.codeEditor.pressButton(KeyEvent.VK_ESCAPE);
        this.setupClass.wait(5000);
        codeEditor.typeCSSCode(true, false);
        codeEditor.save();
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.defaultTimeout);
    }

    @Test(description = "Verify js/ts valid code change is applied corrected!", groups = {"android", "ios"})
    public void test_06_valid_code_change_to_js_ts() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if (this.context.lastTestResult != 1) {
            this.codeEditor.pressButton(KeyEvent.VK_ESCAPE);
            this.setupClass.wait(2000);
            codeEditor.typeCSSCode(true, false);
        }
        codeEditor.clearDeviceLogs();
        if (this.setupClass.typeOfProject.equals("ng")) {
            codeEditor.openFile("home.component.ts");
        } else if (this.setupClass.typeOfProject.equals("js")) {
            codeEditor.openFile("home-page.js");
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            codeEditor.openFile("home-view-model.ts");
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            codeEditor.openFile("HelloWorld.vue");
        }
        codeEditor.typeJSTSCode(true, false);
        this.setupClass.getScreenShot(this.context.getTestName() + "_AfterEnterCode");
        this.setupClass.wait(1000);
        codeEditor.save();
        this.setupClass.wait(3000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.defaultTimeout);
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText;
        if (!this.setupClass.typeOfProject.equals("vue")) {
            expectedText = "[" + this.deviceName + "]" + ": log";

        } else {
            expectedText = "[" + this.deviceName + "]" + ": 'log'";
        }
        if (this.setupClass.typeOfProject.equals("vue")) {
            Assert.assertTrue(deviceLog.trim().contains(expectedText.trim()), "Expected text \"" + expectedText.trim() + "\" is not equal to \"" + deviceLog.trim() + "\" .");

        } else {
            Assert.assertEquals(expectedText.trim(), deviceLog.trim(), "Expected text \"" + expectedText.trim() + "\" is not equal to \"" + deviceLog.trim() + "\" .");
        }
    }

    @Test(description = "Verify js/ts invalid code change is applied corrected!", groups = {"android", "ios"})
    public void test_07_invalid_code_change_to_js_ts() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        codeEditor.typeJSTSCode(false, false);
        this.setupClass.getScreenShot(this.context.getTestName() + "_AfterEnterErrorCode");
        String expectedText = "";
        if (this.setupClass.typeOfProject.equals("vue")) {
            expectedText = "An error occurred while transpiling components/HelloWorld.vue.";
            codeEditor.save();
        }
        String errorText = codeEditor.getErrorsTextFromErrorTab();

        if (this.setupClass.typeOfProject.equals("ng")) {
            expectedText = "home/home.component.ts";
        } else if (this.setupClass.typeOfProject.equals("js")) {
            expectedText = "home/home-page.js";
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            expectedText = "home/home-view-model.ts";
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            expectedText = "app.js";
        }
        Assert.assertTrue(errorText.contains(expectedText), "Expected text \"" + expectedText + "\" does not contains \"" + errorText + "\" .");
        if (!this.setupClass.typeOfProject.equals("vue")) {
            this.setupClass.wait(2000);
            codeEditor.save();
            this.setupClass.wait(2000);
            Assert.assertTrue(setupClass.driver.findElements(By.xpath("//div[contains(.,'Unable to apply changes')]")).size() != 0);
            Assert.assertTrue(setupClass.driver.findElements(By.xpath("//div[contains(.,'Please fix the errors and try again.')]")).size() != 0);
            this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.defaultTimeout);
        }
        this.codeEditor.pressButton(KeyEvent.VK_ESCAPE);
        this.setupClass.wait(3000);
        codeEditor.typeJSTSCode(true, false);
        codeEditor.save();
        this.setupClass.wait(3000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.deviceBootTimeout);
    }

    @Test(description = "Verify javascript error is handle correctly!", groups = {"android", "ios"})
    public void test_08_javascript_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if (this.context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true, false);
        }
        codeEditor.clearDeviceLogs();
        this.setupClass.wait(1000);
        setupClass.driver.findElements(By.xpath("//span[contains(.,'Errors')]")).get(0).click();
        this.setupClass.wait(1000);
        if (this.setupClass.typeOfProject.equals("ng")) {
            codeEditor.openFile("home.component.ts");
        } else if (this.setupClass.typeOfProject.equals("js")) {
            codeEditor.openFile("home-page.js");
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            codeEditor.openFile("home-view-model.ts");
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            codeEditor.openFile("HelloWorld.vue");
        }

        this.setupClass.wait(2000);
        codeEditor.typeJSTSCodeWithThrowError();
        this.setupClass.getScreenShot(this.context.getTestName() + "_AfterEnterErrorCode");
        codeEditor.save();
        if (settings.deviceType == settings.deviceType.Simulator) {
            this.setupClass.wait(7000);
            this.context.client.driver.launchApp();
            this.setupClass.wait(4000);
        } else {
            this.setupClass.wait(4000);
        }
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.assertScreen("nsplaydev-synced-javascript-error-ng", this.settings.defaultTimeout, 21);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.assertScreen("nsplaydev-synced-javascript-error-js", this.settings.defaultTimeout, 21);
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.assertScreen("nsplaydev-synced-javascript-error-tsc", this.settings.defaultTimeout, 21);
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            this.assertScreen("nsplaydev-synced-javascript-error-vue", this.settings.defaultTimeout, 21);
        }
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText = null;
        if (this.setupClass.typeOfProject.equals("ng")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "ERROR: ERROR Error";
            } else {
                expectedText = "Error: Error";
            }
        } else if (this.setupClass.typeOfProject.equals("js")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "JS ERROR Error: Error";
            } else {
                expectedText = "Error: Error";
            }
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "nsplaydev";
            } else {
                expectedText = "Error: Error";
            }
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "JS ERROR Error: Error";
            } else {
                expectedText = "Error: Error";
            }
        }

        Assert.assertTrue(deviceLog.contains(expectedText), "Actual log \"" + deviceLog + "\" does not cointains the expected text \"" + expectedText + "\" .");

        codeEditor.typeJSTSCode(true, false);
        this.setupClass.wait(10000);
        codeEditor.save();
        this.setupClass.wait(4000);
        this.setupClass.getScreenShot(this.context.getTestName() + "_AfterEnterValidCode");
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.deviceBootTimeout);

    }

    @Test(description = "Verify java error is handle correctly!", groups = {"android", "ios"})
    public void test_09_java_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if (this.context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true, false);
            codeEditor.save();
            this.setupClass.wait(3000);
        }
        codeEditor.clearDeviceLogs();
        this.setupClass.wait(1000);
        setupClass.driver.findElements(By.xpath("//span[contains(.,'Errors')]")).get(0).click();
        this.setupClass.wait(1000);
        codeEditor.typeJSTSCodeWithThrowJavaError();
        this.setupClass.getScreenShot(this.context.getTestName() + "_AfterEnterErrorCode");
        codeEditor.save();
        if (settings.deviceType == settings.deviceType.Simulator) {
            this.setupClass.wait(7000);
            this.context.client.driver.launchApp();
            this.setupClass.wait(4000);
        } else {
            this.setupClass.wait(4000);
        }
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.assertScreen("nsplaydev-synced-java-error-ng", this.settings.defaultTimeout, 20);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.assertScreen("nsplaydev-synced-java-error-js", this.settings.defaultTimeout, 20);
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.assertScreen("nsplaydev-synced-java-error-tsc", this.settings.defaultTimeout, 20);
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            this.assertScreen("nsplaydev-synced-java-error-vue", this.settings.defaultTimeout, 20);
        }
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText = null;
        if (this.setupClass.typeOfProject.equals("ng")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "Can't find variable: java";
            } else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if (settings.deviceName.contains("Api23") || settings.deviceName.contains("Api22") || settings.deviceName.contains("Api21") || settings.deviceName.contains("Api19")) {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }
            }
        } else if (this.setupClass.typeOfProject.equals("js")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "Can't find variable: java";
            } else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if (settings.deviceName.contains("Api23") || settings.deviceName.contains("Api22") || settings.deviceName.contains("Api21") || settings.deviceName.contains("Api19")) {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }

            }
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "nsplaydev";
            } else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if (settings.deviceName.contains("Api23") || settings.deviceName.contains("Api22") || settings.deviceName.contains("Api21") || settings.deviceName.contains("Api19")) {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }

            }
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "Can't find variable: java";
            } else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if (settings.deviceName.contains("Api23") || settings.deviceName.contains("Api22") || settings.deviceName.contains("Api21") || settings.deviceName.contains("Api19")) {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }

            }
        }

        Assert.assertTrue(deviceLog.contains(expectedText), "Actual log \"" + deviceLog + "\" does not cointains the expected text \"" + expectedText + "\" .");

        codeEditor.typeJSTSCode(true, false);
        this.setupClass.wait(4000);
        codeEditor.save();
        this.setupClass.wait(4000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.deviceBootTimeout);
    }

    @Test(description = "Verify ios error is handle correctly!", groups = {"android", "ios"})
    public void test_10_ios_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if (this.context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true, false);
            codeEditor.save();
            this.setupClass.wait(3000);
        }
        codeEditor.clearDeviceLogs();
        setupClass.driver.findElements(By.xpath("//span[contains(.,'Errors')]")).get(0).click();
        codeEditor.typeJSTSCodeWithThrowiOSError();
        this.setupClass.wait(2000);
        this.setupClass.getScreenShot(this.context.getTestName() + "_AfterEnterErrorCode");
        codeEditor.save();
        if (settings.deviceType == settings.deviceType.Simulator) {
            this.setupClass.wait(7000);
            this.context.client.driver.launchApp();
            this.setupClass.wait(6000);
        } else {
            this.setupClass.wait(4000);
        }
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.assertScreen("nsplaydev-synced-ios-error-ng", this.settings.defaultTimeout, 21);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.assertScreen("nsplaydev-synced-ios-error-js", this.settings.defaultTimeout, 21);
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.assertScreen("nsplaydev-synced-ios-error-tsc", this.settings.defaultTimeout, 21);
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            this.assertScreen("nsplaydev-synced-ios-error-vue", this.settings.defaultTimeout, 21);
        }
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText = null;
        if (this.setupClass.typeOfProject.equals("ng")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "not-existing-path";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        } else if (this.setupClass.typeOfProject.equals("js")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "not-existing-path";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "nsplaydev";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "not-existing-path";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                expectedText = "not-existing-path";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        }
        Assert.assertTrue(deviceLog.contains(expectedText), "Actual log \"" + deviceLog + "\" does not cointains the expected text \"" + expectedText + "\" .");

        codeEditor.typeJSTSCode(true, false);
        this.setupClass.wait(10000);
        codeEditor.save();
        this.setupClass.wait(4000);
        this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.deviceBootTimeout);

    }

    @Test(description = "Verify ios cocoa error is handle correctly!", groups = {"android", "ios"})
    public void test_11_ios_cocoa_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        if (this.context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true, false);
            codeEditor.save();
            this.setupClass.wait(3000);
        }
        if (settings.deviceType == settings.deviceType.Simulator) {
            codeEditor.clearDeviceLogs();
            this.device.cleanConsoleLog();
            setupClass.driver.findElements(By.xpath("//span[contains(.,'Errors')]")).get(0).click();
            codeEditor.typeJSTSCodeWithThrowiOSCocoaError();
            codeEditor.save();
            if (settings.deviceType == settings.deviceType.Simulator) {
                this.setupClass.wait(10000);
                this.context.client.driver.launchApp();
                this.setupClass.wait(6000);
            } else {
                this.setupClass.wait(6000);
            }
            if (this.setupClass.typeOfProject.equals("ng")) {
                this.assertScreen("nsplaydev-synced-ios-cocoa-error-ng", this.settings.defaultTimeout, 20);
            } else if (this.setupClass.typeOfProject.equals("js")) {
                this.assertScreen("nsplaydev-synced-ios-cocoa-error-js", this.settings.defaultTimeout, 20);
            } else if (this.setupClass.typeOfProject.equals("tsc")) {
                this.assertScreen("nsplaydev-synced-ios-cocoa-error-tsc", this.settings.defaultTimeout, 20);
            } else if (this.setupClass.typeOfProject.equals("vue")) {
                this.assertScreen("nsplaydev-synced-ios-cocoa-error-vue", this.settings.defaultTimeout, 20);
            }
            String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
            this.setupClass.getScreenShot(this.context.getTestName() + "_AfterEnterErrorCode");
            String expectedText = null;
            if (this.setupClass.typeOfProject.equals("ng")) {
                if (settings.deviceType == settings.deviceType.Simulator) {
                    expectedText = "PlayLiveSync: Uncaught Exception";
                } else {
                    expectedText = "ReferenceError";
                }
            } else if (this.setupClass.typeOfProject.equals("js")) {
                if (settings.deviceType == settings.deviceType.Simulator) {
                    expectedText = "Uncaught Exception";
                } else {
                    expectedText = "ReferenceError";
                }
            } else if (this.setupClass.typeOfProject.equals("tsc")) {
                if (settings.deviceType == settings.deviceType.Simulator) {
                    expectedText = "nsplaydev";
                } else {
                    expectedText = "ReferenceError";
                }
            } else if (this.setupClass.typeOfProject.equals("vue")) {
                if (settings.deviceType == settings.deviceType.Simulator) {
                    expectedText = "Uncaught Exception";
                } else {
                    expectedText = "ReferenceError";
                }
            }

            Assert.assertTrue(deviceLog.contains(expectedText), "Actual log \"" + deviceLog + "\" does not cointains the expected text \"" + expectedText + "\" .");

            codeEditor.typeJSTSCode(true, false);
            this.setupClass.wait(10000);
            codeEditor.save();
            this.setupClass.wait(3000);
            this.assertScreen("nsplaydev-synced-valid-code-css", this.settings.deviceBootTimeout);
        }
    }

    @Test(description = "Verify saved session is loaded correctly!", groups = {"android", "ios"})
    public void test_12_open_saved_session() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(this.setupClass);
        this.setupClass.wait(6000);
        if (this.context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true, false);
            codeEditor.save();
            this.setupClass.wait(6000);
        }
        //shit appium ios 10 and 9
        if (settings.deviceType == settings.deviceType.Simulator) {
            this.setupClass.changeIosDriverToWebView();
            this.setupClass.wait(8000);
        } else {
            this.setupClass.restoreAndroidDriver();
            this.setupClass.wait(8000);
        }

        if (this.setupClass.isLive) {
            if (settings.deviceType == settings.deviceType.Simulator) {
                if (this.setupClass.typeOfProject.equals("ng")) {
                    this.client.driver.get("https://play.nativescript.org/?template=play-ng&id=6F74Ey");
                } else if (this.setupClass.typeOfProject.equals("js")) {
                    this.client.driver.get("https://play.nativescript.org/?template=play-js&id=eRObPw");
                } else if (this.setupClass.typeOfProject.equals("tsc")) {
                    this.client.driver.get("https://play.nativescript.org/?template=play-tsc&id=peX6hs");
                } else if (this.setupClass.typeOfProject.equals("vue")) {
                    this.client.driver.get("https://play.nativescript.org/?template=play-vue&id=fqRxUm");
                }
            }
            else {
                if (this.setupClass.typeOfProject.equals("ng")) {
                    this.setupClass.openURL("https://play.nativescript.org/?template=play-ng&id=6F74Ey");
                } else if (this.setupClass.typeOfProject.equals("js")) {
                    this.setupClass.openURL("https://play.nativescript.org/?template=play-js&id=eRObPw");
                } else if (this.setupClass.typeOfProject.equals("tsc")) {
                    this.setupClass.openURL("https://play.nativescript.org/?template=play-tsc&id=peX6hs");
                } else if (this.setupClass.typeOfProject.equals("vue")) {
                    this.setupClass.openURL("https://play.nativescript.org/?template=play-vue&id=fqRxUm");
                }
            }
        } else {
            if (settings.deviceType == settings.deviceType.Simulator) {
                if (this.setupClass.typeOfProject.equals("ng")) {
                    this.client.driver.get("https://play.nativescript.be/?template=play-ng&id=uolyao");
                } else if (this.setupClass.typeOfProject.equals("js")) {
                    this.client.driver.get("https://play.nativescript.be/?template=play-js&id=WFQqZ2");
                } else if (this.setupClass.typeOfProject.equals("tsc")) {
                    this.client.driver.get("https://play.nativescript.be/?template=play-tsc&id=Oh69ux");
                } else if (this.setupClass.typeOfProject.equals("vue")) {
                    this.client.driver.get("https://play.nativescript.be/?template=play-vue&id=vxaCzV");
                }
            }
            else{
                if (this.setupClass.typeOfProject.equals("ng")) {
                    this.setupClass.openURL("https://play.nativescript.be/?template=play-ng&id=uolyao");
                } else if (this.setupClass.typeOfProject.equals("js")) {
                    this.setupClass.openURL("https://play.nativescript.be/?template=play-js&id=WFQqZ2");
                } else if (this.setupClass.typeOfProject.equals("tsc")) {
                    this.setupClass.openURL("https://play.nativescript.be/?template=play-tsc&id=Oh69ux");
                } else if (this.setupClass.typeOfProject.equals("vue")) {
                    this.setupClass.openURL("https://play.nativescript.be/?template=play-vue&id=vxaCzV");
                }
            }
        }

        this.setupClass.wait(12000);
        this.setupClass.navigateToSavedSession("Tap to open the saved");
        this.setupClass.wait(10000);
        if (settings.deviceName.contains("Api19")) {
            this.assertScreen("nsplaydev-synced-saved-session-api19", this.settings.deviceBootTimeout, 5);
        } else {
            this.assertScreen("nsplaydev-synced-saved-session", this.settings.deviceBootTimeout, 5);
        }
        //shit appium ios 10 and 9
        if (settings.deviceType == settings.deviceType.Simulator) {
            this.setupClass.restoreIosDriver();
        }
    }
}

