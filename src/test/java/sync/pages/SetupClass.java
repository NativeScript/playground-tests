package sync.pages;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.sikuli.script.*;
import functional.tests.core.mobile.device.android.Adb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import functional.tests.core.mobile.appium.Capabilities;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import functional.tests.core.mobile.device.Device;
import functional.tests.core.mobile.settings.MobileSettings;
import functional.tests.core.image.Sikuli;
import functional.tests.core.image.ImageUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import functional.tests.core.mobile.appium.Client;
import java.awt.Robot;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.io.File;
import functional.tests.core.mobile.element.UIRectangle;
import functional.tests.core.utils.OSUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;


public class SetupClass extends BasePage {
    public Robot s = new Robot();
    public String liveSyncConnectionString;
    public String deviceId = "";
    public Sikuli sikuli;
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

    public boolean isLive = false;

    public SetupClass(Client client, MobileSettings mobileSettings, Device device) throws InterruptedException, IOException, FindFailed, AWTException {
        super();
        this.mobileSettings = mobileSettings;
        this.client = client;
        this.device = device;
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        this.imageUtils = new ImageUtils(settings, client, device);
        this.appName = this.app.getName().replaceAll(".app", "");
        this.sikuli = new Sikuli(this.appName, client, this.context.imageUtils);
        String currentPath = System.getProperty("user.dir");
        ImagePathDirectory = currentPath + "/src/test/java/sync/pages/images.sikuli";
        this.folderForScreenshots = currentPath + "/target/surefire-reports/screenshots/";
        this.folderForDesktopScreenshots = currentPath + "/temp/";
        File directory = new File(folderForDesktopScreenshots);
        if (!directory.exists()) {
            directory.mkdir();
        } else {
            directory.delete();
            directory.mkdir();
        }

        if (settings.deviceType == settings.deviceType.Simulator) {
            this.client.driver.removeApp("org.nativescript.preview");
            functional.tests.core.utils.Archive.extractArchive(new File(currentPath + "/testapp/nsplaydev.tgz"), new File(currentPath + "/testapp/"));

            functional.tests.core.mobile.device.ios.IOSDevice ios = new functional.tests.core.mobile.device.ios.IOSDevice(client, mobileSettings);
            ios.installApp("nsplaydev.app", "org.nativescript.preview");
            this.deviceId = ios.getId();
            context.settings.packageId = "org.nativescript.preview";
            context.settings.testAppFileName = "nsplaydev.app";
            Capabilities newiOSCapabilities = new Capabilities();
            DesiredCapabilities newDesireCapabilites = new DesiredCapabilities();
            newDesireCapabilites = newiOSCapabilities.loadDesiredCapabilities(context.settings);
            newDesireCapabilites.setCapability("newCommandTimeout", 6000);
            context.client.driver = new IOSDriver(context.server.service.getUrl(), newDesireCapabilites);
        } else {
            functional.tests.core.mobile.device.android.AndroidDevice android = new functional.tests.core.mobile.device.android.AndroidDevice(client, mobileSettings);
            new Adb(this.mobileSettings).uninstallApp("org.nativescript.preview");
            android.installApp("app-universal-release.apk", "org.nativescript.preview");
            this.deviceId = android.getId();
            context.settings.packageId = "org.nativescript.preview";
            context.settings.testAppFileName = "app-universal-release.apk";
            Capabilities newAndroidCapabilities = new Capabilities();
            DesiredCapabilities newDesireCapabilites = new DesiredCapabilities();
            newDesireCapabilites = newAndroidCapabilities.loadDesiredCapabilities(context.settings);
            newDesireCapabilites.setCapability("newCommandTimeout", 6000);
            context.client.driver = new AndroidDriver(context.server.service.getUrl(), newDesireCapabilites);
        }

        //this.CloseBrowser();
        this.OpenBrowser();
    }

