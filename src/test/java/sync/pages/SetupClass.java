package sync.pages;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import functional.tests.core.enums.PlatformType;
import functional.tests.core.mobile.appium.Capabilities;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import functional.tests.core.mobile.device.Device;
import functional.tests.core.mobile.settings.MobileSettings;
import functional.tests.core.image.Sikuli;
import functional.tests.core.image.ImageUtils;
import functional.tests.core.utils.FileSystem;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.Image;
import org.testng.Assert;
import org.sikuli.script.*;
import functional.tests.core.mobile.appium.Client;
import java.awt.event.InputEvent;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.io.File;
import functional.tests.core.mobile.element.UIRectangle;
import functional.tests.core.utils.OSUtils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.imageio.ImageIO;
import javax.swing.*;

public class SetupClass extends BasePage {
public  Screen s = Screen.all();;
public String liveSyncConnectionString;
public String deviceId = "";
public Sikuli sikuli;
public String ImagePathDirectory = "";
public ImageUtils imageUtils;
public Device device;
public int deviceScreenWidth;
public String appName;
public Client client;
public App browserAPP;
public String typeOfProject = OSUtils.getEnvironmentVariable("typeOfProject","ng");
public String browser = OSUtils.getEnvironmentVariable("browser","Google Chrome");
public String folderForScreenshots;
public String folderForDesktopScreenshots;
public Integer imageNumber = 0;
public Robot robot = null;
public MobileSettings mobileSettings;
    public SetupClass(Client client, MobileSettings mobileSettings, Device device) throws InterruptedException, IOException, FindFailed {
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
        this.sikuli = new Sikuli(this.appName, client, this.imageUtils);
        String currentPath = System.getProperty("user.dir");
        ImagePathDirectory = currentPath+"/src/test/java/sync/pages/images.sikuli";
        this.folderForScreenshots = currentPath+"/target/surefire-reports/screenshots/";
        this.folderForDesktopScreenshots = currentPath+"/temp/";
        File directory = new File(folderForDesktopScreenshots);
        if (!directory.exists()){
            directory.mkdir();
        }
        else{
            directory.delete();
            directory.mkdir();
        }
        this.client.driver.removeApp("org.nativescript.preview");
        this.wait(2000);
        if(settings.deviceType == settings.deviceType.Simulator)
        {
            functional.tests.core.utils.Archive.extractArchive(new File(currentPath+"/testapp/nsplaydev.tgz"),new File(currentPath+"/testapp/"));
            this.wait(2000);
            functional.tests.core.mobile.device.ios.IOSDevice ios = new functional.tests.core.mobile.device.ios.IOSDevice(client, mobileSettings);
            ios.installApp("nsplaydev.app","org.nativescript.preview");
            this.deviceId=ios.getId();
            context.settings.packageId = "org.nativescript.preview";
            context.settings.testAppFileName = "nsplaydev.app";
            Capabilities newiOSCapabilities = new Capabilities();
            DesiredCapabilities newDesireCapabilites = new DesiredCapabilities();
            newDesireCapabilites = newiOSCapabilities.loadDesiredCapabilities(context.settings);
            context.client.driver = new IOSDriver(context.server.service.getUrl(), newDesireCapabilites);
        }
        else {
            functional.tests.core.mobile.device.android.AndroidDevice android = new functional.tests.core.mobile.device.android.AndroidDevice(client, mobileSettings);
            android.installApp("app-universal-release.apk", "org.nativescript.preview");
            this.deviceId=android.getId();
            context.settings.packageId = "org.nativescript.preview";
            context.settings.testAppFileName = "app-universal-release.apk";
            Capabilities newAndroidCapabilities = new Capabilities();
            context.client.driver = new AndroidDriver(context.server.service.getUrl(), newAndroidCapabilities.loadDesiredCapabilities(context.settings));
        }

        ImagePath.add(ImagePathDirectory);
        //this.CloseBrowser();
        this.OpenBrowser();
    }

    public void CloseBrowser() throws InterruptedException, IOException {
        App.close(this.browser);
        this.wait(5000);
    }

    public void OpenBrowser() throws InterruptedException {
        this.wait(1000);
        this.browserAPP = App.open(this.browser);
        this.wait(12000);
        s.type("f", KeyModifier.CMD+KeyModifier.CTRL);
        this.wait(2000);
    }

    public void NavigateToPage(String URL) throws InterruptedException {
        s.type("l", KeyModifier.CMD);
        this.wait(1000);
        s.type(URL);
        this.wait(1000);
        s.type(Key.ENTER);
        this.wait(20000);
    }

