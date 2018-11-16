package sync.pages;

import functional.tests.core.enums.SwipeElementDirection;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import net.sf.saxon.Err;
import org.sikuli.script.*;
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

public class CodeEditorClass extends BasePage {
    public Screen s = new Screen();
    public SetupClass setupClass;

    public CodeEditorClass(SetupClass setupClass) throws InterruptedException {
        super();
        this.setupClass = setupClass;
        //old sikuli way
        //String currentPath = System.getProperty("user.dir");
        //ImagePath.add(currentPath+"/src/test/java/sync/pages/images.sikuli");
    }

    /**
     * Verify home page loaded.
     */
    public void navigate(String button) {
        this.find.byText(button).click();
        this.log.info("Navigate to " + button);
    }

    public void typeCode(String code) {
        this.typeCode(code, null);
    }

    public void typeCode(String code, String whereToType) {
        if (whereToType != null) {
            try {
                s.click(whereToType);
            } catch (FindFailed findFailed) {
                findFailed.printStackTrace();
            }
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (code.contains("{")) {
            s.type(code);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            s.type("}");
            s.type(Key.LEFT);
        } else {
            s.type(code);
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
        s.type("v", KeyModifier.CMD);
    }

    public void deleteAllCode()
    {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setupClass.driver.findElements(By.tagName("monaco-editor")).get(0).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s.type("a", KeyModifier.CMD);
        this.setupClass.wait(1000);
        s.type(Key.DELETE);
        this.setupClass.wait(1000);

    }

    public void typeXMLOrHTMLCode(boolean isValid, boolean shouldType) {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("js") || this.setupClass.typeOfProject.equals("tsc")) {
            if(shouldType) {
                this.typeCode("<Page loaded=\"pageLoaded\" class=\"page\" xmlns=\"http://www.nativescript.org/tns.xsd\">");
                s.type(Key.ENTER);
                this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\">");
                s.type(Key.ENTER);
                this.typeCode("</ActionBar>");
                s.type(Key.ENTER);
                this.typeCode("<ScrollView>");
                s.type(Key.ENTER);
                this.typeCode("<StackLayout class=\"home-panel\">");
                s.type(Key.ENTER);
                this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />");
                s.type(Key.ENTER);
                if (isValid) {
                    this.typeCode("</StackLayout>");
                }
                s.type(Key.ENTER);
                this.typeCode("</ScrollView>");
                s.type(Key.ENTER);
                this.typeCode("</Page>");
            }
            else
            {
                String code = "";
                if(isValid)
                {
                    code = "<Page loaded=\"pageLoaded\" class=\"page\" xmlns=\"http://www.nativescript.org/tns.xsd\">\n" +
                            "\n" +
                            "    <ActionBar title=\"Test\" class=\"action-bar\">\n" +
                            "    </ActionBar>\n" +
                            "    <ScrollView>\n" +
                            "        <StackLayout class=\"home-panel\">\n" +
                            "            <!--Add your page content here-->\n" +
                            "            <Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n" +
                            "        </StackLayout>\n" +
                            "    </ScrollView>\n" +
                            "</Page>";
                }
                else{
                    code = "<Page loaded=\"pageLoaded\" class=\"page\" xmlns=\"http://www.nativescript.org/tns.xsd\">\n" +
                            "\n" +
                            "    <ActionBar title=\"Test\" class=\"action-bar\">\n" +
                            "    </ActionBar>\n" +
                            "    <ScrollView>\n" +
                            "        <StackLayout class=\"home-panel\">\n" +
                            "            <!--Add your page content here-->\n" +
                            "            <Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n" +
                            "\n" +
                            "    </ScrollView>\n" +
                            "</Page>";
                }

                pasteText(code);
            }
        }
        if (this.setupClass.typeOfProject.equals("ng")) {
            if(shouldType) {
                this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\">");
                s.type(Key.ENTER);
                this.typeCode("</ActionBar>");
                s.type(Key.ENTER);
                this.typeCode("<ScrollView class=\"page\">");
                s.type(Key.ENTER);
                this.typeCode("<StackLayout class=\"home-panel\">");
                s.type(Key.ENTER);
                this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" ></Label>");
                s.type(Key.ENTER);
                if (isValid) {
                    this.typeCode("</StackLayout>");
                }
                s.type(Key.ENTER);
                this.typeCode("</ScrollView>");
                s.type(Key.ENTER);
            }
            else {
                String code = "";
                if(isValid)
                {
                    code = "<ActionBar title=\"Test\" class=\"action-bar\">\n" +
                            "</ActionBar>\n" +
                            "\n" +
                            "<GridLayout>\n" +
                            "    <ScrollView class=\"page\">\n" +
                            "        <StackLayout class=\"home-panel\">\n" +
                            "            <!--Add your page content here-->\n" +
                            "            <Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\"></Label>\n" +
                            "        </StackLayout>\n" +
                            "    </ScrollView>\n" +
                            "</GridLayout>";
                }
                else{
                    code = "<ActionBar title=\"Test\" class=\"action-bar\">\n" +
                            "</ActionBar>\n" +
                            "\n" +
                            "<GridLayout>\n" +
                            "    <ScrollView class=\"page\">\n" +
                            "        <StackLayout class=\"home-panel\">\n" +
                            "            <!--Add your page content here-->\n" +
                            "            <Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\"></Label>\n" +
                            "    </ScrollView>\n" +
                            "</GridLayout>";
                }

                pasteText(code);
            }
        }

        if (this.setupClass.typeOfProject.equals("vue")) {
            if(shouldType) {
                this.typeCode("<template>");
                this.typeCode(Key.ENTER);
                this.typeCode("<Page class=\"page\">");
                this.typeCode(Key.ENTER);
                this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\" />");
                this.typeCode(Key.ENTER);
                this.typeCode("<ScrollView>");
                this.typeCode(Key.ENTER);
                this.typeCode("<StackLayout class=\"home-panel\">");
                this.typeCode(Key.ENTER);
                this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />");
                this.typeCode(Key.ENTER);

                if (isValid) {
                    this.typeCode("</StackLayout>");
                    this.typeCode(Key.ENTER);
                } else {
                    this.typeCode("</StackLayoutggggggggggg");
                    this.typeCode(Key.ENTER);
                }
                this.typeCode(Key.ENTER);
                this.typeCode("</ScrollView>");
                this.typeCode(Key.ENTER);
                this.typeCode("</Page>");
                this.typeCode(Key.ENTER);
                this.typeCode("</template>");
                this.typeCode(Key.ENTER);

                this.typeCode("<script>");
                this.typeCode(Key.ENTER);

                this.typeCode("export default {");
                this.typeCode(Key.ENTER);
                this.typeCode("data () {");
                this.typeCode(Key.ENTER);
                this.typeCode("return {");
                this.typeCode(Key.ENTER);
                this.typeCode(Key.DOWN);
                this.typeCode(";");
                this.typeCode(Key.DOWN);
                this.typeCode(",");
                this.typeCode(Key.DOWN);
                this.typeCode(Key.DOWN);
                this.typeCode(Key.DOWN);
                this.typeCode(Key.ENTER);
                this.typeCode("</script>");
                this.typeCode(Key.ENTER);
                this.typeCode(Key.ENTER);
                this.typeCode("<style scoped>");
                this.typeCode(Key.ENTER);
                this.typeCode(".home-panel {");
                this.typeCode(Key.ENTER);
                this.typeCode("vertical-align: center;");
                this.typeCode(Key.ENTER);
                this.typeCode("font-size: 20;");
                this.typeCode(Key.ENTER);
                this.typeCode("margin: 15;");
                this.typeCode(Key.ENTER);
                this.typeCode(Key.DOWN);
                this.typeCode(Key.ENTER);
                this.typeCode(".description-label {");
                this.typeCode(Key.ENTER);
                this.typeCode("margin-bottom: 15;");
                this.typeCode(Key.DOWN);
                this.typeCode(Key.ENTER);
                this.typeCode("</style>");
                this.typeCode(Key.ENTER);
            }
            else{
                String code = "";
                if(isValid)
                {
                    code = "<template>\n" +
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
                else{
                    code = "<template>\n" +
                            "    <Page class=\"page\">\n" +
                            "        <ActionBar title=\"Test\" class=\"action-bar\" />\n" +
                            "        <ScrollView>\n" +
                            "            <StackLayout class=\"home-panel\">\n" +
                            "                <!--Add your page content here-->\n" +
                            "                <Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n" +
                            "                </StackLayoutggggggggggg \n" +
                            "                </ScrollView> \n" +
                            "                </Page> \n" +
                            "                </template>\n" +
                            "                    \n" +
                            "                    <script>\n" +
                            "                export default {\n" +
                            "                data() {\n" +
                            "                return {};\n" +
                            "                }\n" +
                            "                };\n" +
                            "                </script>\n" +
                            "\n" +
                            "                <style scoped>\n" +
                            "                    .home-panel {\n" +
                            "                        vertical-align: center;\n" +
                            "                        font-size: 20;\n" +
                            "                        margin: 15;\n" +
                            "                    }\n" +
                            "\n" +
                            "                    .description-label {\n" +
                            "                        margin-bottom: 15;\n" +
                            "                    }\n" +
                            "                </style>";
                }

                pasteText(code);
            }
        }
        this.setupClass.wait(2000);
    }

    public void typeCSSCode(boolean isValid, boolean shouldType) {

        this.deleteAllCode();
        if(shouldType) {
            if (isValid == false) {
                this.typeCode(".description-label");
                this.typeCode("{");
                s.type(Key.ENTER);
                this.typeCode("margin-bottom: 15;");
                s.type(Key.ENTER);
                this.typeCode("}");
                s.type(Key.ENTER);
            }
            s.type(Key.ENTER);
            this.typeCode("@import 'nativescript-theme-core/css/core.dark.css';");
            s.type(Key.ENTER);
            this.typeCode(".home-panel");
            this.typeCode("{");
            s.type(Key.ENTER);
            this.typeCode("vertical-align: center;");
            s.type(Key.ENTER);
            this.typeCode("font-size: 20;");
            s.type(Key.ENTER);
            this.typeCode("margin: 15;");
            s.type(Key.ENTER);
            s.type(Key.DOWN);
            s.type(Key.DOWN);
            s.type(Key.DOWN);
            s.type(Key.ENTER);
            this.typeCode(".description-label");
            this.typeCode("{");
            s.type(Key.ENTER);
            this.typeCode("margin-bottom: 15;");
            s.type(Key.ENTER);
        }
        else{
            String code = "";
            if(isValid)
            {
                code = "@import 'nativescript-theme-core/css/core.dark.css';\n" +
                        "\n" +
                        ".home-panel{\n" +
                        "    vertical-align: center;\n" +
                        "    font-size: 20;\n" +
                        "    margin: 15;\n" +
                        "}\n" +
                        ".description-label{\n" +
                        "    margin-bottom: 15;\n" +
                        "}";
            }
            else{
                code = "description-label{\n" +
                        "    margin-bottom: 15;\n" +
                        "}\n" +
                        "@import 'nativescript-theme-core/css/core.dark.css';\n" +
                        ".home-panel{\n" +
                        "    vertical-align: center;\n" +
                        "    font-size: 20;\n" +
                        "    margin: 15;\n" +
                        "}\n" +
                        ".description-label{\n" +
                        "    margin-bottom: 15;}\n" +
                        "}";
            }

            pasteText(code);
        }
        this.setupClass.wait(2000);
    }

    public void save() {
        this.save(null);
    }

    public void save(String waitForChanges) {
        s.type("s", KeyModifier.CMD);
        if(waitForChanges!=null) {
            try {
                this.setupClass.waitPreviewAppToLoad(30, waitForChanges);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
        this.setupClass.wait(4000);
        }
    }

    public void typeJSTSCode(boolean isValid, boolean shouldType) {
        this.deleteAllCode();
        if(this.setupClass.typeOfProject.equals("ng")) {
            if(shouldType) {
                this.typeCode("import {");
                this.typeCode("Component, OnInit");
                this.typeCode(Key.RIGHT);
                this.typeCode(" from \"@angular/core\";");
                this.typeCode(Key.ENTER);
                this.typeCode("@Component({");
                this.typeCode(Key.ENTER);
                this.typeCode("selector: \"Home\",");
                this.typeCode(Key.ENTER);
                this.typeCode("moduleId: module.id");
                this.typeCode(",");
                this.typeCode("");
                this.typeCode(Key.ENTER);
                this.typeCode("templateUrl: \"./home.component.html\",");
                this.typeCode(Key.ENTER);
                this.typeCode("styleUrls: ['./home.component.css']");
                this.typeCode(Key.ENTER);
                this.setupClass.s.type(Key.DOWN);
                this.setupClass.s.type(Key.DOWN);
                this.setupClass.s.type(Key.DOWN);
                this.typeCode(Key.ENTER);
                this.typeCode("export class HomeComponent implements OnInit {");
                this.typeCode(Key.ENTER);
                this.typeCode("constructor() {");
                this.typeCode(Key.ENTER);
                this.typeCode("console.log(\"log\");");
                this.typeCode(Key.ENTER);
                this.setupClass.s.type(Key.DOWN);
                this.typeCode(Key.ENTER);
                this.typeCode("ngOnInit(): void {");
                this.typeCode(Key.ENTER);
                this.setupClass.s.type(Key.DOWN);
                this.setupClass.s.type(Key.DOWN);
                this.typeCode(Key.ENTER);
            }
            else
            {
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
        }
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            if(shouldType) {
                this.typeCode("var frameModule = require(\"ui/frame\");");
                this.typeCode(Key.ENTER);
                this.typeCode("var HomeViewModel = require(\"./home-view-model\");");
                this.typeCode(Key.ENTER);
                this.typeCode("var homeViewModel = new HomeViewModel();");
                this.typeCode(Key.ENTER);
                this.typeCode("function pageLoaded(args) {");
                this.typeCode(Key.ENTER);
                this.typeCode(Key.ENTER);
                this.typeCode("var page = args.object;");
                this.typeCode(Key.ENTER);
                this.typeCode("page.bindingContext = homeViewModel;");
                this.typeCode(Key.ENTER);
                this.typeCode("console.log(\"log\");");
                this.typeCode(Key.ENTER);
                this.setupClass.s.type(Key.DOWN);
                this.setupClass.s.type(Key.DOWN);
                this.setupClass.s.type(Key.DOWN);
                this.setupClass.s.type(Key.ENTER);
                this.typeCode("exports.pageLoaded = pageLoaded;");
                this.typeCode(Key.ENTER);
            }
            else
            {
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
        }
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            if(shouldType) {
                this.typeCode("import {");
                this.typeCode(" Observable ");
                this.typeCode(Key.RIGHT);
                this.typeCode(" from 'data/observable';");
                this.typeCode(Key.ENTER);
                this.typeCode("export class HomeViewModel extends Observable {");
                this.typeCode(Key.ENTER);
                this.setupClass.s.type(Key.ENTER);
                this.typeCode("constructor() {");
                this.setupClass.s.type(Key.ENTER);
                this.typeCode("super();");
                this.typeCode(Key.ENTER);
                this.typeCode("console.log(\"log\");");
                this.typeCode(Key.ENTER);
            }
            else
            {
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
        }
        else if(this.setupClass.typeOfProject.equals("vue"))
        {
            if(shouldType) {
                this.typeCode("<template>");
                this.typeCode(Key.ENTER);
                this.typeCode("<Page class=\"page\">");
                this.typeCode(Key.ENTER);
                this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\" />");
                this.typeCode(Key.ENTER);
                this.typeCode("<ScrollView>");
                this.typeCode(Key.ENTER);
                this.typeCode("<StackLayout class=\"home-panel\">");
                this.typeCode(Key.ENTER);
                this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />");
                this.typeCode(Key.ENTER);
                this.typeCode("</StackLayout>");
                this.typeCode(Key.ENTER);
                this.typeCode("</ScrollView>");
                this.typeCode(Key.ENTER);
                this.typeCode("</Page>");
                this.typeCode(Key.ENTER);
                this.typeCode("</template>");
                this.typeCode(Key.ENTER);

                this.typeCode("<script>");
                this.typeCode(Key.ENTER);

                this.typeCode("export default {");
                this.typeCode(Key.ENTER);
                this.typeCode("mounted: function () {");
                this.typeCode(Key.ENTER);
                this.typeCode("console.log(\"log\");");
                this.typeCode(Key.ENTER);
                this.typeCode(Key.DOWN);
                this.typeCode(",");

                if (isValid == false && this.setupClass.typeOfProject.equals("vue")) {
                    this.typeCode("}");
                }

                this.typeCode("data () {");
                this.typeCode(Key.ENTER);
                this.typeCode("return {");
                this.typeCode(Key.ENTER);
                this.typeCode(Key.DOWN);
                this.typeCode(";");
                this.typeCode(Key.DOWN);
                this.typeCode(",");
                this.typeCode(Key.DOWN);
                this.typeCode(Key.DOWN);
                this.typeCode(Key.DOWN);
                this.typeCode(Key.ENTER);
                this.typeCode("</script>");
                this.typeCode(Key.ENTER);

                this.typeCode("<style scoped>");
                this.typeCode(Key.ENTER);
                this.typeCode(".home-panel {");
                this.typeCode(Key.ENTER);
                this.typeCode("vertical-align: center;");
                this.typeCode(Key.ENTER);
                this.typeCode("font-size: 20;");
                this.typeCode(Key.ENTER);
                this.typeCode("margin: 15;");
                this.typeCode(Key.ENTER);
                this.typeCode(Key.DOWN);
                this.typeCode(Key.ENTER);
                this.typeCode(".description-label {");
                this.typeCode(Key.ENTER);
                this.typeCode("margin-bottom: 15;");
                this.typeCode(Key.DOWN);
                this.typeCode(Key.ENTER);
                this.typeCode("</style>");
                this.typeCode(Key.ENTER);
            }
            else{
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

                pasteText(code);
            }
        }
        if(isValid==false && !this.setupClass.typeOfProject.equals("vue"))
        {
            this.typeCode("}");
        }
        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowError() {
        this.deleteAllCode();
        if(this.setupClass.typeOfProject.equals("ng")) {
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
        else if(this.setupClass.typeOfProject.equals("js"))
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
        else if(this.setupClass.typeOfProject.equals("tsc"))
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
        else if(this.setupClass.typeOfProject.equals("vue"))
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
        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowJavaError() {
        this.deleteAllCode();
        if(this.setupClass.typeOfProject.equals("ng")) {
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
        else if(this.setupClass.typeOfProject.equals("js"))
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
        else if(this.setupClass.typeOfProject.equals("tsc"))
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
        else if(this.setupClass.typeOfProject.equals("vue"))
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
        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowiOSError() {
        this.deleteAllCode();
        if(this.setupClass.typeOfProject.equals("ng")) {
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
        else if(this.setupClass.typeOfProject.equals("js"))
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
        else if(this.setupClass.typeOfProject.equals("tsc"))
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
        else if(this.setupClass.typeOfProject.equals("vue"))
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

        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowiOSCocoaError() {
        this.deleteAllCode();
        if(this.setupClass.typeOfProject.equals("ng")) {
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
        else if(this.setupClass.typeOfProject.equals("js"))
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
        else if(this.setupClass.typeOfProject.equals("tsc"))
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
        else if(this.setupClass.typeOfProject.equals("vue"))
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

        this.setupClass.wait(2000);
    }

    public void waitForElement(int time) throws InterruptedException {
        synchronized(this.wait) {
            this.wait.wait(time);
        }
    }

    public void scrollDown() {
        UIElement scroll = this.wait.waitForVisible(this.locators.findByTextLocator("DatePicker", true));
        scroll.scrollInElement(SwipeElementDirection.DOWN, 1);
        this.log.info("Scroll Down");
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

    public void assertImageIsOnScreen(String imageToFind, float similarity){
        try {
            this.setupClass.s.find(new Pattern(imageToFind).similar(similarity));
        } catch (FindFailed findFailed) {
            this.setupClass.getScreenShot(imageToFind+"_Not_Found");
            Assert.assertTrue(false, "Image "+imageToFind+" not found on screen!");
        }
        Assert.assertTrue(true, "Image "+imageToFind+" found on screen!");
    }

    public void assertImageIsOnScreen(String imageToFind){
        this.assertImageIsOnScreen(imageToFind, 0.7f);
    }

    public void assertDeviceTab(String deviceNameExpected, String modelExpected, String osVersionExpected, String previewAppVersionExpected, String runtimeVersionExpected, String componentVersionsExpected)
    {
            WebElement baseTable = null;
            if(setupClass.waitUntilWebElementIsPresentByClassName("device-name-td"))
            {
                baseTable = setupClass.driver.findElements(By.className("devices")).get(0);
            }
            else {
                Assert.assertTrue(false, "Devices tab could not be found!!!");
            }
            List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
            tableRows.get(0).findElements(By.tagName("td")).get(0).click();
            baseTable = setupClass.driver.findElement(By.className("devices"));
            tableRows = baseTable.findElements(By.tagName("tr"));
            String componentsVersion = tableRows.get(1).findElements(By.tagName("td")).get(0).getText();
            String deviceName = tableRows.get(0).findElements(By.tagName("td")).get(0).getText();
            String modelName = tableRows.get(0).findElements(By.tagName("td")).get(1).getText();
            String osVersionText = tableRows.get(0).findElements(By.tagName("td")).get(2).getText();
            String previewAppVersionText = tableRows.get(0).findElements(By.tagName("td")).get(3).getText();
            String runtimeVersionText = tableRows.get(0).findElements(By.tagName("td")).get(4).getText();

            Assert.assertEquals(componentsVersion, componentVersionsExpected,"components version is not correct!");
            Assert.assertEquals(deviceName, deviceNameExpected, "device name is not correct!");
            Assert.assertEquals(modelName, modelExpected, "model name is not correct!");
            Assert.assertTrue(osVersionText.contains(osVersionExpected), "Actual os version is "+osVersionText+" , expected os version is "+osVersionExpected);
            Assert.assertEquals(previewAppVersionText, previewAppVersionExpected, "preview app version is not correct!");
            Assert.assertEquals(runtimeVersionText, runtimeVersionExpected, "runtime version is not correct!");
    }

    public String getTextWithCopy() {
        String text = "";
        this.setupClass.wait(1000);
        s.type("c", KeyModifier.CMD);
        this.setupClass.wait(1000);
        try {
            text = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.setupClass.wait(1000);
        return text.trim();
    }

}