    public void OpenBrowser() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        Thread.sleep(10000);
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        options.addArguments("disable-application-cache");
        options.addArguments("incognito");
        options.addArguments("no-sandbox");
        this.driver = new ChromeDriver(options);
        Thread.sleep(10000);

    }

    public void NavigateToPage(String URL) throws InterruptedException {
        driver.get(URL);
        Thread.sleep(5000);
        driver.findElements(By.xpath("//button[contains(.,'Accept Cookies')]")).get(0).click();
    }

    public void GetDeviceLink() throws InterruptedException, IOException, UnsupportedFlavorException {
        this.liveSyncConnectionString = driver.findElements(By.xpath("//span[contains(.,'nsplay://boot')]")).get(0).getText();
    }

    public void startPreviewAppWithLiveSync() throws InterruptedException, IOException {
        List<String> params;
        this.deviceScreenWidth = client.driver.manage().window().getSize().width;
        if (settings.deviceType == settings.deviceType.Simulator) {
            this.liveSyncConnectionString = this.liveSyncConnectionString.replaceAll("\\\\", "/");
            params = java.util.Arrays.asList("xcrun", "simctl", "openurl", this.deviceId, liveSyncConnectionString);
        } else {
            log.info(liveSyncConnectionString);
            params = java.util.Arrays.asList(System.getenv("ANDROID_HOME") + "/platform-tools/adb", "-s", this.deviceId, "shell", "am", "start", "-a", "android.intent.action.VIEW", "-d", "\"" + liveSyncConnectionString + "\"", "org.nativescript.preview");
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

        if (settings.deviceType == settings.deviceType.Simulator) {
            log.info("Searching for Home or Open");
            this.wait(5000);
            String foundItem = this.waitText1OrText2ToBeShown(12, "Home", "Open");
            log.info("Found Item " + foundItem);
            if (foundItem == "Open") {
                if (this.settings.platformVersion.toString().contains("10.") || this.settings.platformVersion.toString().contains("9.")) {
                    if (this.settings.platformVersion.toString().contains("10.")) {
                        this.wait(5000);
                        if (ExpectedConditions.alertIsPresent() != null) {
                            this.client.driver.switchTo().alert().accept();
                        }

                        this.wait(7000);
                        try {
                            if (ExpectedConditions.alertIsPresent() != null) {
                                this.client.driver.switchTo().alert().accept();
                                this.wait(2000);
                            }
                        } catch (Exception e) {

                        }
                    } else {
                        this.wait(5000);
                        if (ExpectedConditions.alertIsPresent() != null) {
                            this.client.driver.switchTo().alert().dismiss();
                        }
                        this.wait(5000);
                        functional.tests.core.mobile.device.ios.IOSDevice ios = new functional.tests.core.mobile.device.ios.IOSDevice(client, mobileSettings);
                        this.deviceId = ios.getId();
                        context.settings.packageId = "org.nativescript.preview";
                        context.settings.testAppFileName = "nsplaydev.app";
                        Capabilities newiOSCapabilities = new Capabilities();
                        context.client.driver = new IOSDriver(context.server.service.getUrl(), newiOSCapabilities.loadDesiredCapabilities(context.settings));
                        this.wait(6000);
                        if (ExpectedConditions.alertIsPresent() != null) {
                            this.client.driver.switchTo().alert().dismiss();
                        }
                        this.wait(6000);
                        if (ExpectedConditions.alertIsPresent() != null) {
                            this.client.driver.switchTo().alert().dismiss();
                        }
                        this.wait(6000);
                    }
                } else {
                    this.find.byText("Open").click();
                    this.wait(7000);
                    if (this.find.byText("Open") != null) {
                        this.find.byText("Open").click();
                    }
                }
            }

        }
    }

    public void waitPreviewAppToLoad(int numberOfTries) throws InterruptedException {
        this.waitPreviewAppToLoad(numberOfTries, "Home");
    }

    public String waitText1OrText2ToBeShown(int numberOfTries, String text1, String text2) throws InterruptedException {
        String textFound = "";
        while (true) {
            if (this.settings.platformVersion.toString().contains("10.") || this.settings.platformVersion.toString().contains("9.")) {
                log.info("Search for image!");
                if (this.sikuli.waitForImage(text1, 0.7d, 2)) {
                    textFound = text1;
                    break;
                }
                if (this.sikuli.waitForImage(text2, 0.7d, 2)) {
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
                UIElement text1element = this.find.byText(text1, false, this.settings.shortTimeout);
                UIElement text2element = this.find.byText(text2, false, this.settings.shortTimeout);
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
            if (this.settings.platformVersion.toString().contains("10.") || this.settings.platformVersion.toString().contains("9.")) {
                if (this.sikuli.waitForImage(object, 0.7d, 2)) {
                    break;
                }
                numberOfTries = numberOfTries - 1;
                if (numberOfTries <= 0) {
                    log.info("Image " + object + " is not found!");
                    break;
                }

            } else {
                UIElement home = this.find.byText(object);
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
        this.waitTextToBeShown(numberOfTries, object);
        if (this.settings.platformVersion.toString().contains("10.") || this.settings.platformVersion.toString().contains("9.")) {
            UIRectangle home = this.sikuli.findImageOnScreen(object, 0.9d);
            Assert.assertNotNull(home, "Preview app not synced! Item missing " + object);
            this.log.info("Preview app synced! The item " + object + " is found!");
        } else {
            UIElement home = this.find.byText(object);
            Assert.assertNotNull(home, "Preview app not synced! Item missing " + object);
            this.log.info("Preview app synced! The item " + object + " is found!");
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
        //this.browserAPP.focus();
        //this.wait(2000);
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
        List<String> params = java.util.Arrays.asList("xcrun", "simctl", "getenv", this.deviceId, "SIMULATOR_RUNTIME_VERSION");

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
            Process p = Runtime.getRuntime().exec("screencapture -C -x " + this.folderForScreenshots + screenshotName + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openURL(String url) {
        List<String> params = null;
        if (settings.deviceType == settings.deviceType.Simulator) {
            url = url.replaceAll("\\\\", "/");
            params = java.util.Arrays.asList("xcrun", "simctl", "openurl", this.deviceId, url);
        } else {
            params = java.util.Arrays.asList(System.getenv("ANDROID_HOME") + "/platform-tools/adb", "-s", this.deviceId, "shell", "am", "start", "-a", "android.intent.action.VIEW", "-d", "\"" + url + "\"");
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

        if (settings.deviceType == settings.deviceType.Emulator) {
            this.wait(4000);
            UIElement webView = this.find.byTextContains("WebView");
            if (webView != null) {
                webView.click();
                this.log.info("Navigate to " + webView);
            } else {
                this.log.info("Element " + webView + " not found! Not able to click it!");
            }

            this.wait(3000);
            UIElement webViewForJust = this.find.byTextContains("Just Once");
            if (webViewForJust != null) {
                webViewForJust.click();
                this.log.info("Navigate to " + webViewForJust);
            } else {
                this.log.info("Element " + webViewForJust + " not found! Not able to click it!");
            }
        }
    }

    public void navigateToSavedSession(String button) throws InterruptedException {
        if (settings.deviceType == settings.deviceType.Emulator) {
            List<WebElement> link = (List<WebElement>) this.client.driver.findElements(By.xpath("//*[@content-desc='Load project in Preview app Tap to open the saved project in the Preview app']"));
            if (link.size() != 0) {
                link.get(0).click();
                this.log.info("Navigate to " + button);
            } else {
                this.log.info("Element " + button + " not found! Not able to click it!");
            }
        } else if (settings.deviceType == settings.deviceType.Simulator) {
            List<WebElement> buttons = (List<WebElement>)this.client.driver.findElements(By.xpath("//div[contains(.,'Tap to open the saved project in the Preview app')]"));
            if (buttons.size() != 0) {
                buttons.get(0).click();
                this.log.info("Navigate to " + button);
            } else {
                this.log.info("Element " + button + " not found! Not able to click it!");
            }
            buttons.get(buttons.size() - 1).click();
            this.wait(5000);
            this.restoreIosDriver();
            this.find.byText("Return to Safari").click();
            if (this.find.byText("Open") != null) {
                this.find.byText("Open").click();
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

    public void refreshAndroidDriver() {
        Capabilities newAndroidCapabilities = new Capabilities();
        DesiredCapabilities newDesireCapabilites = new DesiredCapabilities();
        newDesireCapabilites = newAndroidCapabilities.loadDesiredCapabilities(context.settings);
        newDesireCapabilites.setCapability("newCommandTimeout", 6000);
        context.client.driver = new AndroidDriver(context.server.service.getUrl(), newDesireCapabilites);

    }

    public static String getImageFullName(String imageFolderPath, String imageName) {
        String imageFullName = null;
        if (imageName.contains(".png")) {
            imageFullName = imageFolderPath + File.separator + imageName;
        } else {
            imageFullName = imageFolderPath + File.separator + imageName + ".png";
        }

        return imageFullName;
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