    public void GetDeviceLink() throws InterruptedException, FindFailed, IOException, UnsupportedFlavorException {
        if(existsOnDesktopScreen("playnowbutton.png")) {
            clickOnDesktop("playnowbutton.png");
            this.wait(2000);
        }
        if(existsOnDesktopScreen("acceptCookies.png")) {
            clickOnDesktop("acceptCookies.png");
            this.wait(2000);
        }
        if(!existsOnDesktopScreen("devicesLinkMessage.png")) {
            if(this.browser.equals("Safari"))
            {
                clickOnDesktop("qrcodeSafari.png");
            }
            else {
                clickOnDesktop("qrcode.png", 0.63f);
            }
        }
        this.wait(3000);
        clickOnDesktop("devicesLinkMessage.png");
        this.wait(3000);
        s.dragDrop(findImageOnDesktopScreen("devicesLinkMessage.png", 0.63, -101, 0),
                findImageOnDesktopScreen("devicesLinkMessage.png", 0.63, 500, 25));
        this.wait(3000);
        s.type("c", KeyModifier.CMD);
        this.liveSyncConnectionString = (String) Toolkit.getDefaultToolkit()
                .getSystemClipboard().getData(DataFlavor.stringFlavor);

    }

    public void startPreviewAppWithLiveSync() throws InterruptedException, FindFailed, IOException {
        List<String> params;
        this.deviceScreenWidth = client.driver.manage().window().getSize().width;
        if(settings.deviceType == settings.deviceType.Simulator)
        {
            this.liveSyncConnectionString = this.liveSyncConnectionString.replaceAll("\\\\", "/");
            params = java.util.Arrays.asList("xcrun", "simctl", "openurl", this.deviceId, liveSyncConnectionString);
        }
        else
        {
            log.info(liveSyncConnectionString);
            params = java.util.Arrays.asList(System.getenv("ANDROID_HOME")+"/platform-tools/adb", "-s" ,this.deviceId, "shell" ,"am" , "start" , "-a", "android.intent.action.VIEW", "-d", "\""+liveSyncConnectionString+"\"", "org.nativescript.preview");
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

        if(settings.deviceType == settings.deviceType.Simulator) {
            log.info("Searching for Home or Open");
            this.wait(5000);
            String foundItem = this.waitText1OrText2ToBeShown(12,"Home", "Open");
            log.info("Found Item "+foundItem);
            if(foundItem == "Open") {
                if (this.settings.platformVersion.toString().contains("10.") || this.settings.platformVersion.toString().contains("9.")) {
                    if(this.settings.platformVersion.toString().contains("10.")) {
                        this.wait(5000);
                        if (ExpectedConditions.alertIsPresent() != null) {
                            this.client.driver.switchTo().alert().accept();
                        }

                        this.wait(2000);

                        this.waitPreviewAppToLoad(10, "Open");
                        if (ExpectedConditions.alertIsPresent() != null) {
                            this.client.driver.switchTo().alert().accept();
                        }

                        this.wait(2000);
                    }
                    else {
                        this.wait(5000);
                        if (ExpectedConditions.alertIsPresent() != null) {
                            this.client.driver.switchTo().alert().dismiss();
                        }
                        this.wait(5000);
                        functional.tests.core.mobile.device.ios.IOSDevice ios = new functional.tests.core.mobile.device.ios.IOSDevice(client, mobileSettings);
                        this.deviceId=ios.getId();
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
                }
                else {
                    this.find.byText("Open").click();
                    this.waitPreviewAppToLoad(10, "Open");
                    this.find.byText("Open").click();
                }
            }

        }

        this.waitPreviewAppToLoad(10);
        this.wait(6000);
    }

    public void waitPreviewAppToLoad(int numberOfTries) throws InterruptedException {
        this.waitPreviewAppToLoad(numberOfTries, "Home");
    }

    public String waitText1OrText2ToBeShown(int numberOfTries, String text1, String text2) throws InterruptedException {
        String textFound="";
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
                    log.info("Image "+ text1 + " and Image "+text2 + " are not found!");
                    break;
                }

            } else {
                log.info("Search for text!");
                UIElement text1element = this.find.byText(text1, false, this.settings.shortTimeout);
                UIElement text2element = this.find.byText(text2, false, this.settings.shortTimeout);
                log.info("start checking!");
                if (text1element != null) {
                    textFound = text1;
                    log.info("Found "+textFound);
                    break;
                }
                if (text2element != null) {
                    textFound = text2;
                    log.info("Found "+textFound);
                    break;
                }
                numberOfTries = numberOfTries - 1;
                if (numberOfTries <= 0) {
                    log.info("Text  " + text1 + " and Text " + text2 + " are not found!");
                    break;
                }
            }
            log.info("Nothing found in turn "+numberOfTries);
        }
        log.info("Exit loop! Text found "+textFound);
        return textFound;
    }

    public void waitTextToBeShown(int numberOfTries, String object) throws InterruptedException {
        while (true)
        {
            if (this.settings.platformVersion.toString().contains("10.") || this.settings.platformVersion.toString().contains("9.")) {
                if (this.sikuli.waitForImage(object, 0.7d, 2)) {
                    break;
                }
                numberOfTries = numberOfTries - 1;
                if (numberOfTries <= 0) {
                    log.info("Image "+ object + " is not found!");
                    break;
                }

            }
            else {
                UIElement home = this.find.byText(object);
                if (home != null || numberOfTries <= 0) {
                    if (numberOfTries <= 0) {
                        log.info("Text " + object + " is not found!");
                    }
                    break;
                }
                numberOfTries = numberOfTries - 1;
            }
            log.info("Nothing found in turn "+numberOfTries);
        }
    }

    public void waitPreviewAppToLoad(int numberOfTries, String object) throws InterruptedException {
        this.waitTextToBeShown(numberOfTries,object);
        if (this.settings.platformVersion.toString().contains("10.") || this.settings.platformVersion.toString().contains("9.")) {
            UIRectangle home = this.findImageOnScreen(object, 0.9d);
            Assert.assertNotNull(home, "Preview app not synced! Item missing "+ object);
            this.log.info("Preview app synced! The item "+object+" is found!");
        }
        else{
            UIElement home = this.find.byText(object);
            Assert.assertNotNull(home, "Preview app not synced! Item missing "+ object);
            this.log.info("Preview app synced! The item "+object+" is found!");
        }


    }

    public UIRectangle findImageOnScreen(String imageName, double similarity) {
        BufferedImage screenBufferImage = this.device.getScreenshot();
        Finder finder = this.getFinder(screenBufferImage, imageName, (float)similarity, 0, 0, false);
        Match searchedImageMatch = finder.next();
        Point point;
        if(searchedImageMatch != null) {
            point = searchedImageMatch.getCenter().getPoint();
        }
        else
        {
            return null;
        }
        Rectangle rectangle = this.getRectangle(point, screenBufferImage.getWidth());
        return new UIRectangle(rectangle);
    }

    private Finder getFinder(BufferedImage screenBufferImage, String imageName, float similarity, int offsetX, int offsetY, boolean isDesktop) {
        ImageUtils var10001 = this.imageUtils;
        BufferedImage searchedBufferImage = null;
        if(!isDesktop) {
            searchedBufferImage = this.imageUtils.getImageFromFile(this.getImageFullName(this.getImageFolderPath(this.appName), imageName));
        }
        else
        {
            searchedBufferImage = this.imageUtils.getImageFromFile(this.getImageFullName(this.ImagePathDirectory, imageName));
        }
        Image searchedImage = new Image(searchedBufferImage);
        Pattern searchedImagePattern = new Pattern(searchedImage);
        Image mainImage = new Image(screenBufferImage);

        if (similarity != 0.0)
        {
            searchedImagePattern.similar(similarity);
        }

        if (offsetX != 0 && offsetY != 0)
        {
            searchedImagePattern.targetOffset(offsetX, offsetY);
        }

        Finder finder = new Finder(mainImage);
        finder.findAll(searchedImagePattern);
        return finder;
    }

    protected String getImageFolderPath(String appName) {
        String imageFolderPath = this.settings.screenshotResDir + File.separator + appName + File.separator + this.settings.deviceName;
        FileSystem.ensureFolderExists(imageFolderPath);
        return imageFolderPath;
    }
    public Rectangle getRectangle(Point point, int screenShotWidth) {
        int densityRatio = this.getDensityRatio(screenShotWidth);
        Rectangle rectangle = new Rectangle(point.x / densityRatio, point.y / densityRatio, 50, 50);
        return rectangle;
    }

    private int getDensityRatio(int screenshotWidth) {
        return this.client.settings.platform == PlatformType.iOS ? screenshotWidth / this.deviceScreenWidth : 1;
    }

    public void wait(int time) {
        synchronized(this.s) {
            try {
                this.s.wait(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void giveFocus() throws InterruptedException {
        this.browserAPP.focus();
        this.wait(2000);
    }

    public String getComputerName()
    {
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
                if(line.trim()!="")
                {
                    computerName = line.trim();
                }
            }
        } catch (Exception ex) {
            log.info(ex.toString());
        }
        return  computerName;
    }

    public String getIOSVersion()
    {
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
                if(line.trim()!="")
                {
                    version = line.trim();
                }
            }
        } catch (Exception ex) {
            log.info(ex.toString());
        }
        return  version;
    }

    public void getScreenShot(String screenshotName){
        try {
            Process p = Runtime.getRuntime().exec("screencapture -C -x " + this.folderForScreenshots + screenshotName + ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeTutorial()
    {
        Region gettingStartedRegion = null;
        Region closeButton = null;
        try {
             gettingStartedRegion = this.s.find("gettingstartedlogo");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
            log.info("Tutorial is not opened!");
        }
        if(gettingStartedRegion!=null) {
            try {
                closeButton = gettingStartedRegion.right().find("closebutton");
            } catch (FindFailed findFailed) {
                findFailed.printStackTrace();
                log.info("Couldn't find close button for tutorial!");
            }
            closeButton.click();
            this.wait(3000);
            try {
                s.click("okbuttonTutorial");
            } catch (FindFailed findFailed) {
                findFailed.printStackTrace();
            }
            this.wait(3000);
            log.info("Tutorial is closed!");
        }
    }

    public void openURL(String url)
    {
        List<String> params = null;
        if(settings.deviceType == settings.deviceType.Simulator)
        {
            url = url.replaceAll("\\\\", "/");
            params = java.util.Arrays.asList("xcrun", "simctl", "openurl", this.deviceId, url);
        }
        else {
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

        if(settings.deviceType == settings.deviceType.Emulator) {
            this.wait(4000);
            UIElement webView = this.find.byTextContains("WebView");
           if(webView!=null) {
                webView.click();
                this.log.info("Navigate to " + webView);
            }
            else {
                this.log.info("Element " + webView + " not found! Not able to click it!");
            }

            this.wait(3000);
            UIElement webViewForJust  = this.find.byTextContains("Just Once");
            if(webViewForJust!=null) {
                webViewForJust.click();
                this.log.info("Navigate to " + webViewForJust);
            }
            else {
                this.log.info("Element " + webViewForJust + " not found! Not able to click it!");
            }
        }
    }

    public void navigateToSavedSession(String button) throws InterruptedException {
        if(settings.deviceType == settings.deviceType.Emulator) {
            List<WebElement> link = (List<WebElement>) this.client.driver.findElements(By.xpath("//*[@content-desc='Load project in Preview app Tap to open the saved project in the Preview app']"));
            if(link.size()!=0) {
                link.get(0).click();
                this.log.info("Navigate to " + button);
            }
            else {
                this.log.info("Element " + button + " not found! Not able to click it!");
            }
        }
        else if (settings.deviceType == settings.deviceType.Simulator)
        {
            UIRectangle link = this.findImageOnScreen("SavedSession", 0.8d);
            if(link!=null) {
                new TouchAction((MobileDriver) this.client.driver).tap((new PointOption().withCoordinates((link.getRectangle().x ), (link.getRectangle().y)))).perform();
                //this.client.driver.tap(1,link.getRectangle().x,link.getRectangle().y,500);
                this.wait(7000);
                if(this.settings.platformVersion.toString().contains("10.") || this.settings.platformVersion.toString().contains("9.")) {
                    this.client.driver.switchTo().alert().accept();
                }
                else {
                    UIRectangle openButton = this.findImageOnScreen("Open", 0.8d);
                    new TouchAction((MobileDriver) this.client.driver).tap((new PointOption().withCoordinates((openButton.getRectangle().x ), (openButton.getRectangle().y)))).perform();
                    //this.client.driver.tap(1,openButton.getRectangle().x,openButton.getRectangle().y,500);
                }
                this.wait(7000);
            }
            else {
                this.log.info("Element " + button + " not found! Not able to click it!");
            }
        }


    }

   public void changeIosDriverToWebView(){
       Capabilities newiOSCapabilities = new Capabilities();
       DesiredCapabilities newDesiredCapabilites = new DesiredCapabilities();
       newDesiredCapabilites = newiOSCapabilities.loadDesiredCapabilities(context.settings);
       newDesiredCapabilites.setBrowserName("Safari");
       newDesiredCapabilites.setCapability("autoWebview",true);
       context.client.driver = new IOSDriver(context.server.service.getUrl(), newDesiredCapabilites);
   }

    public void restoreIosDriver(){
        Capabilities newiOSCapabilities = new Capabilities();
        DesiredCapabilities newDesiredCapabilites = new DesiredCapabilities();
        newDesiredCapabilites = newiOSCapabilities.loadDesiredCapabilities(this.mobileSettings);
        context.client.driver = new IOSDriver(context.server.service.getUrl(), newDesiredCapabilites);
    }

    public void refreshAndroidDriver(){
        Capabilities newAndroidCapabilities = new Capabilities();
        context.client.driver = new AndroidDriver(context.server.service.getUrl(), newAndroidCapabilities.loadDesiredCapabilities(context.settings));

    }

    public Region findImageOnDesktopScreen(String imageName, double similarity, int offsetX, int offsetY) {
        BufferedImage screenBufferImage = getScreenShotForSikuli();

        Finder finder = this.getFinder(screenBufferImage, imageName, (float) similarity, offsetX, offsetY, true);

        Match searchedImageMatch = finder.next();

        if(searchedImageMatch == null)
        {
            throw new Error(imageName + " with severity " + similarity + " could not be found!");
        }

        Point point = searchedImageMatch.getCenter().getPoint();

        Rectangle rectangle =  new Rectangle(point.x , point.y , 0, 0);

        return new Region(rectangle);
    }

    public boolean existsOnDesktopScreen(String imageName, double similarity) {
        BufferedImage screenBufferImage = getScreenShotForSikuli();

        Finder finder = this.getFinder(screenBufferImage, imageName, (float) similarity, 0, 0, true);

        Match searchedImageMatch = finder.next();
        if(searchedImageMatch !=  null)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    public boolean existsOnDesktopScreen(String imageName) {
        return existsOnDesktopScreen(imageName, 0);
    }

    public void clickOnDesktop(String imageName, double similarity, int offsetX, int offsetY){
        Region objectToClick = findImageOnDesktopScreen(imageName, similarity, offsetX, offsetY);

        //try {
        //    this.s.click(objectToClick);
        //} catch (FindFailed findFailed) {
        //    findFailed.printStackTrace();
       // }
        Click myClick = new Click(objectToClick.x, objectToClick.y);
        Thread click = new Thread(myClick);
        click.start();
        try {
            click.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("CLick on " + imageName);
    }

    public class Click implements Runnable {
        private int x;
        private int y;

        public Click(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void run() {
            try {
                Robot myRobot = new Robot();
                myRobot.mouseMove(x, y);
                myRobot.mousePress(InputEvent.BUTTON1_MASK);
                myRobot.delay(1000);
                myRobot.mouseRelease(InputEvent.BUTTON1_MASK);
                myRobot.delay(1000);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }

    }

    public void clickOnDesktop(String imageName, double similarity){
        clickOnDesktop(imageName, similarity, 0, 0);
    }

    public void clickOnDesktop(String imageName, int offsetX, int offsetY){
        clickOnDesktop(imageName, 0, offsetX, offsetY);
    }

    public void clickOnDesktop(String imageName){
        clickOnDesktop(imageName, 0, 0, 0);
    }

    public BufferedImage getScreenShotForSikuli() {
//        Process p = null;
//        try {
//            this.wait(10000);
//            p = Runtime.getRuntime().exec("screencapture -S -x -r -t png " + this.folderForDesktopScreenshots + this.imageNumber + ".png");
//            this.wait(10000);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice[] screens = ge.getScreenDevices();

        Rectangle allScreenBounds = new Rectangle();
        for (GraphicsDevice screen : screens) {
            Rectangle screenBounds = screen.getDefaultConfiguration().getBounds();

            allScreenBounds.width += screenBounds.width;
            allScreenBounds.height = Math.max(allScreenBounds.height, screenBounds.height);
        }

        BufferedImage screenShot = robot.createScreenCapture(allScreenBounds);
        File f = new File(this.folderForScreenshots + "test" +imageNumber+ ".png");
        imageNumber++;
        System.out.println("Save image " + imageNumber);
        try {
            ImageIO.write(screenShot, "png", f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return screenShot;
        //try {
            //p.waitFor();

       // } catch (InterruptedException e) {
            //e.printStackTrace();
        //}
//        File screenFile = new File(this.folderForDesktopScreenshots + this.imageNumber + ".png");
//        this.imageNumber++;
//        try {
//            return ImageIO.read(screenFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
    }

    public static String getImageFullName(String imageFolderPath, String imageName) {
        String imageFullName = null;
        if(imageName.contains(".png")) {
            imageFullName = imageFolderPath + File.separator + imageName;
        }
        else{
            imageFullName = imageFolderPath + File.separator + imageName + ".png";
        }

        return imageFullName;
    }

}
