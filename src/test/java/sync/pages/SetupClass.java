package sync.pages;

import functional.tests.core.enums.DeviceType;
import functional.tests.core.image.ImageUtils;
import functional.tests.core.mobile.appium.Capabilities;
import functional.tests.core.mobile.appium.Client;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.device.Device;
import functional.tests.core.mobile.device.android.Adb;
import functional.tests.core.mobile.element.UIElement;
import functional.tests.core.mobile.element.UIRectangle;
import functional.tests.core.mobile.settings.MobileSettings;
import functional.tests.core.utils.OSUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utils.Sikuli;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class SetupClass extends BasePage {
    public Robot s = new Robot();
    public String liveSyncConnectionString;
    public String deviceId = "";
    public String ImagePathDirectory = "";
    public ImageUtils imageUtils;
    public Device device;
    public int deviceScreenWidth;
    public String appName;
    public Client client;
    public String typeOfProject = OSUtils.getEnvironmentVariable("typeOfProject", "ng");
    public String browser = OSUtils.getEnvironmentVariable("browser", "Google Chrome");
    public String isHMREnabled = OSUtils.getEnvironmentVariable("isHMREnabled", "false");
    public String folderForScreenshots;
    public String folderForDesktopScreenshots;
    public Robot robot = null;
    public MobileSettings mobileSettings;
    public WebDriver driver;
    public Sikuli sikuli;

    public boolean isLive = false;

    public SetupClass(Client client, MobileSettings mobileSettings, Device device) throws InterruptedException, IOException, AWTException {
        super();
        mobileSettings = mobileSettings;
        client = client;
        device = device;
        sikuli = new Sikuli(device, mobileSettings.testAppName + "-map", client, imageUtils);
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        imageUtils = new ImageUtils(settings, client, device);
        appName = app.getName().replaceAll(".app", "");
        String currentPath = System.getProperty("user.dir");
        ImagePathDirectory = currentPath + "/src/test/java/sync/pages/images.sikuli";
        folderForScreenshots = currentPath + "/target/surefire-reports/screenshots/";
        folderForDesktopScreenshots = currentPath + "/temp/";
        File directory = new File(folderForDesktopScreenshots);
        if (!directory.exists()) {
            directory.mkdir();
        } else {
            directory.delete();
            directory.mkdir();
        }

        if (settings.deviceType == DeviceType.Simulator) {
            client.driver.removeApp("org.nativescript.preview");
            functional.tests.core.utils.Archive.extractArchive(new File(currentPath + "/testapp/nsplaydev.tgz"), new File(currentPath + "/testapp/"));

            functional.tests.core.mobile.device.ios.IOSDevice ios = new functional.tests.core.mobile.device.ios.IOSDevice(client, mobileSettings);
            ios.installApp("nsplaydev.app", "org.nativescript.preview");
            deviceId = ios.getId();
            context.settings.packageId = "org.nativescript.preview";
            context.settings.testAppFileName = "nsplaydev.app";
            Capabilities newiOSCapabilities = new Capabilities();
            DesiredCapabilities newDesireCapabilites = new DesiredCapabilities();
            newDesireCapabilites = newiOSCapabilities.loadDesiredCapabilities(context.settings);
            newDesireCapabilites.setCapability("newCommandTimeout", 6000);
            context.client.driver = new IOSDriver(context.server.service.getUrl(), newDesireCapabilites);
        } else {
            functional.tests.core.mobile.device.android.AndroidDevice android = new functional.tests.core.mobile.device.android.AndroidDevice(client, mobileSettings);
            new Adb(mobileSettings).uninstallApp("org.nativescript.preview");
            android.installApp("app-universal-release.apk", "org.nativescript.preview");
            deviceId = android.getId();
            context.settings.packageId = "org.nativescript.preview";
            context.settings.testAppFileName = "app-universal-release.apk";
            Capabilities newAndroidCapabilities = new Capabilities();
            DesiredCapabilities newDesireCapabilites = new DesiredCapabilities();
            newDesireCapabilites = newAndroidCapabilities.loadDesiredCapabilities(context.settings);
            newDesireCapabilites.setCapability("newCommandTimeout", 6000);
            context.client.driver = new AndroidDriver(context.server.service.getUrl(), newDesireCapabilites);
        }

        //CloseBrowser();
        OpenBrowser();
    }

    public void OpenBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        Thread.sleep(10000);
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        options.addArguments("disable-application-cache");
        options.addArguments("incognito");
        options.addArguments("no-sandbox");
        driver = new ChromeDriver(options);
        Thread.sleep(10000);

    }

    public void NavigateToPage(String URL) throws InterruptedException {
        driver.get(URL);
        Thread.sleep(5000);
        List<WebElement> acceptCockiesButton = driver.findElements(By.xpath("//button[contains(.,'Accept Cookies')]"));
        if (acceptCockiesButton.size() != 0) {
            acceptCockiesButton.get(0).click();
        }
    }

    public void GetDeviceLink() throws InterruptedException, IOException, UnsupportedFlavorException {
        List<WebElement> previewLinkElements = driver.findElements(By.xpath("//span[contains(.,'nsplay://boot')]"));
        if (previewLinkElements.size() == 0) {
            driver.findElements(By.xpath("//button[contains(.,'QR code')]")).get(0).click();
            Thread.sleep(5000);
            previewLinkElements = driver.findElements(By.xpath("//span[contains(.,'nsplay://boot')]"));
        }
        liveSyncConnectionString = previewLinkElements.get(0).getText();
    }


    public void liveSyncPreview() {
        List<String> params;
        deviceScreenWidth = client.driver.manage().window().getSize().width;
        if (settings.deviceType == DeviceType.Simulator) {
            liveSyncConnectionString = liveSyncConnectionString.replaceAll("\\\\", "/");
            params = java.util.Arrays.asList("xcrun", "simctl", "openurl", deviceId, liveSyncConnectionString);
        } else {
            log.info(liveSyncConnectionString);
            params = java.util.Arrays.asList(System.getenv("ANDROID_HOME") + "/platform-tools/adb", "-s", deviceId, "shell", "am", "start", "-a", "android.intent.action.VIEW", "-d", "\"" + liveSyncConnectionString + "\"", "org.nativescript.preview");
        }

        try {
            ProcessBuilder pb = new
                    ProcessBuilder(params);
            log.info(pb.command().toString());
            final Process p = pb.start();
            log.info("Start logging command...");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                log.info(line);
            }
            log.info("End logging command...");
        } catch (Exception ex) {
            log.info(ex.toString());
        }
    }

    public void startPreviewAppWithLiveSync() throws InterruptedException, IOException {
        startPreviewAppWithLiveSync(true);
    }

    public void startPreviewAppWithLiveSync(boolean waitToLoad) throws InterruptedException, IOException {

        liveSyncPreview();
        if (waitToLoad) {
            if (settings.deviceType == DeviceType.Simulator) {
                log.info("Searching for Home or Open");
                wait(5000);
                String foundItem = waitText1OrText2ToBeShown(12, "Home", "Open");
                log.info("Found Item " + foundItem);
                if (foundItem == "Open") {
                    if (settings.platformVersion.toString().contains("10.") || settings.platformVersion.toString().contains("9.")) {
                        if (settings.platformVersion.toString().contains("10.")) {
                            wait(5000);
                            if (ExpectedConditions.alertIsPresent() != null) {
                                client.driver.switchTo().alert().accept();
                            }

                            wait(7000);
                            try {
                                if (ExpectedConditions.alertIsPresent() != null) {
                                    client.driver.switchTo().alert().accept();
                                    wait(2000);
                                }
                            } catch (Exception e) {

                            }
                        } else {
                            wait(5000);
                            if (ExpectedConditions.alertIsPresent() != null) {
                                client.driver.switchTo().alert().dismiss();
                            }
                            wait(5000);
                            functional.tests.core.mobile.device.ios.IOSDevice ios = new functional.tests.core.mobile.device.ios.IOSDevice(client, mobileSettings);
                            deviceId = ios.getId();
                            context.settings.packageId = "org.nativescript.preview";
                            context.settings.testAppFileName = "nsplaydev.app";
                            Capabilities newiOSCapabilities = new Capabilities();
                            context.client.driver = new IOSDriver(context.server.service.getUrl(), newiOSCapabilities.loadDesiredCapabilities(context.settings));
                            wait(6000);
                            if (ExpectedConditions.alertIsPresent() != null) {
                                client.driver.switchTo().alert().dismiss();
                            }
                            wait(6000);
                            if (ExpectedConditions.alertIsPresent() != null) {
                                client.driver.switchTo().alert().dismiss();
                            }
                            wait(6000);
                        }
                    } else {
                        find.byText("Open").click();
                        wait(7000);
                        if (find.byText("Open") != null) {
                            find.byText("Open").click();
                        }

                    }
                }
            } else {
                waitTextToBeShown(10, "Home");
            }
        }
    }

    public void waitPreviewAppToLoad(int numberOfTries) throws InterruptedException {
        waitPreviewAppToLoad(numberOfTries, "Home");
    }

    public String waitText1OrText2ToBeShown(int numberOfTries, String text1, String text2) throws InterruptedException {
        String textFound = "";
        while (true) {
            if (settings.deviceType == DeviceType.Simulator && (settings.platformVersion.toString().contains("10.") || settings.platformVersion.toString().contains("9."))) {
                log.info("Search for image!");
                if (sikuli.waitForImage(text1, 0.7d, 2)) {
                    textFound = text1;
                    break;
                }
                if (sikuli.waitForImage(text2, 0.7d, 2)) {
                    textFound = text2;
                    break;
                }
                numberOfTries = numberOfTries - 1;
                if (numberOfTries <= 0) {
                    log.info("Image " + text1 + " and Image " + text2 + " are not found!");
                    break;
                }

            } else {
                log.info("Search for text!");
                UIElement text1element = find.byText(text1, false, settings.shortTimeout);
                UIElement text2element = find.byText(text2, false, settings.shortTimeout);
                log.info("start checking!");
                if (text1element != null) {
                    textFound = text1;
                    log.info("Found " + textFound);
                    break;
                }
                if (text2element != null) {
                    textFound = text2;
                    log.info("Found " + textFound);
                    break;
                }
                numberOfTries = numberOfTries - 1;
                if (numberOfTries <= 0) {
                    log.info("Text  " + text1 + " and Text " + text2 + " are not found!");
                    break;
                }
            }
            log.info("Nothing found in turn " + numberOfTries);
        }
        log.info("Exit loop! Text found " + textFound);
        return textFound;
    }

    public void waitTextToBeShown(int numberOfTries, String object) throws InterruptedException {
        while (true) {
            if (settings.deviceType == DeviceType.Simulator && (settings.platformVersion.toString().contains("10.") || settings.platformVersion.toString().contains("9."))) {
                if (sikuli.waitForImage(object, 0.7d, 2)) {
                    break;
                }
                numberOfTries = numberOfTries - 1;
                if (numberOfTries <= 0) {
                    log.info("Image " + object + " is not found!");
                    break;
                }

            } else {
                UIElement home = find.byText(object);
                if (home != null || numberOfTries <= 0) {
                    if (numberOfTries <= 0) {
                        log.info("Text " + object + " is not found!");
                    }
                    break;
                }
                numberOfTries = numberOfTries - 1;
            }
            log.info("Nothing found in turn " + numberOfTries);
        }
    }

    public void waitPreviewAppToLoad(int numberOfTries, String object) throws InterruptedException {
        waitTextToBeShown(numberOfTries, object);
        if (settings.deviceType == DeviceType.Simulator && (settings.platformVersion.toString().contains("10.") || settings.platformVersion.toString().contains("9."))) {
            UIRectangle home = sikuli.findImageOnScreen(object, 0.9d);
            Assert.assertNotNull(home, "Preview app not synced! Item missing " + object);
            log.info("Preview app synced! The item " + object + " is found!");
        } else {
            UIElement home = find.byText(object);
            Assert.assertNotNull(home, "Preview app not synced! Item missing " + object);
            log.info("Preview app synced! The item " + object + " is found!");
        }


    }

    public void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void giveFocus() throws InterruptedException {
        //browserAPP.focus();
        //wait(2000);
    }

    public String getComputerName() {
        String computerName = "";
        try {
            ProcessBuilder pb = new
                    ProcessBuilder("hostname");
            log.info(pb.command().toString());
            final Process p = pb.start();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                log.info(line);
                if (line.trim() != "") {
                    computerName = line.trim();
                }
            }
        } catch (Exception ex) {
            log.info(ex.toString());
        }
        return computerName;
    }

    public String getIOSVersion() {
        String version = "";
        List<String> params = java.util.Arrays.asList("xcrun", "simctl", "getenv", deviceId, "SIMULATOR_RUNTIME_VERSION");

        try {
            ProcessBuilder pb = new
                    ProcessBuilder(params);
            log.info(pb.command().toString());
            final Process p = pb.start();
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                log.info(line);
                if (line.trim() != "") {
                    version = line.trim();
                }
            }
        } catch (Exception ex) {
            log.info(ex.toString());
        }
        return version;
    }

    public void getScreenShot(String screenshotName) {
        try {
            Process p = Runtime.getRuntime().exec("screencapture -C -x " + folderForScreenshots + screenshotName + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openURL(String url) {
        List<String> params = null;
        if (settings.deviceType == DeviceType.Simulator) {
            url = url.replaceAll("\\\\", "/");
            params = java.util.Arrays.asList("xcrun", "simctl", "openurl", deviceId, url);
        } else {
            params = java.util.Arrays.asList(System.getenv("ANDROID_HOME") + "/platform-tools/adb", "-s", deviceId, "shell", "am", "start", "-a", "android.intent.action.VIEW", "-d", "\"" + url + "\"");
        }
        try {
            ProcessBuilder pb = new
                    ProcessBuilder(params);
            log.info(pb.command().toString());
            final Process p = pb.start();
            log.info("Start logging command...");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            p.getInputStream()));
            String line;
            while ((line = br.readLine()) != null) {
                log.info(line);
            }
            log.info("End logging command...");
        } catch (Exception ex) {
            log.info(ex.toString());
        }

        if (settings.deviceType == DeviceType.Emulator) {
            wait(4000);

            UIElement acceptContinue = find.byTextContains("Accept & continue");
            if (acceptContinue != null) {
                acceptContinue.click();
                log.info("Navigate to " + acceptContinue);
            } else {
                log.info("Element Accept & continue not found! Not able to click it!");
            }

            UIElement noThanks = find.byTextContains("No Thanks");
            if (noThanks != null) {
                noThanks.click();
                log.info("Navigate to " + noThanks);
            } else {
                log.info("Element No Thanks not found! Not able to click it!");
            }

            UIElement webView = find.byTextContains("WebView");
            if (webView != null) {
                webView.click();
                log.info("Navigate to " + webView);
            } else {
                log.info("Element WebView not found! Not able to click it!");
            }

            wait(3000);
            UIElement webViewForJust = find.byTextContains("Just Once");
            if (webViewForJust != null) {
                webViewForJust.click();
                log.info("Navigate to " + webViewForJust);
            } else {
                log.info("Element Just Once not found! Not able to click it!");
            }
            UIElement cookies = find.byTextContains("accept cookies");
            if (cookies != null) {
                cookies.click();
                log.info("Navigate to " + cookies);
            } else {
                log.info("Element accept cookies not found! Not able to click it!");
            }
            UIElement closeDialog = find.byText("Close", true, 3);
            if (closeDialog != null) {
                closeDialog.click();
                log.info("Navigate to " + closeDialog);
            } else {
                log.info("Element Close not found! Not able to click it!");
            }
        }
    }

    public void navigateToSavedSession(String button) throws InterruptedException {
        if (settings.deviceType == DeviceType.Emulator) {
            List<WebElement> link = (List<WebElement>) client.driver.findElements(By.xpath("//*[@content-desc='Load project in Preview app Tap to open the saved project in the Preview app']"));
            if (link.size() != 0) {
                link.get(0).click();
                log.info("Navigate to " + button);
            } else {
                log.info("Element " + button + " not found by xpath! Not able to click it! Will try be text!");
                List<WebElement> linkBytext = (List<WebElement>) client.driver.findElements(By.xpath("//*[@text='Load project in Preview app Tap to open the saved project in the Preview app']"));
                if (linkBytext.size() != 0) {
                    linkBytext.get(0).click();
                    log.info("Navigate to " + button);
                } else {
                    log.info("Element " + button + " not found! Not able to click it! Not found by text too!");
                }
            }
        } else if (settings.deviceType == DeviceType.Simulator) {
            List<WebElement> buttons = (List<WebElement>) client.driver.findElements(By.xpath("//div[contains(.,'Tap to open the saved project in the Preview app')]"));
            if (buttons.size() != 0) {
                buttons.get(0).click();
                log.info("Navigate to " + button);
            } else {
                log.info("Element " + button + " not found! Not able to click it!");
            }
            buttons.get(buttons.size() - 1).click();
            wait(5000);
            client.driver.context("NATIVE_APP").findElements(By.name("Open")).get(0).click();
            if (find.byText("Open") != null) {
                find.byText("Open").click();
            }
        }


    }

    public void changeIosDriverToWebView() {
        Capabilities newiOSCapabilities = new Capabilities();
        DesiredCapabilities newDesiredCapabilites = new DesiredCapabilities();
        newDesiredCapabilites = newiOSCapabilities.loadDesiredCapabilities(context.settings);
        newDesiredCapabilites.setBrowserName("Safari");
        newDesiredCapabilites.setCapability("autoWebview", true);
        context.client.driver = new IOSDriver(context.server.service.getUrl(), newDesiredCapabilites);
    }

    public void restoreIosDriver() {
        Capabilities newiOSCapabilities = new Capabilities();
        DesiredCapabilities newDesireCapabilites = new DesiredCapabilities();
        newDesireCapabilites = newiOSCapabilities.loadDesiredCapabilities(context.settings);
        newDesireCapabilites.setCapability("newCommandTimeout", 6000);
        context.client.driver = new IOSDriver(context.server.service.getUrl(), newDesireCapabilites);
    }

    public void restoreAndroidDriver() {
        Capabilities newAndroidCapabilities = new Capabilities();
        DesiredCapabilities newDesireCapabilites = new DesiredCapabilities();
        newDesireCapabilites = newAndroidCapabilities.loadDesiredCapabilities(context.settings);
        newDesireCapabilites.setCapability("newCommandTimeout", 6000);
        context.client.driver = new AndroidDriver(context.server.service.getUrl(), newDesireCapabilites);
    }

    public boolean waitUntilWebElementIsPresentByXpath(String xpath, int tries) {
        while (true) {
            tries--;
            List<WebElement> elements = driver.findElements(By.xpath(xpath));
            if (elements.size() != 0) {
                return true;
            }
            if (tries < 1) {
                return false;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean waitUntilWebElementIsPresentByXpath(String xpath) {
        return waitUntilWebElementIsPresentByXpath(xpath, 20);
    }

    public boolean waitUntilWebElementIsPresentByClassName(String className, int tries) {
        while (true) {
            tries--;
            List<WebElement> elements = driver.findElements(By.className(className));
            if (elements.size() != 0) {
                return true;
            }
            if (tries < 1) {
                return false;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean waitUntilWebElementIsPresentByClassName(String className) {
        return waitUntilWebElementIsPresentByClassName(className, 30);
    }

}
