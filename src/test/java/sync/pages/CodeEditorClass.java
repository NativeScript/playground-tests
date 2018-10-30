package sync.pages;

import functional.tests.core.enums.SwipeElementDirection;
import functional.tests.core.mobile.basepage.BasePage;
import functional.tests.core.mobile.element.UIElement;
import org.sikuli.script.*;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class CodeEditorClass extends BasePage {
    public Screen s = new Screen();
    public SetupClass setupClass;

    public CodeEditorClass(SetupClass setupClass) throws InterruptedException {
        super();
        this.setupClass = setupClass;
        String currentPath = System.getProperty("user.dir");
        ImagePath.add(currentPath + "/src/test/java/sync/pages/images.sikuli");
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
                this.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        s.type(code);
        this.setupClass.wait(1500);
    }

    public void deleteAllCode() {
        try {
            s.click(new Pattern("NativescriptLogo.png").targetOffset(330, 119));
            this.setupClass.wait(500);
            s.click(new Pattern("NativescriptLogo.png").targetOffset(330, 119));
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        this.setupClass.wait(1500);
        s.type("a", KeyModifier.CMD);
        this.setupClass.wait(500);
        s.type(Key.DELETE);
        this.setupClass.wait(1000);
    }

    public void typeXMLOrHTMLCode(boolean isValid) {
        if (this.setupClass.typeOfProject.equals("js") || this.setupClass.typeOfProject.equals("tsc")) {
            //fix after xml changes don't kill the livesync
//            this.deleteAllCode();
//            this.typeCode("<Page loaded=\"pageLoaded\" class=\"page\" xmlns=\"http://www.nativescript.org/tns.xsd\">");
//            s.type(Key.ENTER);
//            this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\">");
//            s.type(Key.ENTER);
//            this.typeCode("</ActionBar>");
//            s.type(Key.ENTER);
//            this.typeCode("<ScrollView>");
//            s.type(Key.ENTER);
//            this.typeCode("<StackLayout class=\"home-panel\">");
//            s.type(Key.ENTER);
//            this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />");
//            s.type(Key.ENTER);
//            if (isValid) {
//                this.typeCode("</StackLayout>");
//            }
//            s.type(Key.ENTER);
//            this.typeCode("</ScrollView>");
//            s.type(Key.ENTER);
//            this.typeCode("</Page>");
        }

        if (this.setupClass.typeOfProject.equals("ng")) {
            this.deleteAllCode();
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

        if (this.setupClass.typeOfProject.equals("vue")) {
            this.deleteAllCode();
            this.typeCode("const Vue = require(\"nativescript-vue\");\n");
            this.typeCode("new Vue({\n");
            this.typeCode("template: `\n");
            this.typeCode("<Page class=\"page\">\n");
            this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\" />\n");
            this.typeCode("<ScrollView>\n");
            this.typeCode("<StackLayout class=\"home-panel\">\n");
            this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n");
            if (isValid) {
                this.typeCode("</StackLayout>\n");
            } else {
                this.typeCode("</StackLayoutggggggggggg\n");
            }

            this.typeCode("</ScrollView>\n");
            this.typeCode("</Page>\n");
            this.typeCode("`,\n");
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(".$start();\n");
        }

        this.setupClass.wait(2000);
    }

    public void typeCSSCode(boolean isValid) {
        this.deleteAllCode();
        if (isValid == false) {
            this.typeCode(".description-label{");
            s.type(Key.ENTER);
            this.typeCode("margin-bottom: 15;");
            s.type(Key.ENTER);
            this.typeCode("}");
            s.type(Key.ENTER);
        }

        s.type(Key.ENTER);
        this.typeCode("@import 'nativescript-theme-core/css/core.dark.css';");
        s.type(Key.ENTER);
        this.typeCode(".home-panel{");
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
        this.typeCode(".description-label{");
        s.type(Key.ENTER);
        this.typeCode("margin-bottom: 15;");
        s.type(Key.ENTER);

        this.setupClass.wait(2000);
    }

    public void save() {
        this.save(null);
    }

    public void save(String waitForChanges) {
        s.type("s", KeyModifier.CMD);
        if (waitForChanges != null) {
            try {
                this.setupClass.waitPreviewAppToLoad(30, waitForChanges);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            this.setupClass.wait(4000);
        }
    }

    public void typeJSTSCode(boolean isValid) {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";\n");
            this.typeCode("@Component({\n");
            this.typeCode("selector: \"Home\",\n");
            this.typeCode("moduleId: module.id");
            this.typeCode(",");
            this.typeCode("\n");
            this.typeCode("templateUrl: \"./home.component.html\",\n");
            this.typeCode("styleUrls: ['./home.component.css']\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode("export class HomeComponent implements OnInit {\n");
            this.typeCode("constructor() {\n");
            this.typeCode("console.log(\"log\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode("ngOnInit(): void {\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.typeCode(Key.ENTER);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.typeCode("var frameModule = require(\"ui/frame\");\n");
            this.typeCode("var HomeViewModel = require(\"./home-view-model\");\n");
            this.typeCode("var homeViewModel = new HomeViewModel();\n");
            this.typeCode("function pageLoaded(args) {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("var page = args.object;\n");
            this.typeCode("page.bindingContext = homeViewModel;\n");
            this.typeCode("console.log(\"log\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("exports.pageLoaded = pageLoaded;\n");
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.typeCode("import { Observable } from 'data/observable';\n");
            this.typeCode("export class HomeViewModel extends Observable {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("constructor() {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("super();\n");
            this.typeCode("console.log(\"log\");\n");
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            this.deleteAllCode();
            this.typeCode("const Vue = require(\"nativescript-vue\");\n");

            this.typeCode("new Vue({\n");
            this.typeCode("template: `\n");
            this.typeCode("<Page class=\"page\">\n");
            this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\" />\n");
            this.typeCode("<ScrollView>\n");
            this.typeCode("<StackLayout class=\"home-panel\">\n");
            this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n");
            this.typeCode("</StackLayout>\n");
            this.typeCode("</ScrollView>\n");
            this.typeCode("</Page>\n");
            this.typeCode("`,\n");
            this.typeCode("mounted: function () {\n");
            this.typeCode("console.log(\"log\");\n");
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(".$start();\n");
        }

        if (isValid == false) {
            this.typeCode("}");
        }

        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowError() {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";\n");
            this.typeCode("@Component({\n");
            this.typeCode("selector: \"Home\",\n");
            this.typeCode("moduleId: module.id");
            this.typeCode(",");
            this.typeCode("\n");
            this.typeCode("templateUrl: \"./home.component.html\",\n");
            this.typeCode("styleUrls: ['./home.component.css']\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("export class HomeComponent implements OnInit {\n");
            this.typeCode("constructor() {\n");
            this.typeCode("throw new Error(\"Error\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("ngOnInit(): void {\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.typeCode("var frameModule = require(\"ui/frame\");\n");
            this.typeCode("var HomeViewModel = require(\"./home-view-model\");\n");
            this.typeCode("var homeViewModel = new HomeViewModel();\n");
            this.typeCode("function pageLoaded(args) {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("var page = args.object;\n");
            this.typeCode("page.bindingContext = homeViewModel;\n");
            this.typeCode("throw new Error(\"Error\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("exports.pageLoaded = pageLoaded;\n");
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.typeCode("import { Observable } from 'data/observable';\n");
            this.typeCode("export class HomeViewModel extends Observable {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("constructor() {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("super();\n");
            this.typeCode("throw new Error(\"Error\");\n");
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            this.deleteAllCode();
            this.typeCode("const Vue = require(\"nativescript-vue\");\n");

            this.typeCode("new Vue({\n");
            this.typeCode("template: `\n");
            this.typeCode("<Page class=\"page\">\n");
            this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\" />\n");
            this.typeCode("<ScrollView>\n");
            this.typeCode("<StackLayout class=\"home-panel\">\n");
            this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n");
            this.typeCode("</StackLayout>\n");
            this.typeCode("</ScrollView>\n");
            this.typeCode("</Page>\n");
            this.typeCode("`,\n");
            this.typeCode("mounted: function () {\n");
            this.typeCode("throw new Error(\"Error\");\n");
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(".$start();\n");
        }

        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowJavaError() {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";\n");
            this.typeCode("declare var java : any");
            this.typeCode(";\n");
            this.typeCode("@Component({\n");
            this.typeCode("selector: \"Home\",\n");
            this.typeCode("moduleId: module.id");
            this.typeCode(",");
            this.typeCode("\n");
            this.setupClass.wait(1000);
            this.typeCode("templateUrl: \"./home.component.html\",\n");
            this.typeCode("styleUrls: ['./home.component.css']\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode("export class HomeComponent implements OnInit {\n");
            this.typeCode("constructor() {\n");
            this.typeCode("java.lang.Integer.parseInt(\"sdklfjsd\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode("ngOnInit(): void {\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.typeCode("var frameModule = require(\"ui/frame\");\n");
            this.typeCode("var HomeViewModel = require(\"./home-view-model\");\n");
            this.typeCode("var homeViewModel = new HomeViewModel();\n");
            this.typeCode("function pageLoaded(args) {\n");
            this.typeCode(Key.ENTER);
            this.typeCode("var page = args.object;\n");
            this.typeCode("page.bindingContext = homeViewModel;\n");
            this.typeCode("java.lang.Integer.parseInt(\"sdklfjsd\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode("exports.pageLoaded = pageLoaded;\n");
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.typeCode("declare var java : any;\n");
            this.typeCode("import { Observable } from 'data/observable';\n");
            this.typeCode("export class HomeViewModel extends Observable {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("constructor() {\n");
            this.typeCode(Key.ENTER);
            this.typeCode("super();\n");
            this.typeCode("java.lang.Integer.parseInt(\"sdklfjsd\");\n");
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            this.deleteAllCode();
            this.typeCode("const Vue = require(\"nativescript-vue\");\n");

            this.typeCode("new Vue({\n");
            this.typeCode("template: `\n");
            this.typeCode("<Page class=\"page\">\n");
            this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\" />\n");
            this.typeCode("<ScrollView>\n");
            this.typeCode("<StackLayout class=\"home-panel\">\n");
            this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n");
            this.typeCode("</StackLayout>\n");
            this.typeCode("</ScrollView>\n");
            this.typeCode("</Page>\n");
            this.typeCode("`,\n");
            this.typeCode("mounted: function () {\n");
            this.typeCode("java.lang.Integer.parseInt(\"sdklfjsd\");\n");
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(".$start();\n");
        }

        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowiOSError() {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";\n");
            this.typeCode("declare var NSFileManager : any");
            this.typeCode(";\n");
            this.typeCode("@Component({\n");
            this.typeCode("selector: \"Home\",\n");
            this.typeCode("moduleId: module.id");
            this.typeCode(",");
            this.typeCode("\n");
            this.typeCode("templateUrl: \"./home.component.html\",\n");
            this.typeCode("styleUrls: ['./home.component.css']\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("export class HomeComponent implements OnInit {\n");
            this.typeCode("constructor() {\n");
            this.typeCode("var fileManager = NSFileManager.defaultManager;\n");
            this.typeCode("fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("ngOnInit(): void {\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.typeCode("var frameModule = require(\"ui/frame\");\n");
            this.typeCode("var HomeViewModel = require(\"./home-view-model\");\n");
            this.typeCode("var homeViewModel = new HomeViewModel();\n");
            this.typeCode("function pageLoaded(args) {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("var page = args.object;\n");
            this.typeCode("page.bindingContext = homeViewModel;\n");
            this.typeCode("var fileManager = NSFileManager.defaultManager;\n");
            this.typeCode("fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("exports.pageLoaded = pageLoaded;\n");
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.typeCode("declare var NSFileManager : any;\n");
            this.typeCode("import { Observable } from 'data/observable';\n");
            this.typeCode("export class HomeViewModel extends Observable {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("constructor() {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("super();\n");
            this.typeCode("var fileManager = NSFileManager.defaultManager;\n");
            this.typeCode("fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");\n");
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            this.deleteAllCode();
            this.typeCode("const Vue = require(\"nativescript-vue\");\n");

            this.typeCode("new Vue({\n");
            this.typeCode("template: `\n");
            this.typeCode("<Page class=\"page\">\n");
            this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\" />\n");
            this.typeCode("<ScrollView>\n");
            this.typeCode("<StackLayout class=\"home-panel\">\n");
            this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n");
            this.typeCode("</StackLayout>\n");
            this.typeCode("</ScrollView>\n");
            this.typeCode("</Page>\n");
            this.typeCode("`,\n");
            this.typeCode("mounted: function () {\n");
            this.typeCode("var fileManager = NSFileManager.defaultManager;\n");
            this.typeCode("fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");\n");
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(".$start();\n");
        }

        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowiOSCocoaError() {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";\n");
            this.typeCode("declare var NSArray : any");
            this.typeCode(";\n");
            this.typeCode("@Component({\n");
            this.typeCode("selector: \"Home\",\n");
            this.typeCode("moduleId: module.id");
            this.typeCode(",");
            this.typeCode("\n");
            this.typeCode("templateUrl: \"./home.component.html\",\n");
            this.typeCode("styleUrls: ['./home.component.css']\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("export class HomeComponent implements OnInit {\n");
            this.typeCode("constructor() {\n");
            this.typeCode("var arr = new NSArray();\n");
            this.typeCode("var o = arr.objectAtIndex(\"5\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("ngOnInit(): void {\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.typeCode("var frameModule = require(\"ui/frame\");\n");
            this.typeCode("var HomeViewModel = require(\"./home-view-model\");\n");
            this.typeCode("var homeViewModel = new HomeViewModel();\n");
            this.typeCode("function pageLoaded(args) {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("var page = args.object;\n");
            this.typeCode("page.bindingContext = homeViewModel;\n");
            this.typeCode("var arr = new NSArray();\n");
            this.typeCode("var o = arr.objectAtIndex(\"5\");\n");
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("exports.pageLoaded = pageLoaded;\n");
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.typeCode("declare var NSArray : any;\n");
            this.typeCode("import { Observable } from 'data/observable';\n");
            this.typeCode("export class HomeViewModel extends Observable {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("constructor() {\n");
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("super();\n");
            this.typeCode("var arr = new NSArray();\n");
            this.typeCode("var o = arr.objectAtIndex(\"5\");\n");
        } else if (this.setupClass.typeOfProject.equals("vue")) {
            this.deleteAllCode();
            this.typeCode("const Vue = require(\"nativescript-vue\");\n");

            this.typeCode("new Vue({\n");
            this.typeCode("template: `\n");
            this.typeCode("<Page class=\"page\">\n");
            this.typeCode("<ActionBar title=\"Test\" class=\"action-bar\" />\n");
            this.typeCode("<ScrollView>\n");
            this.typeCode("<StackLayout class=\"home-panel\">\n");
            this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />\n");
            this.typeCode("</StackLayout>\n");
            this.typeCode("</ScrollView>\n");
            this.typeCode("</Page>\n");
            this.typeCode("`,\n");
            this.typeCode("mounted: function () {\n");
            this.typeCode("var arr = new NSArray();\n");
            this.typeCode("var o = arr.objectAtIndex(\"5\");\n");
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode(Key.DOWN);
            this.typeCode(Key.DOWN);
            this.typeCode(".$start();\n");
        }

        this.setupClass.wait(2000);
    }

    public void waitForElement(int time) throws InterruptedException {
        synchronized (this.wait) {
            this.wait.wait(time);
        }
    }

    public void scrollDown() {
        UIElement scroll = this.wait.waitForVisible(this.locators.findByTextLocator("DatePicker", true));
        scroll.scrollInElement(SwipeElementDirection.DOWN, 1);
        this.log.info("Scroll Down");
    }

    public void openFile(Pattern fileToOpen) {
        try {
            this.setupClass.s.click(fileToOpen);
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
        this.setupClass.wait(3000);
    }

    public String getErrorsTextFromErrorTab() {
        String ErrorText = "";
        try {
            if (this.setupClass.browser.equals("Safari")) {
                setupClass.s.dragDrop(new Pattern("ErrorsSafari").similar(0.7f).targetOffset(-3, 16),
                        new Pattern("ErrorsSafari").similar(0.7f).targetOffset(16, 170));
            } else {
                setupClass.s.dragDrop(new Pattern("Errors").similar(0.75f).targetOffset(-3, 16),
                        new Pattern("Errors").similar(0.75f).targetOffset(16, 170));
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        this.setupClass.wait(3000);
        s.type("c", KeyModifier.CMD);
        this.setupClass.wait(1000);
        try {
            ErrorText = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ErrorText;
    }

    public String getLogsTextFromDeviceLogsTab() {
        String ErrorText = "";
        setupClass.wait(3000);
        try {
            if (this.setupClass.browser.equals("Safari")) {
                setupClass.s.click("devicelogsSafari");
            } else {
                setupClass.s.click("devicelogs");
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        setupClass.wait(3000);
        try {
            if (this.setupClass.browser.equals("Safari")) {
                setupClass.s.dragDrop(new Pattern("devicelogsSafari").targetOffset(-3, 16),
                        new Pattern("devicelogsSafari").targetOffset(16, 45));
            } else {
                setupClass.s.dragDrop(new Pattern("devicelogs").targetOffset(-3, 16),
                        new Pattern("devicelogs").targetOffset(16, 45));
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        this.setupClass.wait(3000);
        for (int i = 0; i < 70; i++) {
            s.type(Key.DOWN, KeyModifier.SHIFT);
        }

        this.setupClass.wait(3000);
        s.type("c", KeyModifier.CMD);
        this.setupClass.wait(1500);
        try {
            ErrorText = (String) Toolkit.getDefaultToolkit()
                    .getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ErrorText;
    }

    public void clearDeviceLogs() {
        try {
            if (this.setupClass.browser.equals("Safari")) {
                setupClass.s.click("devicelogsSafari");
            } else {
                setupClass.s.click("devicelogs");
            }
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        setupClass.wait(3000);
        try {
            setupClass.s.click("cleardevicelogs");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }

        setupClass.wait(3000);
    }

    public void assertImageIsOnScreen(String imageToFind, float similarity) {
        try {
            this.setupClass.s.find(new Pattern(imageToFind).similar(similarity));
        } catch (FindFailed findFailed) {
            this.setupClass.getScreenShot(imageToFind + "_Not_Found");
            Assert.assertTrue(false, "Image " + imageToFind + " not found on screen!");
        }

        Assert.assertTrue(true, "Image " + imageToFind + " found on screen!");
    }

    public void assertImageIsOnScreen(String imageToFind) {
        this.assertImageIsOnScreen(imageToFind, 0.7f);
    }

    public void assertDeviceTab(String deviceNameExpected, String modelExpected, String osVersionExpected, String previewAppVersionExpected, String runtimeVersionExpected, String componentVersionsExpected) {
        try {
            this.setupClass.s.click("devicestab");
            this.setupClass.wait(2000);
            this.setupClass.s.click(new Pattern("devicename.png").targetOffset(-29, 39));
            this.setupClass.wait(1000);
            this.setupClass.s.doubleClick(new Pattern("devicename.png").targetOffset(-43, 76));
            for (int i = 34; i > 0; i--) {
                this.setupClass.s.type(Key.DOWN, KeyModifier.SHIFT);
            }

            String componentsVersion = this.getTextWithCopy();
            if (this.setupClass.browser.equals("Safari")) {
                setupClass.s.click("ErrorsNotSelectedSafari");
            } else {
                setupClass.s.click("ErrorsNotSelected");
            }

            this.setupClass.wait(2000);
            this.setupClass.s.click("devicestab");
            this.setupClass.wait(2000);
            this.setupClass.s.dragDrop(new Pattern("devicename.png").targetOffset(8, 39), new Pattern("model.png").targetOffset(-25, 40));
            String deviceName = this.getTextWithCopy();
            this.setupClass.s.click("devicestab");
            if (settings.deviceType == settings.deviceType.Emulator) {
                this.setupClass.s.dragDrop(new Pattern("model.png").targetOffset(-25, 40), new Pattern("model.png").targetOffset(153, 39));
            } else {
                this.setupClass.s.dragDrop(new Pattern("model.png").targetOffset(-25, 40), new Pattern("model.png").targetOffset(82, 40));
            }

            String modelName = this.getTextWithCopy();
            this.setupClass.s.dragDrop(new Pattern("osversion.png").targetOffset(-35, 41), new Pattern("osversion.png").targetOffset(75, 41));
            String osVersionText = this.getTextWithCopy();
            this.setupClass.s.dragDrop(new Pattern("previewappversion.png").targetOffset(-61, 38), new Pattern("previewappversion.png").targetOffset(71, 38));
            String previewAppVersionText = this.getTextWithCopy();
            this.setupClass.s.dragDrop(new Pattern("runtimeversion.png").targetOffset(-52, 37), new Pattern("runtimeversion.png").targetOffset(85, 37));
            String runtimeVersionText = this.getTextWithCopy();
            Assert.assertEquals(componentsVersion, componentVersionsExpected, "components version is not correct!");
            Assert.assertEquals(deviceName, deviceNameExpected, "device name is not correct!");
            Assert.assertEquals(modelName, modelExpected, "model name is not correct!");
            Assert.assertTrue(osVersionText.contains(osVersionExpected), "Actual os version is " + osVersionText + " , expected os version is " + osVersionExpected);
            Assert.assertEquals(previewAppVersionText, previewAppVersionExpected, "preview app version is not correct!");
            Assert.assertEquals(runtimeVersionText, runtimeVersionExpected, "runtime version is not correct!");
        } catch (FindFailed findFailed) {
            findFailed.printStackTrace();
        }
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
