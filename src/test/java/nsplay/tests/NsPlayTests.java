package nsplay.tests;

import functional.tests.core.enums.PlatformType;
import functional.tests.core.mobile.basetest.MobileTest;
import org.testng.annotations.BeforeTest;
import nsplay.pages.QRPage;
import nsplay.pages.ScanPage;
import nsplay.pages.WebPage;
import nsplay.pages.InfoPage;
import org.testng.annotations.Test;
import org.testng.SkipException;


public class NsPlayTests extends MobileTest {
    @BeforeTest
    public void beforeTest() {
        //this.settings.
        //if(this.settings.this.settings.platformName)
            //throw new SkipException("Skip test because platform is not supported for the test");
    }

    @Test(description = "Verify scan page looks OK.", groups = {"android", "ios"})
    public void test_01_scan_page_looks_ok() throws Exception {
        ScanPage scanPage = new ScanPage();
        this.assertScreen("nsplay-home-view", this.settings.shortTimeout);
    }

    @Test(description = "Verify details page looks OK.", groups = {"android", "ios"})
    public void test_02_QR_page_looks_ok() throws Exception {
        ScanPage scanPage = new ScanPage();
        scanPage.navigate("Scan QR code");
        if(settings.deviceType == settings.deviceType.Simulator)
        {
            this.assertScreen("nsplay-home-view", this.settings.shortTimeout);

        }
        else if(settings.deviceType == settings.deviceType.Emulator)
        {
            if(scanPage.checkIfElementisShown("Allow")) {
                scanPage.navigate("Allow");
            }
            QRPage detailsPage = new QRPage();
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
            this.assertScreen("nsplay-info-view-scrolled-emulator", this.settings.shortTimeout);
        }

    }

    @Test(description = "Verify web nativescript page looks OK.", groups = { "iOS"})
    public void test_04_WebNativescript_page_looks_ok() throws Exception {
        ScanPage scanPage = new ScanPage();
        scanPage.navigate("https://www.nativescript.org");
        if(settings.deviceName.contains("Google"))
        {
            scanPage.navigate("Chrome");
            scanPage.navigate("Just once");
            scanPage.waitForElement();
            if(scanPage.checkIfElementisShown("ACCEPT & CONTINUE"))
            {
                scanPage.navigate("Accept & continue");
                scanPage.navigate("No thanks");
            }

        }
        WebPage webPage = new WebPage();
        this.assertScreen("nsplay-opened-history-element", this.settings.shortTimeout,20.0);
    }
}
