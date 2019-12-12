package sync.pages;

import functional.tests.core.enums.SwipeElementDirection;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.testng.Assert;
import java.util.List;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.awt.datatransfer.*;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
public class CodeEditorClass extends BasePage {
    public Robot s = new Robot();
    private SetupClass setupClass;

    public CodeEditorClass(SetupClass setupClass) throws AWTException {
        super();
        this.setupClass = setupClass;
    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String button) {
        find.byText(button).click();
        log.info("Navigate to " + button);
    }


    public void pressButton(Integer key) {
        pressButton(key, null);
    }
    public void pressButton(Integer key, boolean shouldWait) {
        pressButton(key, null, shouldWait);
    }

    public void pressButton(Integer key, Integer keyModifier) {
        pressButton(key, keyModifier, false);
    }

    public void pressButton(Integer key, Integer keyModifier, boolean shouldWait) {
        if (keyModifier != null)
        {
            s.keyPress(keyModifier);
        }
        s.keyPress(key);
        if(shouldWait)
        {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (keyModifier != null)
        {
            s.keyRelease(keyModifier);
        }
        s.keyRelease(key);
        if(shouldWait)
        {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void typeCode(String code) {
        if (code.contains("{")) {
            pasteText(code);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pressButton(KeyEvent.VK_BRACERIGHT);
            pressButton(KeyEvent.VK_BRACERIGHT);
            pressButton(KeyEvent.VK_LEFT);
        } else {
            pasteText(code);
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pasteText(String code){
        StringSelection stringSelection = new StringSelection(code);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        pressButton(KeyEvent.VK_V, KeyEvent.VK_META, true);
        setupClass.wait(1000);
    }

    public void deleteAllCode()
    {
        setupClass.wait(1000);
        setupClass.driver.findElements(By.tagName("monaco-editor")).get(0).click();
        setupClass.wait(1000);
        pressButton(KeyEvent.VK_A, KeyEvent.VK_META);
        setupClass.wait(1000);
        pressButton(KeyEvent.VK_DELETE);
        setupClass.wait(1000);

    }

    public void typeXMLOrHTMLCode(boolean isValid) {
        deleteAllCode();
        if (setupClass.typeOfProject.equals("js") || setupClass.typeOfProject.equals("tsc")) {
            String code = "";
            if (isValid) {
                code = "<Page loaded=\"pageLoaded\" xmlns=\"http://www.nativescript.org/tns.xsd\">\n" +
                        "\n" +
                        "    <ActionBar title=\"Test\">\n" +
                        "    </ActionBar>\n" +
                        "    <ScrollView>\n" +
                        "        <StackLayout class=\"home-panel\">\n" +
                        "            <!--Add your page content here-->\n" +
                        "            <Label text=\"Testing Label!\" />\n" +
                        "        </StackLayout>\n" +
                        "    </ScrollView>\n" +
                        "</Page>";
            } else {
                code = "<Page loaded=\"pageLoaded\" xmlns=\"http://www.nativescript.org/tns.xsd\">\n" +
                        "\n" +
                        "    <ActionBar title=\"Test\">\n" +
                        "    </ActionBar>\n" +
                        "    <ScrollView>\n" +
                        "        <StackLayout class=\"home-panel\">\n" +
                        "            <!--Add your page content here-->\n" +
                        "            <Label text=\"Testing Label!\" />\n" +
                        "    </ScrollView>\n" +
                        "</Page>";
            }

            pasteText(code);
            pressButton(KeyEvent.VK_ENTER, true);
        }
        if (setupClass.typeOfProject.equals("ng")) {


            String code = "";
            if (isValid) {
                code = "<ActionBar title=\"Test\">\n" +
                        "</ActionBar>\n" +
                        "\n" +
                        "<GridLayout>\n" +
                        "    <ScrollView>\n" +
                        "        <StackLayout class=\"home-panel\">\n" +
                        "            <!--Add your page content here-->\n" +
                        "            <Label text=\"Testing Label!\"></Label>\n" +
                        "        </StackLayout>\n" +
                        "    </ScrollView>\n" +
                        "</GridLayout>";
            } else {
                code = "<ActionBar title=\"Test\">\n" +
                        "</ActionBar>\n" +
                        "\n" +
                        "<GridLayout>\n" +
                        "    <ScrollView>\n" +
                        "        <StackLayout class=\"home-panel\">\n" +
                        "            <!--Add your page content here-->\n" +
                        "            <Label text=\"Testing Label!\"></Label>\n" +
                        "    </ScrollView>\n" +
                        "</GridLayout>";
            }

            pasteText(code);
        }

        if (setupClass.typeOfProject.equals("vue")) {
            String code = "";
            if (isValid) {
                code = "<template>\n" +
                        "    <Page>\n" +
                        "        <ActionBar title=\"Test\" />\n" +
                        "        <ScrollView>\n" +
                        "            <StackLayout class=\"home-panel\">\n" +
                        "                <!--Add your page content here-->\n" +
                        "                <Label text=\"Testing Label!\" />\n" +
                        "            </StackLayout>\n" +
                        "        </ScrollView>\n" +
                        "    </Page>\n" +
                        "</template>\n" +
                        "\n" +
                        "<script>\n" +
                        "    export default {\n" +
                        "        data() {\n" +
                        "            return {};\n" +
                        "        }\n" +
                        "    };\n" +
                        "</script>\n" +
                        "\n" +
                        "<style scoped>\n" +
                        "    .home-panel {\n" +
                        "        vertical-align: center;\n" +
                        "        font-size: 20;\n" +
                        "        margin: 15;\n" +
                        "    }\n" +
                        "\n" +
                        "    .description-label {\n" +
                        "        margin-bottom: 15;\n" +
                        "    }\n" +
                        "</style>";
            } else {
                code = "<template>\n" +
                        "    <Page>\n" +
                        "        <ActionBar title=\"Test\" />\n" +
                        "        <ScrollView>\n" +
                        "            <StackLayout class=\"home-panel\">\n" +
                        "                <!--Add your page content here-->\n" +
                        "                <Label text=\"Testing Label!\" />\n" +
                        "            </StackLayoutssssssssssssss>\n" +
                        "        </ScrollView>\n" +
                        "    </Page>\n" +
                        "</template>\n" +
                        "\n" +
                        "<script>\n" +
                        "    export default {\n" +
                        "        data() {\n" +
                        "            return {};\n" +
                        "        }\n" +
                        "    };\n" +
                        "</script>\n" +
                        "\n" +
                        "<style scoped>\n" +
                        "    .home-panel {\n" +
                        "        vertical-align: center;\n" +
                        "        font-size: 20;\n" +
                        "        margin: 15;\n" +
                        "    }\n" +
                        "\n" +
                        "    .description-label {\n" +
                        "        margin-bottom: 15;\n" +
                        "    }\n" +
                        "</style>";
            }

            pasteText(code);

        }
        setupClass.wait(2000);
    }

    public void typeCSSCode(boolean isValid) {

        deleteAllCode();
        String code = "";
        if (isValid) {
            code = "@import '~@nativescript/theme/css/core.css';\n" +
                    "@import '~@nativescript/theme/css/blue.css';\n" +
                    "\n" +
                    ".btn {\n" +
                    "    font-size: 18;\n" +
                    "}\n" +
                    "\n" +
                    ".home-panel{\n" +
                    "    vertical-align: center;\n" +
                    "    font-size: 20;\n" +
                    "    margin: 15;\n" +
                    "}\n" +
                    "\n" +
                    ".description-label{\n" +
                    "    margin-bottom: 15;\n" +
                    "}\n";
        } else {
            code = ".btn {\n" +
                    "    font-size: 18;\n" +
                    "}\n" +
                    "\n" +
                    ".home-panel{\n" +
                    "    vertical-align: center;\n" +
                    "    font-size: 20;\n" +
                    "    margin: 15;\n" +
                    "}\n" +
                    "\n" +
                    ".description-label{\n" +
                    "    margin-bottom: 15;\n" +
                    "}}\n" +
                    "\n" +
                    "@import '~@nativescript/theme/css/core.css';\n" +
                    "@import '~@nativescript/theme/css/blue.css';\n";
        }

        pasteText(code);

        setupClass.wait(2000);
    }

    public void save() {
        save(null);
    }

    public void save(String waitForChanges) {
        setupClass.wait(1500);
        pressButton(KeyEvent.VK_S, KeyEvent.VK_META);

        if(waitForChanges!=null) {
                setupClass.waitPreviewAppToLoad(30, waitForChanges);
        }
        else {
            setupClass.wait(5000);
        }
    }

    public void typeJSTSCode(boolean isValid) {
        deleteAllCode();
        if(setupClass.typeOfProject.equals("ng")) {
            String code = "import { Component, OnInit } from \"@angular/core\";\n" +
                    "\n" +
                    "@Component({\n" +
                    "    selector: \"Home\",\n" +
                    "    moduleId: module.id,\n" +
                    "    templateUrl: \"./home.component.html\",\n" +
                    "    styleUrls: ['./home.component.css']\n" +
                    "})\n" +
                    "export class HomeComponent implements OnInit {\n" +
                    "\n" +
                    "    constructor() {\n" +
                    "        console.log(\"log\");\n" +
                    "    }\n" +
                    "\n" +
                    "    ngOnInit(): void {\n" +
                    "    }\n" +
                    "}\n";

            pasteText(code);

        }
        else if(setupClass.typeOfProject.equals("js")) {

            String code = "var frameModule = require(\"tns-core-modules/ui/frame\");\n" +
                    "var HomeViewModel = require(\"./home-view-model\");\n" +
                    "\n" +
                    "var homeViewModel = new HomeViewModel();\n" +
                    "\n" +
                    "function pageLoaded(args) {\n" +
                    "  var page = args.object;\n" +
                    "  page.bindingContext = homeViewModel;\n" +
                    "  console.log(\"log\");\n" +
                    "}\n" +
                    "exports.pageLoaded = pageLoaded;\n";

            pasteText(code);

        }
        else if(setupClass.typeOfProject.equals("tsc")) {
            String code = "import { Observable } from 'tns-core-modules/data/observable';\n" +
                    "\n" +
                    "export class HomeViewModel extends Observable {\n" +
                    "    constructor() {\n" +
                    "        super();\n" +
                    "        console.log(\"log\");\n" +
                    "    }\n" +
                    "}";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("vue")) {
            String code = null;
            if (isValid) {
                code = "<template>\n" +
                        "    <Page>\n" +
                        "        <ActionBar title=\"Test\" />\n" +
                        "        <ScrollView>\n" +
                        "            <StackLayout class=\"home-panel\">\n" +
                        "                <!--Add your page content here-->\n" +
                        "                <Label text=\"Testing Label!\" />\n" +
                        "            </StackLayout>\n" +
                        "        </ScrollView>\n" +
                        "    </Page>\n" +
                        "</template>\n" +
                        "\n" +
                        "<script>\n" +
                        "    export default {\n" +
                        "        mounted: function() {\n" +
                        "            console.log(\"log\");\n" +
                        "        },\n" +
                        "        data() {\n" +
                        "            return {};\n" +
                        "        },\n" +
                        "    }\n" +
                        "</script>\n" +
                        "\n" +
                        "<style scoped>\n" +
                        "    .home-panel {\n" +
                        "        vertical-align: center;\n" +
                        "        font-size: 20;\n" +
                        "        margin: 15;\n" +
                        "    }\n" +
                        "\n" +
                        "    .description-label {\n" +
                        "        margin-bottom: 15;\n" +
                        "    }\n" +
                        "</style>";
            } else {
                code = "<template>\n" +
                        "    <Page>\n" +
                        "        <ActionBar title=\"Test\" />\n" +
                        "        <ScrollView>\n" +
                        "            <StackLayout class=\"home-panel\">\n" +
                        "                <!--Add your page content here-->\n" +
                        "                <Label text=\"Testing Label!\" />\n" +
                        "            </StackLayout>\n" +
                        "        </ScrollView>\n" +
                        "    </Page>\n" +
                        "</template>\n" +
                        "\n" +
                        "<script>\n" +
                        "    export default {\n" +
                        "        mounted: function() {\n" +
                        "            console.log(\"log\");\n" +
                        "        },}\n" +
                        "        data() {\n" +
                        "            return {};\n" +
                        "        },\n" +
                        "    }\n" +
                        "</script>\n" +
                        "\n" +
                        "<style scoped>\n" +
                        "    .home-panel {\n" +
                        "        vertical-align: center;\n" +
                        "        font-size: 20;\n" +
                        "        margin: 15;\n" +
                        "    }\n" +
                        "\n" +
                        "    .description-label {\n" +
                        "        margin-bottom: 15;\n" +
                        "    }\n" +
                        "</style>";
            }
            pasteText(code);

        }
        if(isValid==false && !setupClass.typeOfProject.equals("vue"))
        {
            typeCode("}");
        }
        setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowError() {
        deleteAllCode();
        if(setupClass.typeOfProject.equals("ng")) {
            String code = "import { Component, OnInit } from \"@angular/core\";\n" +
                    "\n" +
                    "@Component({\n" +
                    "    selector: \"Home\",\n" +
                    "    moduleId: module.id,\n" +
                    "    templateUrl: \"./home.component.html\",\n" +
                    "    styleUrls: ['./home.component.css']\n" +
                    "})\n" +
                    "export class HomeComponent implements OnInit {\n" +
                    "\n" +
                    "    constructor() {\n" +
                    "        throw new Error(\"Error\");\n" +
                    "    }\n" +
                    "\n" +
                    "    ngOnInit(): void {\n" +
                    "    }\n" +
                    "}\n";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("js"))
        {
            String code = "var frameModule = require(\"tns-core-modules/ui/frame\");\n" +
                    "var HomeViewModel = require(\"./home-view-model\");\n" +
                    "\n" +
                    "var homeViewModel = new HomeViewModel();\n" +
                    "\n" +
                    "function pageLoaded(args) {\n" +
                    "  var page = args.object;\n" +
                    "  page.bindingContext = homeViewModel;\n" +
                    "  throw new Error(\"Error\");\n" +
                    "}\n" +
                    "exports.pageLoaded = pageLoaded;\n";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("tsc"))
        {
            String code = "import { Observable } from 'tns-core-modules/data/observable';\n" +
                    "\n" +
                    "export class HomeViewModel extends Observable {\n" +
                    "    constructor() {\n" +
                    "        super();\n" +
                    "        throw new Error(\"Error\");\n" +
                    "    }\n" +
                    "}";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("vue"))
        {
            String code = "<template>\n" +
                    "    <Page class=\"page\">\n" +
                    "        <ActionBar title=\"Home\" class=\"action-bar\" />\n" +
                    "        <ScrollView>\n" +
                    "            <StackLayout class=\"home-panel\">\n" +
                    "                <!--Add your page content here-->\n" +
                    "                <Label textWrap=\"true\" text=\"Play with NativeScript!\" class=\"h2 description-label\" />\n" +
                    "                <Label textWrap=\"true\" text=\"Write code in the editor or drag and drop components to build a NativeScript mobile application.\"\n" +
                    "                    class=\"h2 description-label\" />\n" +
                    "                <Label textWrap=\"true\" text=\"Scan the QR code with your mobile device and watch the changes sync live while you play with the code.\"\n" +
                    "                    class=\"h2 description-label\" />\n" +
                    "            </StackLayout>\n" +
                    "        </ScrollView>\n" +
                    "    </Page>\n" +
                    "</template>\n" +
                    "\n" +
                    "<script>\n" +
                    "    export default {\n" +
                    "        mounted: function() {\n" +
                    "            throw new Error(\"Error\");\n" +
                    "        },\n" +
                    "        data() {\n" +
                    "            return {};\n" +
                    "        },\n" +
                    "    }\n" +
                    "</script>\n" +
                    "\n" +
                    "<style scoped>\n" +
                    "    .home-panel {\n" +
                    "        vertical-align: center;\n" +
                    "        font-size: 20;\n" +
                    "        margin: 15;\n" +
                    "    }\n" +
                    "\n" +
                    "    .description-label {\n" +
                    "        margin-bottom: 15;\n" +
                    "    }\n" +
                    "</style>";

            pasteText(code);

        }
        setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowJavaError() {
        deleteAllCode();
        if(setupClass.typeOfProject.equals("ng")) {
            String code = "import { Component, OnInit } from \"@angular/core\";\n" +
                    "declare var java : any;\n" +
                    "\n" +
                    "@Component({\n" +
                    "    selector: \"Home\",\n" +
                    "    moduleId: module.id,\n" +
                    "    templateUrl: \"./home.component.html\",\n" +
                    "    styleUrls: ['./home.component.css']\n" +
                    "})\n" +
                    "export class HomeComponent implements OnInit {\n" +
                    "\n" +
                    "    constructor() {\n" +
                    "        java.lang.Integer.parseInt(\"sdklfjsd\");\n" +
                    "    }\n" +
                    "\n" +
                    "    ngOnInit(): void {\n" +
                    "    }\n" +
                    "}\n";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("js"))
        {
            String code = "var frameModule = require(\"tns-core-modules/ui/frame\");\n" +
                    "var HomeViewModel = require(\"./home-view-model\");\n" +
                    "\n" +
                    "var homeViewModel = new HomeViewModel();\n" +
                    "\n" +
                    "function pageLoaded(args) {\n" +
                    "  var page = args.object;\n" +
                    "  page.bindingContext = homeViewModel;\n" +
                    "  java.lang.Integer.parseInt(\"sdklfjsd\");\n" +
                    "}\n" +
                    "exports.pageLoaded = pageLoaded;\n";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("tsc"))
        {
            String code = "declare var java : any;\n" +
                    "import { Observable } from 'tns-core-modules/data/observable';\n" +
                    "\n" +
                    "export class HomeViewModel extends Observable {\n" +
                    "    constructor() {\n" +
                    "        super();\n" +
                    "        java.lang.Integer.parseInt(\"sdklfjsd\");\n" +
                    "    }\n" +
                    "}";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("vue"))
        {
            String code = "<template>\n" +
                    "    <Page class=\"page\">\n" +
                    "        <ActionBar title=\"Test\" class=\"action-bar\" />\n" +
                    "        <ScrollView>\n" +
                    "            <StackLayout class=\"home-panel\">\n" +
                    "                <!--Add your page content here-->\n" +
                    "                <Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n" +
                    "            </StackLayout>\n" +
                    "        </ScrollView>\n" +
                    "    </Page>\n" +
                    "</template>\n" +
                    "\n" +
                    "<script>\n" +
                    "    export default {\n" +
                    "        mounted: function() {\n" +
                    "            java.lang.Integer.parseInt(\"sdklfjsd\");\n" +
                    "        },\n" +
                    "        data() {\n" +
                    "            return {};\n" +
                    "        },\n" +
                    "    }\n" +
                    "</script>\n" +
                    "\n" +
                    "<style scoped>\n" +
                    "    .home-panel {\n" +
                    "        vertical-align: center;\n" +
                    "        font-size: 20;\n" +
                    "        margin: 15;\n" +
                    "    }\n" +
                    "\n" +
                    "    .description-label {\n" +
                    "        margin-bottom: 15;\n" +
                    "    }\n" +
                    "</style>";

            pasteText(code);

        }
        setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowiOSError() {
        deleteAllCode();
        if(setupClass.typeOfProject.equals("ng")) {
            String code = "import { Component, OnInit } from \"@angular/core\";\n" +
                    "declare var NSFileManager : any;\n" +
                    "\n" +
                    "@Component({\n" +
                    "    selector: \"Home\",\n" +
                    "    moduleId: module.id,\n" +
                    "    templateUrl: \"./home.component.html\",\n" +
                    "    styleUrls: ['./home.component.css']\n" +
                    "})\n" +
                    "export class HomeComponent implements OnInit {\n" +
                    "\n" +
                    "    constructor() {\n" +
                    "        var fileManager = NSFileManager.defaultManager;\n" +
                    "        fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");\n" +
                    "    }\n" +
                    "\n" +
                    "    ngOnInit(): void {\n" +
                    "    }\n" +
                    "}\n";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("js"))
        {
            String code = "var frameModule = require(\"tns-core-modules/ui/frame\");\n" +
                    "var HomeViewModel = require(\"./home-view-model\");\n" +
                    "\n" +
                    "var homeViewModel = new HomeViewModel();\n" +
                    "\n" +
                    "function pageLoaded(args) {\n" +
                    "  var page = args.object;\n" +
                    "  page.bindingContext = homeViewModel;\n" +
                    "  var fileManager = NSFileManager.defaultManager;\n" +
                    "  fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");\n" +
                    "}\n" +
                    "exports.pageLoaded = pageLoaded;\n";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("tsc"))
        {
            String code = "declare var NSFileManager : any;\n" +
                    "import { Observable } from 'tns-core-modules/data/observable';\n" +
                    "\n" +
                    "export class HomeViewModel extends Observable {\n" +
                    "    constructor() {\n" +
                    "        super();\n" +
                    "  		 var fileManager = NSFileManager.defaultManager;\n" +
                    "  		 fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");\n" +
                    "    }\n" +
                    "}";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("vue"))
        {
            String code = "<template>\n" +
                    "    <Page class=\"page\">\n" +
                    "        <ActionBar title=\"Test\" class=\"action-bar\" />\n" +
                    "        <ScrollView>\n" +
                    "            <StackLayout class=\"home-panel\">\n" +
                    "                <!--Add your page content here-->\n" +
                    "                <Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n" +
                    "            </StackLayout>\n" +
                    "        </ScrollView>\n" +
                    "    </Page>\n" +
                    "</template>\n" +
                    "\n" +
                    "<script>\n" +
                    "    export default {\n" +
                    "        mounted: function() {\n" +
                    "  			var fileManager = NSFileManager.defaultManager;\n" +
                    "  			fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");\n" +
                    "        },\n" +
                    "        data() {\n" +
                    "            return {};\n" +
                    "        },\n" +
                    "    }\n" +
                    "</script>\n" +
                    "\n" +
                    "<style scoped>\n" +
                    "    .home-panel {\n" +
                    "        vertical-align: center;\n" +
                    "        font-size: 20;\n" +
                    "        margin: 15;\n" +
                    "    }\n" +
                    "\n" +
                    "    .description-label {\n" +
                    "        margin-bottom: 15;\n" +
                    "    }\n" +
                    "</style>";

            pasteText(code);

        }

        setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowiOSCocoaError() {
        deleteAllCode();
        if(setupClass.typeOfProject.equals("ng")) {
            String code = "import { Component, OnInit } from \"@angular/core\";\n" +
                    "declare var NSArray : any;\n" +
                    "\n" +
                    "@Component({\n" +
                    "    selector: \"Home\",\n" +
                    "    moduleId: module.id,\n" +
                    "    templateUrl: \"./home.component.html\",\n" +
                    "    styleUrls: ['./home.component.css']\n" +
                    "})\n" +
                    "export class HomeComponent implements OnInit {\n" +
                    "\n" +
                    "    constructor() {\n" +
                    "        var arr = new NSArray();\n" +
                    "        var o = arr.objectAtIndex(\"5\");\n" +
                    "    }\n" +
                    "\n" +
                    "    ngOnInit(): void {\n" +
                    "    }\n" +
                    "}\n";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("js"))
        {
            String code = "var frameModule = require(\"tns-core-modules/ui/frame\");\n" +
                    "var HomeViewModel = require(\"./home-view-model\");\n" +
                    "\n" +
                    "var homeViewModel = new HomeViewModel();\n" +
                    "\n" +
                    "function pageLoaded(args) {\n" +
                    "  var page = args.object;\n" +
                    "  page.bindingContext = homeViewModel;\n" +
                    "  var arr = new NSArray();\n" +
                    "  var o = arr.objectAtIndex(\"5\");\n" +
                    "}\n" +
                    "exports.pageLoaded = pageLoaded;\n";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("tsc"))
        {
            String code = "declare var NSArray : any;\n" +
                    "import { Observable } from 'tns-core-modules/data/observable';\n" +
                    "\n" +
                    "export class HomeViewModel extends Observable {\n" +
                    "    constructor() {\n" +
                    "        super();\n" +
                    "        var arr = new NSArray();\n" +
                    "        var o = arr.objectAtIndex(\"5\");\n" +
                    "    }\n" +
                    "}";

            pasteText(code);
        }
        else if(setupClass.typeOfProject.equals("vue"))
        {
            String code = "<template>\n" +
                    "    <Page class=\"page\">\n" +
                    "        <ActionBar title=\"Test\" class=\"action-bar\" />\n" +
                    "        <ScrollView>\n" +
                    "            <StackLayout class=\"home-panel\">\n" +
                    "                <!--Add your page content here-->\n" +
                    "                <Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n" +
                    "            </StackLayout>\n" +
                    "        </ScrollView>\n" +
                    "    </Page>\n" +
                    "</template>\n" +
                    "\n" +
                    "<script>\n" +
                    "    export default {\n" +
                    "        mounted: function() {\n" +
                    "        	var arr = new NSArray();\n" +
                    "        	var o = arr.objectAtIndex(\"5\");\n" +
                    "        },\n" +
                    "        data() {\n" +
                    "            return {};\n" +
                    "        },\n" +
                    "    }\n" +
                    "</script>\n" +
                    "\n" +
                    "<style scoped>\n" +
                    "    .home-panel {\n" +
                    "        vertical-align: center;\n" +
                    "        font-size: 20;\n" +
                    "        margin: 15;\n" +
                    "    }\n" +
                    "\n" +
                    "    .description-label {\n" +
                    "        margin-bottom: 15;\n" +
                    "    }\n" +
                    "</style>";

            pasteText(code);

        }

        setupClass.wait(2000);
    }

    public void waitForElement(int time) throws InterruptedException {
        synchronized(wait) {
            wait.wait(time);
        }
    }

    public void scrollDown() {
        UIElement scroll = wait.waitForVisible(locators.findByTextLocator("DatePicker", true));
        scroll.scrollInElement(SwipeElementDirection.DOWN, 1);
        log.info("Scroll Down");
    }

    public void openFile(String fileToOpen){
        setupClass.driver.findElements(By.xpath("//span[contains(.,'" + fileToOpen + "')]")).get(0).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getErrorsTextFromErrorTab(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return setupClass.driver.findElements(By.className("errors")).get(0).getText();
    }

    public String getLogsTextFromDeviceLogsTab(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setupClass.driver.findElements(By.xpath("//span[contains(.,'Device Logs')]")).get(0).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return setupClass.driver.findElements(By.className("logs")).get(0).getText();
    }

    public void clearDeviceLogs()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setupClass.driver.findElements(By.xpath("//span[contains(.,'Device Logs')]")).get(0).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setupClass.driver.findElements(By.xpath("//*[@title=\"Clear logs\"]")).get(0).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void assertDeviceTab(String deviceNameExpected, String modelExpected, String osVersionExpected, String previewAppVersionExpected, String runtimeVersionExpected, String componentVersionsExpected)
    {
        WebElement baseTable = null;
        if(setupClass.waitUntilWebElementIsPresentByClassName("device-name-td"))
        {
            baseTable = setupClass.driver.findElements(By.className("devices")).get(0);
        }
        else {
            setupClass.driver.findElements(By.xpath("//span[contains(.,'Devices')]")).get(0).click();
            if(setupClass.waitUntilWebElementIsPresentByClassName("device-name-td"))
            {
                baseTable = setupClass.driver.findElements(By.className("devices")).get(0);
            }
            else{
                Assert.assertTrue(false, "Devices tab could not be found!!!");
            }
        }
        List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
        tableRows.get(0).findElements(By.tagName("td")).get(0).click();
        setupClass.wait(2000);
        baseTable = setupClass.driver.findElement(By.className("devices"));
        tableRows = baseTable.findElements(By.tagName("tr"));
        String componentsVersion = tableRows.get(1).findElements(By.tagName("td")).get(0).getText();
        String deviceName = tableRows.get(0).findElements(By.tagName("td")).get(0).getText();
        String modelName = tableRows.get(0).findElements(By.tagName("td")).get(1).getText();
        String osVersionText = tableRows.get(0).findElements(By.tagName("td")).get(2).getText();
        String previewAppVersionText = tableRows.get(0).findElements(By.tagName("td")).get(3).getText();
        String runtimeVersionText = tableRows.get(0).findElements(By.tagName("td")).get(4).getText();
        if (!componentsVersion.contains("-next-")&&!componentsVersion.contains("-rc-")) {
            Assert.assertEquals(componentsVersion, componentVersionsExpected, "components version is not correct!");
            Assert.assertEquals(previewAppVersionText, previewAppVersionExpected, "preview app version is not correct!");
            Assert.assertEquals(runtimeVersionText, runtimeVersionExpected, "runtime version is not correct!");
        }
        Assert.assertEquals(deviceName, deviceNameExpected, "device name is not correct!");
        Assert.assertEquals(modelName, modelExpected, "model name is not correct!");
        Assert.assertTrue(osVersionText.contains(osVersionExpected), "Actual os version is "+osVersionText+" , expected os version is "+osVersionExpected);

    }

    public String getTextWithCopy() {
        String text = "";
        setupClass.wait(1000);
        pressButton(KeyEvent.VK_C, KeyEvent.VK_META);
        setupClass.wait(1000);
        try {
            text = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        setupClass.wait(1000);
        return text.trim();
    }

}
