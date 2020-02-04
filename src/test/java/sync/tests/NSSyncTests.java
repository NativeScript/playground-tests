package sync.tests;

import functional.tests.core.enums.DeviceType;
import functional.tests.core.mobile.basetest.MobileTest;
import functional.tests.core.mobile.element.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import sync.pages.CodeEditorClass;
import sync.pages.SetupClass;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class NSSyncTests extends MobileTest {
    SetupClass setupClass;
    public String deviceName;
    CodeEditorClass codeEditor;

    @BeforeClass
    public void beforeClass() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        setupClass = new SetupClass(client, settings, device);
        String projectURL = "https://play.nativescript.be/?template=play-" + setupClass.typeOfProject + "&debug=true";

        if (setupClass.isHMREnabled.contains("false")) {
            projectURL = projectURL + "&enableHMR=false";
        }

        if (projectURL.contains("play.nativescript.org")) {
            setupClass.isLive = true;
        }

        setupClass.getScreenShot("BeforeStartOfTests_BeforeNavigateToProject");
        setupClass.NavigateToPage(projectURL);
        setupClass.getScreenShot("BeforeStartOfTests_AfterNavigateToProject");
        setupClass.GetDeviceLink();
        setupClass.getScreenShot("BeforeStartOfTests_AfterGetDeviceLink");
        setupClass.startPreviewAppWithLiveSync();
        setupClass.getScreenShot("BeforeStartOfTests_AfterLiveSync");
        codeEditor = new CodeEditorClass(setupClass);
        setupClass.wait(5000);
        if (setupClass.driver.findElements(By.cssSelector("iframe[title='Intercom Live Chat']")).size() != 0) {
            WebElement frame = setupClass.driver.findElement(By.cssSelector("iframe[title='Intercom Live Chat']"));
            setupClass.driver.switchTo().frame(frame);
            if (setupClass.driver.findElements(By.xpath("//*[@class='intercom-note-close intercom-anchor']")).size() != 0) {
                setupClass.driver.findElements(By.xpath("//*[@class='intercom-note-close intercom-anchor']")).get(0).click();
            }
            setupClass.driver.switchTo().defaultContent();
            setupClass.wait(5000);
        }
    }

    @AfterClass
    public void afterClass() {
        setupClass.driver.quit();
    }

    @BeforeMethod
    public void beforeTest() {
        setupClass.getScreenShot(context.getTestName() + "_BeforeStart");
        context.shouldRestartAppOnFailure = false;
    }

    @AfterMethod
    public void afterTest() {
        if (context.lastTestResult != 1) {
            codeEditor.pressButton(KeyEvent.VK_ESCAPE);
            setupClass.getScreenShot(context.getTestName() + "_AfterStart_Fail");
        } else {
            setupClass.getScreenShot(context.getTestName() + "_AfterStart_Success");
        }
    }

    @Test(description = "Verify devices tab is showing valid data!", groups = {"android", "ios"})
    public void test_01_verify_devices_tab() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);

        if (settings.deviceType == DeviceType.Emulator) {
            deviceName = "Android SDK built for x86";

        } else {
            deviceName = setupClass.getComputerName();
            if (settings.platformVersion.toString().contains("9.")) {
                deviceName = "iPhone Simulator";

            }
            if (settings.platformVersion.toString().contains("12.")) {
                deviceName = "iPhone 7 12";

            }
            if (settings.platformVersion.toString().contains("13.")) {
                deviceName = "iPhone XR 13";

            }

        }
        String modelExpected;
        if (settings.deviceType == DeviceType.Emulator) {
            modelExpected = "Android SDK built for x86";

        } else {
            modelExpected = "Simulator";
        }
        String osVersionExpected = "";
        if (settings.deviceType == DeviceType.Emulator) {
            osVersionExpected = "Android ";
            osVersionExpected = osVersionExpected + String.valueOf(context.client.driver.getCapabilities().getCapability("platformVersion"));
        } else {
            osVersionExpected = "iOS ";
            osVersionExpected = osVersionExpected + setupClass.getIOSVersion();
        }

        String previewAppVersionExpected = "";
        if (settings.deviceType == DeviceType.Emulator) {

            previewAppVersionExpected = "1.29.0";
        } else {
            previewAppVersionExpected = "1.29.0";
        }

        String runtimeVersionExpected = "";
        if (settings.deviceType == DeviceType.Emulator) {

            runtimeVersionExpected = "6.4.0";
        } else {
            runtimeVersionExpected = "6.4.0";
        }

        String componentVersionsExpected = "{\n" +
                "  \"@angular/animations\": \"8.2.14\",\n" +
                "  \"@angular/common\": \"8.2.14\",\n" +
                "  \"@angular/compiler\": \"8.2.14\",\n" +
                "  \"@angular/core\": \"8.2.14\",\n" +
                "  \"@angular/forms\": \"8.2.14\",\n" +
                "  \"@angular/platform-browser\": \"8.2.14\",\n" +
                "  \"@angular/platform-browser-dynamic\": \"8.2.14\",\n" +
                "  \"@angular/router\": \"8.2.14\",\n" +
                "  \"@nativescript/angular\": \"8.20.4\",\n" +
                "  \"@nativescript/core\": \"6.4.0\",\n" +
                "  \"@nativescript/theme\": \"2.2.1\",\n" +
                "  \"@progress-nativechat/nativescript-nativechat\": \"3.0.0\",\n" +
                "  \"kinvey-nativescript-sdk\": \"4.2.5\",\n" +
                "  \"nativescript-accelerometer\": \"3.0.0\",\n" +
                "  \"nativescript-angular\": \"8.20.4\",\n" +
                "  \"nativescript-background-http\": \"4.2.1\",\n" +
                "  \"nativescript-camera\": \"4.5.0\",\n" +
                "  \"nativescript-geolocation\": \"5.1.0\",\n" +
                "  \"nativescript-image\": \"3.0.1\",\n" +
                "  \"nativescript-imagepicker\": \"7.1.0\",\n" +
                "  \"nativescript-intl\": \"3.0.0\",\n" +
                "  \"nativescript-iqkeyboardmanager\": \"1.5.1\",\n" +
                "  \"nativescript-social-share\": \"1.6.0\",\n" +
                "  \"nativescript-theme-core\": \"1.0.6\",\n" +
                "  \"nativescript-ui-autocomplete\": \"6.0.0\",\n" +
                "  \"nativescript-ui-calendar\": \"6.0.0\",\n" +
                "  \"nativescript-ui-chart\": \"7.1.1\",\n" +
                "  \"nativescript-ui-dataform\": \"6.0.0\",\n" +
                "  \"nativescript-ui-gauge\": \"6.0.0\",\n" +
                "  \"nativescript-ui-listview\": \"8.0.1\",\n" +
                "  \"nativescript-ui-sidedrawer\": \"8.0.0\",\n" +
                "  \"nativescript-vue\": \"2.4.0\",\n" +
                "  \"react\": \"16.12.0\",\n" +
                "  \"react-hot-loader\": \"4.12.19\",\n" +
                "  \"react-nativescript\": \"0.17.0\",\n" +
                "  \"reflect-metadata\": \"0.1.13\",\n" +
                "  \"rxjs\": \"6.5.4\",\n" +
                "  \"rxjs-compat\": \"6.5.4\",\n" +
                "  \"svelte\": \"3.18.1\",\n" +
                "  \"svelte-native\": \"0.7.3\",\n" +
                "  \"tns-core-modules\": \"6.4.0\",\n" +
                "  \"zone.js\": \"0.10.2\"\n" +
                "}";

        codeEditor.assertDeviceTab(deviceName, modelExpected, osVersionExpected, previewAppVersionExpected, runtimeVersionExpected, componentVersionsExpected);
    }

    @Test(description = "Verify XML/HTML valid code change is apllied!", groups = {"android", "ios"})
    public void test_02_valid_code_change_to_xml_or_html() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        codeEditor.typeXMLOrHTMLCode(true);
        codeEditor.save("Test");
        assertScreen("nsplaydev-synced-valid-code", settings.defaultTimeout);
    }

    @Test(description = "Verify XML/HTML invalid code change is apllied and after fix is corrected!", groups = {"android", "ios"})
    public void test_03_invalid_code_change_to_xml_or_html() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        codeEditor.typeXMLOrHTMLCode(false);
        codeEditor.save();
        if (setupClass.waitUntilWebElementIsPresentByXpath("//button[contains(.,'Not now')]")) {
            setupClass.driver.findElements(By.xpath("//button[contains(.,'Not now')]")).get(0).click();
        }

        setupClass.wait(2000);
        if (setupClass.typeOfProject.equals("ng")) {
            assertScreen("nsplaydev-synced-invalid-code-ng", settings.defaultTimeout);
        } else if (setupClass.typeOfProject.equals("vue")) {
            assertScreen("nsplaydev-synced-valid-code", settings.defaultTimeout);
        } else {
            //remove after bug in {N}
            //assertScreen("nsplaydev-synced-invalid-code", settings.defaultTimeout);
            if (settings.deviceType == DeviceType.Simulator) {
                setupClass.wait(9000);
                context.client.driver.launchApp();
                setupClass.wait(10000);
            }
        }
        codeEditor.typeXMLOrHTMLCode(true);
        setupClass.wait(5000);
        if (setupClass.typeOfProject.equals("vue")) {
            codeEditor.save();
            setupClass.wait(2000);
        } else {
            codeEditor.save("Test");
        }
        assertScreen("nsplaydev-synced-valid-code", settings.defaultTimeout);
    }

    @Test(description = "Verify css valid code change is applied corrected!", groups = {"android", "ios"})
    public void test_04_valid_code_change_to_css() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        if (context.lastTestResult != 1) {
            codeEditor.typeXMLOrHTMLCode(true);
        }
        codeEditor.openFile("app.css");
        codeEditor.typeCSSCode(true);
        codeEditor.save();
        assertScreen("nsplaydev-synced-valid-code-css", settings.defaultTimeout);
    }

    @Test(description = "Verify css invalid code change is applied corrected!", groups = {"android", "ios"})
    public void test_05_invalid_code_change_to_css() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        codeEditor.typeCSSCode(false);
        String errorText = codeEditor.getErrorsTextFromErrorTab();
        String expectedText = "app.css (13, 2): [css] at-rule or selector expected";
        Assert.assertEquals(errorText, expectedText, "Expected text \"" + expectedText + "\" is not equal to \"" + errorText + "\" .");
        setupClass.wait(2000);
        codeEditor.save();
        setupClass.wait(2000);
        assertScreen("nsplaydev-synced-valid-code-css", settings.defaultTimeout);
        setupClass.wait(2000);
        codeEditor.pressButton(KeyEvent.VK_ESCAPE);
        setupClass.wait(5000);
        codeEditor.typeCSSCode(true);
        codeEditor.save();
        assertScreen("nsplaydev-synced-valid-code-css", settings.defaultTimeout);
    }

    @Test(description = "Verify js/ts valid code change is applied corrected!", groups = {"android", "ios"})
    public void test_06_valid_code_change_to_js_ts() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        if (context.lastTestResult != 1) {
            codeEditor.pressButton(KeyEvent.VK_ESCAPE);
            setupClass.wait(2000);
            codeEditor.typeCSSCode(true);
        }
        codeEditor.clearDeviceLogs();
        if (setupClass.typeOfProject.equals("ng")) {
            codeEditor.openFile("home.component.ts");
        } else if (setupClass.typeOfProject.equals("js")) {
            codeEditor.openFile("home-page.js");
        } else if (setupClass.typeOfProject.equals("tsc")) {
            codeEditor.openFile("home-view-model.ts");
        } else if (setupClass.typeOfProject.equals("vue")) {
            codeEditor.openFile("HelloWorld.vue");
        }
        codeEditor.typeJSTSCode(true);
        setupClass.getScreenShot(context.getTestName() + "_AfterEnterCode");
        setupClass.wait(1000);
        codeEditor.save();
        setupClass.wait(3000);
        assertScreen("nsplaydev-synced-valid-code-css", settings.defaultTimeout);
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText;
        if (!setupClass.typeOfProject.equals("vue")) {
            expectedText = "[" + deviceName + "]" + ": log";

        } else {
            expectedText = "[" + deviceName + "]" + ": 'log'";
        }
        if (setupClass.typeOfProject.equals("vue")) {
            Assert.assertTrue(deviceLog.trim().contains(expectedText.trim()), "Expected text \"" + expectedText.trim() + "\" is not equal to \"" + deviceLog.trim() + "\" .");

        } else {
            Assert.assertEquals(expectedText.trim(), deviceLog.trim(), "Expected text \"" + expectedText.trim() + "\" is not equal to \"" + deviceLog.trim() + "\" .");
        }
    }

    @Test(description = "Verify js/ts invalid code change is applied corrected!", groups = {"android", "ios"})
    public void test_07_invalid_code_change_to_js_ts() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        codeEditor.typeJSTSCode(false);
        setupClass.getScreenShot(context.getTestName() + "_AfterEnterErrorCode");
        String expectedText = "";
        if (setupClass.typeOfProject.equals("vue")) {
            expectedText = "An error occurred while transpiling components/HelloWorld.vue.";
            codeEditor.save();
        }
        String errorText = codeEditor.getErrorsTextFromErrorTab();

        if (setupClass.typeOfProject.equals("ng")) {
            expectedText = "home/home.component.ts";
        } else if (setupClass.typeOfProject.equals("js")) {
            expectedText = "home/home-page.js";
        } else if (setupClass.typeOfProject.equals("tsc")) {
            expectedText = "home/home-view-model.ts";
        } else if (setupClass.typeOfProject.equals("tsc")) {
            expectedText = "app.js";
        }
        Assert.assertTrue(errorText.contains(expectedText), "Expected text \"" + expectedText + "\" does not contains \"" + errorText + "\" .");
        if (!setupClass.typeOfProject.equals("vue")) {
            setupClass.wait(2000);
            codeEditor.save();
            setupClass.wait(2000);
            Assert.assertTrue(setupClass.driver.findElements(By.xpath("//div[contains(.,'Unable to apply changes')]")).size() != 0);
            Assert.assertTrue(setupClass.driver.findElements(By.xpath("//div[contains(.,'Please fix the errors and try again.')]")).size() != 0);
            assertScreen("nsplaydev-synced-valid-code-css", settings.defaultTimeout);
        }
        codeEditor.pressButton(KeyEvent.VK_ESCAPE);
        setupClass.wait(3000);
        codeEditor.typeJSTSCode(true);
        codeEditor.save();
        setupClass.wait(3000);
        assertScreen("nsplaydev-synced-valid-code-css", settings.deviceBootTimeout);
    }

    @Test(description = "Verify javascript error is handle correctly!", groups = {"android", "ios"})
    public void test_08_javascript_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        if (context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true);
        }
        codeEditor.clearDeviceLogs();
        setupClass.wait(1000);
        setupClass.driver.findElements(By.xpath("//span[contains(.,'Errors')]")).get(0).click();
        setupClass.wait(1000);
        if (setupClass.typeOfProject.equals("ng")) {
            codeEditor.openFile("home.component.ts");
        } else if (setupClass.typeOfProject.equals("js")) {
            codeEditor.openFile("home-page.js");
        } else if (setupClass.typeOfProject.equals("tsc")) {
            codeEditor.openFile("home-view-model.ts");
        } else if (setupClass.typeOfProject.equals("vue")) {
            codeEditor.openFile("HelloWorld.vue");
        }

        setupClass.wait(2000);
        codeEditor.typeJSTSCodeWithThrowError();
        setupClass.getScreenShot(context.getTestName() + "_AfterEnterErrorCode");
        codeEditor.save();
        if (settings.deviceType == DeviceType.Simulator) {
            setupClass.wait(7000);
            context.client.driver.launchApp();
            setupClass.wait(4000);
        } else {
            setupClass.wait(4000);
        }
        if (setupClass.typeOfProject.equals("ng")) {
            assertScreen("nsplaydev-synced-javascript-error-ng", settings.defaultTimeout, 21);
        } else if (setupClass.typeOfProject.equals("js")) {
            assertScreen("nsplaydev-synced-javascript-error-js", settings.defaultTimeout, 21);
        } else if (setupClass.typeOfProject.equals("tsc")) {
            assertScreen("nsplaydev-synced-javascript-error-tsc", settings.defaultTimeout, 21);
        } else if (setupClass.typeOfProject.equals("vue")) {
            assertScreen("nsplaydev-synced-javascript-error-vue", settings.defaultTimeout, 21);
        }
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText = null;
        if (setupClass.typeOfProject.equals("ng")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "ERROR: ERROR Error";
            } else {
                expectedText = "Error: Error";
            }
        } else if (setupClass.typeOfProject.equals("js")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "JS ERROR Error: Error";
            } else {
                expectedText = "Error: Error";
            }
        } else if (setupClass.typeOfProject.equals("tsc")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "nsplaydev";
            } else {
                expectedText = "Error: Error";
            }
        } else if (setupClass.typeOfProject.equals("vue")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "JS ERROR Error: Error";
            } else {
                expectedText = "Error: Error";
            }
        }

        Assert.assertTrue(deviceLog.contains(expectedText), "Actual log \"" + deviceLog + "\" does not cointains the expected text \"" + expectedText + "\" .");

        codeEditor.typeJSTSCode(true);
        setupClass.wait(10000);
        codeEditor.save();
        setupClass.wait(4000);
        setupClass.getScreenShot(context.getTestName() + "_AfterEnterValidCode");
        assertScreen("nsplaydev-synced-valid-code-css", settings.deviceBootTimeout);

    }

    @Test(description = "Verify java error is handle correctly!", groups = {"android", "ios"})
    public void test_09_java_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        if (context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true);
            codeEditor.save();
            setupClass.wait(3000);
        }
        codeEditor.clearDeviceLogs();
        setupClass.wait(1000);
        setupClass.driver.findElements(By.xpath("//span[contains(.,'Errors')]")).get(0).click();
        setupClass.wait(1000);
        codeEditor.typeJSTSCodeWithThrowJavaError();
        setupClass.getScreenShot(context.getTestName() + "_AfterEnterErrorCode");
        codeEditor.save();
        if (settings.deviceType == DeviceType.Simulator) {
            setupClass.wait(7000);
            context.client.driver.launchApp();
            setupClass.wait(4000);
        } else {
            setupClass.wait(4000);
        }
        if (setupClass.typeOfProject.equals("ng")) {
            assertScreen("nsplaydev-synced-java-error-ng", settings.defaultTimeout, 20);
        } else if (setupClass.typeOfProject.equals("js")) {
            assertScreen("nsplaydev-synced-java-error-js", settings.defaultTimeout, 20);
        } else if (setupClass.typeOfProject.equals("tsc")) {
            assertScreen("nsplaydev-synced-java-error-tsc", settings.defaultTimeout, 20);
        } else if (setupClass.typeOfProject.equals("vue")) {
            assertScreen("nsplaydev-synced-java-error-vue", settings.defaultTimeout, 20);
        }
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText = null;
        if (setupClass.typeOfProject.equals("ng")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "Can't find variable: java";
            } else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if (settings.deviceName.contains("Api23") || settings.deviceName.contains("Api22") || settings.deviceName.contains("Api21") || settings.deviceName.contains("Api19")) {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }
            }
        } else if (setupClass.typeOfProject.equals("js")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "Can't find variable: java";
            } else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if (settings.deviceName.contains("Api23") || settings.deviceName.contains("Api22") || settings.deviceName.contains("Api21") || settings.deviceName.contains("Api19")) {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }

            }
        } else if (setupClass.typeOfProject.equals("tsc")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "nsplaydev";
            } else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if (settings.deviceName.contains("Api23") || settings.deviceName.contains("Api22") || settings.deviceName.contains("Api21") || settings.deviceName.contains("Api19")) {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }

            }
        } else if (setupClass.typeOfProject.equals("vue")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "Can't find variable: java";
            } else {
                expectedText = "Error: java.lang.NumberFormatException: For input string: \"sdklfjsd\"";
                if (settings.deviceName.contains("Api23") || settings.deviceName.contains("Api22") || settings.deviceName.contains("Api21") || settings.deviceName.contains("Api19")) {
                    expectedText = "Error: java.lang.NumberFormatException: Invalid int: \"sdklfjsd\"";
                }

            }
        }

        Assert.assertTrue(deviceLog.contains(expectedText), "Actual log \"" + deviceLog + "\" does not cointains the expected text \"" + expectedText + "\" .");

        codeEditor.typeJSTSCode(true);
        setupClass.wait(4000);
        codeEditor.save();
        setupClass.wait(4000);
        assertScreen("nsplaydev-synced-valid-code-css", settings.deviceBootTimeout);
    }

    @Test(description = "Verify ios error is handle correctly!", groups = {"android", "ios"})
    public void test_10_ios_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        if (context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true);
            codeEditor.save();
            setupClass.wait(3000);
        }
        codeEditor.clearDeviceLogs();
        setupClass.driver.findElements(By.xpath("//span[contains(.,'Errors')]")).get(0).click();
        codeEditor.typeJSTSCodeWithThrowiOSError();
        setupClass.wait(2000);
        setupClass.getScreenShot(context.getTestName() + "_AfterEnterErrorCode");
        codeEditor.save();
        if (settings.deviceType == DeviceType.Simulator) {
            setupClass.wait(7000);
            context.client.driver.launchApp();
            setupClass.wait(6000);
        } else {
            setupClass.wait(4000);
        }
        if (setupClass.typeOfProject.equals("ng")) {
            assertScreen("nsplaydev-synced-ios-error-ng", settings.defaultTimeout, 21);
        } else if (setupClass.typeOfProject.equals("js")) {
            assertScreen("nsplaydev-synced-ios-error-js", settings.defaultTimeout, 21);
        } else if (setupClass.typeOfProject.equals("tsc")) {
            assertScreen("nsplaydev-synced-ios-error-tsc", settings.defaultTimeout, 21);
        } else if (setupClass.typeOfProject.equals("vue")) {
            assertScreen("nsplaydev-synced-ios-error-vue", settings.defaultTimeout, 21);
        }
        String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
        String expectedText = null;
        if (setupClass.typeOfProject.equals("ng")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "not-existing-path";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        } else if (setupClass.typeOfProject.equals("js")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "not-existing-path";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        } else if (setupClass.typeOfProject.equals("tsc")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "nsplaydev";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        } else if (setupClass.typeOfProject.equals("tsc")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "not-existing-path";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        } else if (setupClass.typeOfProject.equals("vue")) {
            if (settings.deviceType == DeviceType.Simulator) {
                expectedText = "not-existing-path";
            } else {
                expectedText = "ReferenceError: NSFileManager is not defined";
            }
        }
        Assert.assertTrue(deviceLog.contains(expectedText), "Actual log \"" + deviceLog + "\" does not cointains the expected text \"" + expectedText + "\" .");

        codeEditor.typeJSTSCode(true);
        setupClass.wait(10000);
        codeEditor.save();
        setupClass.wait(4000);
        assertScreen("nsplaydev-synced-valid-code-css", settings.deviceBootTimeout);

    }

    @Test(description = "Verify ios cocoa error is handle correctly!", groups = {"android", "ios"})
    public void test_11_ios_cocoa_error() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        if (context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true);
            codeEditor.save();
            setupClass.wait(3000);
        }
        if (settings.deviceType == DeviceType.Simulator) {
            codeEditor.clearDeviceLogs();
            device.cleanConsoleLog();
            setupClass.driver.findElements(By.xpath("//span[contains(.,'Errors')]")).get(0).click();
            codeEditor.typeJSTSCodeWithThrowiOSCocoaError();
            codeEditor.save();
            if (settings.deviceType == DeviceType.Simulator) {
                setupClass.wait(10000);
                context.client.driver.launchApp();
                setupClass.wait(6000);
            } else {
                setupClass.wait(6000);
            }
            if (setupClass.typeOfProject.equals("ng")) {
                assertScreen("nsplaydev-synced-ios-cocoa-error-ng", settings.defaultTimeout, 20);
            } else if (setupClass.typeOfProject.equals("js")) {
                assertScreen("nsplaydev-synced-ios-cocoa-error-js", settings.defaultTimeout, 20);
            } else if (setupClass.typeOfProject.equals("tsc")) {
                assertScreen("nsplaydev-synced-ios-cocoa-error-tsc", settings.defaultTimeout, 20);
            } else if (setupClass.typeOfProject.equals("vue")) {
                assertScreen("nsplaydev-synced-ios-cocoa-error-vue", settings.defaultTimeout, 20);
            }
            String deviceLog = codeEditor.getLogsTextFromDeviceLogsTab();
            setupClass.getScreenShot(context.getTestName() + "_AfterEnterErrorCode");
            String expectedText = null;
            if (setupClass.typeOfProject.equals("ng")) {
                if (settings.deviceType == DeviceType.Simulator) {
                    expectedText = "PlayLiveSync: Uncaught Exception";
                } else {
                    expectedText = "ReferenceError";
                }
            } else if (setupClass.typeOfProject.equals("js")) {
                if (settings.deviceType == DeviceType.Simulator) {
                    expectedText = "Uncaught Exception";
                } else {
                    expectedText = "ReferenceError";
                }
            } else if (setupClass.typeOfProject.equals("tsc")) {
                if (settings.deviceType == DeviceType.Simulator) {
                    expectedText = "nsplaydev";
                } else {
                    expectedText = "ReferenceError";
                }
            } else if (setupClass.typeOfProject.equals("vue")) {
                if (settings.deviceType == DeviceType.Simulator) {
                    expectedText = "Uncaught Exception";
                } else {
                    expectedText = "ReferenceError";
                }
            }

            Assert.assertTrue(deviceLog.contains(expectedText), "Actual log \"" + deviceLog + "\" does not cointains the expected text \"" + expectedText + "\" .");

            codeEditor.typeJSTSCode(true);
            setupClass.wait(10000);
            codeEditor.save();
            setupClass.wait(3000);
            assertScreen("nsplaydev-synced-valid-code-css", settings.deviceBootTimeout);
        }
    }

    @Test(description = "Verify empty folders are not crashing preview app!", groups = {"android", "ios"})
    public void test_12_empty_folders_are_crashing_preview_app() throws Exception {
        setupClass.driver.get("https://www.google.com/");
        try {
                setupClass.driver.switchTo().alert().accept();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        setupClass.NavigateToPage("https://play.nativescript.be/?template=play-js&id=2FtnMV&v=4&debug=true");
        setupClass.wait(2000);
        List<WebElement> modalDialogElements = setupClass.driver.findElements(By.className("modal-content"));
        if (modalDialogElements.size() > 0) {
            modalDialogElements.get(0).findElements(By.className("close-button")).get(0).click();
            setupClass.wait(2000);
        }
        setupClass.GetDeviceLink();
        setupClass.startPreviewAppWithLiveSync();
        setupClass.wait(10000);
        setupClass.NavigateToPage("https://play.nativescript.be/?template=play-js&id=2FtnMV&v=3&debug=true");
        setupClass.wait(2000);
        modalDialogElements = setupClass.driver.findElements(By.className("modal-content"));
        if (modalDialogElements.size() > 0) {
            modalDialogElements.get(0).findElements(By.className("close-button")).get(0).click();
            setupClass.wait(2000);
        }
        setupClass.GetDeviceLink();
        setupClass.startPreviewAppWithLiveSync();
        UIElement title = find.byText("Home");
        Assert.assertNotNull(title, "Project with empty folder is not working");
    }

    @Test(description = "Verify saved session is loaded correctly!", groups = {"android", "ios"})
    public void test_13_open_saved_session() throws Exception {
        CodeEditorClass codeEditor = new CodeEditorClass(setupClass);
        setupClass.wait(6000);
        if (context.lastTestResult != 1) {
            codeEditor.typeJSTSCode(true);
            codeEditor.save();
            setupClass.wait(6000);
        }
        //shit appium ios 10 and 9
        if (settings.deviceType == DeviceType.Simulator) {
            setupClass.changeIosDriverToWebView();
            setupClass.wait(8000);
        } else {
            setupClass.restoreAndroidDriver();
            setupClass.wait(8000);
        }

        if (setupClass.isLive) {
            if (settings.deviceType == DeviceType.Simulator) {
                if (setupClass.typeOfProject.equals("ng")) {
                    client.driver.get("https://play.nativescript.org/?template=play-ng&id=6F74Ey");
                } else if (setupClass.typeOfProject.equals("js")) {
                    client.driver.get("https://play.nativescript.org/?template=play-js&id=eRObPw");
                } else if (setupClass.typeOfProject.equals("tsc")) {
                    client.driver.get("https://play.nativescript.org/?template=play-tsc&id=peX6hs");
                } else if (setupClass.typeOfProject.equals("vue")) {
                    client.driver.get("https://play.nativescript.org/?template=play-vue&id=fqRxUm");
                }
            } else {
                if (setupClass.typeOfProject.equals("ng")) {
                    setupClass.openURL("https://play.nativescript.org/?template=play-ng&id=6F74Ey");
                } else if (setupClass.typeOfProject.equals("js")) {
                    setupClass.openURL("https://play.nativescript.org/?template=play-js&id=eRObPw");
                } else if (setupClass.typeOfProject.equals("tsc")) {
                    setupClass.openURL("https://play.nativescript.org/?template=play-tsc&id=peX6hs");
                } else if (setupClass.typeOfProject.equals("vue")) {
                    setupClass.openURL("https://play.nativescript.org/?template=play-vue&id=fqRxUm");
                }
            }
        } else {
            if (settings.deviceType == DeviceType.Simulator) {
                if (setupClass.typeOfProject.equals("ng")) {
                    client.driver.get("https://play.nativescript.be/?template=play-ng&id=uolyao");
                } else if (setupClass.typeOfProject.equals("js")) {
                    client.driver.get("https://play.nativescript.be/?template=play-js&id=WFQqZ2");
                } else if (setupClass.typeOfProject.equals("tsc")) {
                    client.driver.get("https://play.nativescript.be/?template=play-tsc&id=Oh69ux");
                } else if (setupClass.typeOfProject.equals("vue")) {
                    client.driver.get("https://play.nativescript.be/?template=play-vue&id=vxaCzV&v=2");
                }
            } else {
                if (setupClass.typeOfProject.equals("ng")) {
                    setupClass.openURL("https://play.nativescript.be/?template=play-ng&id=uolyao");
                } else if (setupClass.typeOfProject.equals("js")) {
                    setupClass.openURL("https://play.nativescript.be/?template=play-js&id=WFQqZ2");
                } else if (setupClass.typeOfProject.equals("tsc")) {
                    setupClass.openURL("https://play.nativescript.be/?template=play-tsc&id=Oh69ux");
                } else if (setupClass.typeOfProject.equals("vue")) {
                    setupClass.openURL("https://play.nativescript.be/?template=play-vue&id=vxaCzV&v=2");
                }
            }
        }

        setupClass.wait(12000);
        setupClass.navigateToSavedSession("Tap to open the saved");
        setupClass.wait(10000);
        if (settings.deviceName.contains("Api19")) {
            assertScreen("nsplaydev-synced-saved-session-api19", settings.deviceBootTimeout, 5);
        } else {
            assertScreen("nsplaydev-synced-saved-session", settings.deviceBootTimeout, 5);
        }
        //shit appium ios 10 and 9
        if (settings.deviceType == DeviceType.Simulator) {
            setupClass.restoreIosDriver();
        }
    }
}

