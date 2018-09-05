package nsplay.tests;

import functional.tests.core.enums.PlatformType;
import functional.tests.core.mobile.basetest.MobileTest;
import functional.tests.core.mobile.element.UIElement;
import nsplay.pages.QRPage;
import nsplay.pages.ScanPage;
import nsplay.pages.WebPage;
import nsplay.pages.InfoPage;
import org.testng.annotations.Test;
import org.testng.SkipException;


public class NsPlayTests extends MobileTest {

    @Test(description = "Verify scan page looks OK.", groups = {"android", "ios"})
    public void test_01_scan_page_looks_ok() throws Exception {
        synchronized(this.wait) {
            this.wait.wait(10000);
        }
        ScanPage scanPage = new ScanPage();
        this.assertScreen("nsplay-home-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify details page looks OK.", groups = {"android", "ios"})
    public void test_02_QR_page_looks_ok() throws Exception {
        ScanPage scanPage = new ScanPage();
        scanPage.navigate("Scan QR code");
        scanPage.waitForElement(9000);
        if(settings.deviceType == settings.deviceType.Simulator)
        {
            this.assertScreen("nsplay-qr-ios-view", this.settings.shortTimeout);
            scanPage.navigate("Close");

        }
        else if(settings.deviceType == settings.deviceType.Emulator)
        {

            if(settings.deviceName.contains("Api24")||settings.deviceName.contains("Api25"))
            {
                scanPage.waitForElement(6000);
                if(scanPage.checkIfElementisShown("OK")) {
                    scanPage.navigate("OK");
                    scanPage.waitForElement(35000);
                    scanPage.navigate("Scan QR code");
                    scanPage.waitForElement(10000);
                }
            }
            if(scanPage.checkIfElementisShown("Allow")) {
                scanPage.navigate("Allow");
            }
            QRPage detailsPage = new QRPage();
            this.assertScreen("nsplay-qr-android-emulator-view", this.settings.defaultTimeout,50.0);
            detailsPage.navigateBack();
            scanPage.navigate("Scan QR code");
            scanPage.waitForElement(9000);
            this.assertScreen("nsplay-qr-android-emulator-view", this.settings.defaultTimeout,50.0);

        }
        else
        {
            QRPage detailsPage = new QRPage();
            this.assertScreen("nsplay-qr-view", this.settings.shortTimeout);
        }
    }

    @Test(description = "Verify scan page looks OK.", groups = {"android", "ios"})
    public void test_03_info_page_looks_ok() throws Exception {

        if(settings.deviceType == settings.deviceType.Emulator)
        {
        QRPage detailsPage = new QRPage();

            detailsPage.navigateBack();
        }
        InfoPage scanPage = new InfoPage();
        this.assertScreen("nsplay-info-view", this.settings.shortTimeout);
        if(settings.deviceType == settings.deviceType.Emulator)
        {
            scanPage.scrollDown();
            scanPage.scrollDown();
            scanPage.scrollDown();
            scanPage.scrollDown();
            this.assertScreen("nsplay-info-view-scrolled-emulator", this.settings.shortTimeout, 1.00);
        }

    }

    @Test(description = "Verify web nativescript page looks OK.", groups = { "iOS"})
    public void test_04_WebNativescript_page_looks_ok() throws Exception {
        ScanPage scanPage = new ScanPage();
        UIElement scan = this.find.byText("Scan");
        if(scan!=null)
        {
            scan.click();
        }

        if(settings.deviceName.contains("Api19")) {

        }
        else
        {
            scanPage.navigate("https://www.nativescript.org");
        }
        if(settings.deviceName.contains("Api27")||settings.deviceName.contains("Api26")||settings.deviceName.contains("Api25"))
        {
            scanPage.waitForElement(4000);
            if(scanPage.checkIfElementisShown("Chrome")) {
                scanPage.navigate("Chrome");
                scanPage.waitForElement(2000);
            }
            if(scanPage.checkIfElementisShown("Just once")) {
                scanPage.navigate("Just once");
                scanPage.waitForElement(4000);
            }
            if(scanPage.checkIfElementisShown("ACCEPT & CONTINUE"))
            {
                scanPage.navigate("Accept & continue");
                if(scanPage.checkIfElementisShown("Next")) {
                    scanPage.navigate("Next");
                    scanPage.waitForElement(4000);
                }
                scanPage.navigate("No thanks");
            }

        }
        if(settings.deviceName.contains("ios11")) {
            synchronized(this.wait) {
                this.wait.wait(20000);
            }
        }
        WebPage webPage = new WebPage();
        this.assertScreen("nsplay-opened-history-element", this.settings.shortTimeout,20.0);
    }
}
