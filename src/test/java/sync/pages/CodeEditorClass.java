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
        if(code.contains("{"))
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        s.type(code);
        if(code.contains("{"))
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteAllCode()
    {
        this.setupClass.driver.findElements(By.tagName("monaco-editor")).get(0).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        s.type("a", KeyModifier.CMD);
        this.setupClass.wait(500);
        s.type(Key.DELETE);
        this.setupClass.wait(1000);

    }

    public void typeXMLOrHTMLCode(boolean isValid) {
        if (this.setupClass.typeOfProject.equals("js") || this.setupClass.typeOfProject.equals("tsc")) {
            this.deleteAllCode();
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
            }
            else
            {
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
        this.setupClass.wait(2000);
    }

    public void typeCSSCode(boolean isValid) {
        this.deleteAllCode();
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

    public void typeJSTSCode(boolean isValid) {
        this.deleteAllCode();
        if(this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";\n");
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
        else if(this.setupClass.typeOfProject.equals("js"))
        {
            this.typeCode("var frameModule = require(\"ui/frame\");");
            this.typeCode(Key.ENTER);
            this.typeCode("var HomeViewModel = require(\"./home-view-model\");");
            this.typeCode(Key.ENTER);
            this.typeCode("var homeViewModel = new HomeViewModel();");
            this.typeCode(Key.ENTER);
            this.typeCode("function pageLoaded(args) {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
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
        else if(this.setupClass.typeOfProject.equals("tsc"))
        {
            this.typeCode("import { Observable } from 'data/observable';");
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
        else if(this.setupClass.typeOfProject.equals("vue"))
        {
            this.deleteAllCode();
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

            if(isValid==false && this.setupClass.typeOfProject.equals("vue"))
            {
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
        if(isValid==false && !this.setupClass.typeOfProject.equals("vue"))
        {
            this.typeCode("}");
        }
        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowError() {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";");
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
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("export class HomeComponent implements OnInit {");
            this.typeCode(Key.ENTER);
            this.typeCode("constructor() {");
            this.typeCode(Key.ENTER);
            this.typeCode("throw new Error(\"Error\");");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("ngOnInit(): void {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.typeCode("var frameModule = require(\"ui/frame\");");
            this.typeCode(Key.ENTER);
            this.typeCode("var HomeViewModel = require(\"./home-view-model\");");
            this.typeCode(Key.ENTER);
            this.typeCode("var homeViewModel = new HomeViewModel();");
            this.typeCode(Key.ENTER);
            this.typeCode("function pageLoaded(args) {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("var page = args.object;");
            this.typeCode("page.bindingContext = homeViewModel;");
            this.typeCode(Key.ENTER);
            this.typeCode("throw new Error(\"Error\");");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("exports.pageLoaded = pageLoaded;");
            this.typeCode(Key.ENTER);
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.typeCode("import { Observable } from 'data/observable';");
            this.typeCode(Key.ENTER);
            this.typeCode("export class HomeViewModel extends Observable {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("constructor() {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("super();");
            this.typeCode(Key.ENTER);
            this.typeCode("throw new Error(\"Error\");");
            this.typeCode(Key.ENTER);
        }
        else if(this.setupClass.typeOfProject.equals("vue"))
        {
            this.deleteAllCode();

            this.typeCode("<template>");
            this.typeCode(Key.ENTER);
            this.typeCode("<Page class=\"page\">");
            this.typeCode(Key.ENTER);
            this.typeCode("<ActionBar title=\"Home\" class=\"action-bar\" />");
            this.typeCode(Key.ENTER);
            this.typeCode("<ScrollView>");
            this.typeCode(Key.ENTER);
            this.typeCode("<StackLayout class=\"home-panel\">");
            this.typeCode(Key.ENTER);
            this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />");
            this.typeCode(Key.ENTER);
            this.typeCode("</StackLayout>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</ScrollView>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</Page>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</template>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);

            this.typeCode("<script>");
            this.typeCode(Key.ENTER);
            this.typeCode("export default {");
            this.typeCode(Key.ENTER);
            this.typeCode("mounted: function () {");
            this.typeCode(Key.ENTER);
            this.typeCode("throw new Error(\"Error\");");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.DOWN);
            this.typeCode(",");
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
        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowJavaError() {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";");
            this.typeCode(Key.ENTER);
            this.typeCode("declare var java : any");
            this.typeCode(";");
            this.typeCode(Key.ENTER);
            this.typeCode("@Component({");
            this.typeCode(Key.ENTER);
            this.typeCode("selector: \"Home\",");
            this.typeCode(Key.ENTER);
            this.typeCode("moduleId: module.id");
            this.typeCode(",");
            this.typeCode("");
            this.typeCode(Key.ENTER);
            this.setupClass.wait(1000);
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
            this.typeCode("java.lang.Integer.parseInt(\"sdklfjsd\");");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode("ngOnInit(): void {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
        } else if (this.setupClass.typeOfProject.equals("js")) {
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
            this.typeCode("java.lang.Integer.parseInt(\"sdklfjsd\");");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.typeCode(Key.ENTER);
            this.typeCode("exports.pageLoaded = pageLoaded;");
            this.typeCode(Key.ENTER);
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.typeCode("declare var java : any;");
            this.typeCode(Key.ENTER);
            this.typeCode("import { Observable } from 'data/observable';");
            this.typeCode(Key.ENTER);
            this.typeCode("export class HomeViewModel extends Observable {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("constructor() {");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("super();");
            this.typeCode(Key.ENTER);
            this.typeCode("java.lang.Integer.parseInt(\"sdklfjsd\");");
            this.typeCode(Key.ENTER);
        }
        else if(this.setupClass.typeOfProject.equals("vue"))
        {
            this.deleteAllCode();
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
            this.typeCode(Key.ENTER);
            this.typeCode("</ScrollView>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</Page>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</template>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);

            this.typeCode("<script>");
            this.typeCode(Key.ENTER);
            this.typeCode("export default {");
            this.typeCode(Key.ENTER);
            this.typeCode("mounted: function () {");
            this.typeCode(Key.ENTER);
            this.typeCode("java.lang.Integer.parseInt(\"sdklfjsd\");");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.DOWN);
            this.typeCode(",");
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

        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowiOSError() {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";");
            this.typeCode(Key.ENTER);
            this.typeCode("declare var NSFileManager : any");
            this.typeCode(";");
            this.typeCode(Key.ENTER);
            this.typeCode("@Component({");
            this.typeCode(Key.ENTER);
            this.typeCode("selector: \"Home\",");
            this.typeCode(Key.ENTER);
            this.typeCode("moduleId: module.id");
            this.typeCode(",");
            this.typeCode(Key.ENTER);
            this.typeCode("templateUrl: \"./home.component.html\",");
            this.typeCode(Key.ENTER);
            this.typeCode("styleUrls: ['./home.component.css']");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("export class HomeComponent implements OnInit {");
            this.typeCode(Key.ENTER);
            this.typeCode("constructor() {");
            this.typeCode(Key.ENTER);
            this.typeCode("var fileManager = NSFileManager.defaultManager;");
            this.typeCode(Key.ENTER);
            this.typeCode("fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("ngOnInit(): void {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.typeCode("var frameModule = require(\"ui/frame\");");
            this.typeCode(Key.ENTER);
            this.typeCode("var HomeViewModel = require(\"./home-view-model\");");
            this.typeCode(Key.ENTER);
            this.typeCode("var homeViewModel = new HomeViewModel();");
            this.typeCode(Key.ENTER);
            this.typeCode("function pageLoaded(args) {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("var page = args.object;");
            this.typeCode(Key.ENTER);
            this.typeCode("page.bindingContext = homeViewModel;");
            this.typeCode(Key.ENTER);
            this.typeCode("var fileManager = NSFileManager.defaultManager;");
            this.typeCode(Key.ENTER);
            this.typeCode("fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("exports.pageLoaded = pageLoaded;");
            this.typeCode(Key.ENTER);
        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.typeCode("declare var NSFileManager : any;");
            this.typeCode(Key.ENTER);
            this.typeCode("import { Observable } from 'data/observable';");
            this.typeCode(Key.ENTER);
            this.typeCode("export class HomeViewModel extends Observable {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("constructor() {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("super();");
            this.typeCode(Key.ENTER);
            this.typeCode("var fileManager = NSFileManager.defaultManager;");
            this.typeCode(Key.ENTER);
            this.typeCode("fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");");
            this.typeCode(Key.ENTER);
        }
        else if(this.setupClass.typeOfProject.equals("vue"))
        {
            this.deleteAllCode();
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
            this.typeCode(Key.ENTER);
            this.typeCode("</ScrollView>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</Page>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</template>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);

            this.typeCode("<script>");
            this.typeCode(Key.ENTER);
            this.typeCode("export default {");
            this.typeCode(Key.ENTER);
            this.typeCode("mounted: function () {");
            this.typeCode(Key.ENTER);
            this.typeCode("var fileManager = NSFileManager.defaultManager;");
            this.typeCode(Key.ENTER);
            this.typeCode("fileManager.contentsOfDirectoryAtPathError(\"/not-existing-path\");");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.DOWN);
            this.typeCode(",");
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

        this.setupClass.wait(2000);
    }

    public void typeJSTSCodeWithThrowiOSCocoaError() {
        this.deleteAllCode();
        if (this.setupClass.typeOfProject.equals("ng")) {
            this.typeCode("import { Component, OnInit } from \"@angular/core\";");
            this.typeCode(Key.ENTER);
            this.typeCode("declare var NSArray : any");
            this.typeCode(";");
            this.typeCode(Key.ENTER);
            this.typeCode("@Component({");
            this.typeCode(Key.ENTER);
            this.typeCode("selector: \"Home\",");
            this.typeCode(Key.ENTER);
            this.typeCode("moduleId: module.id");
            this.typeCode(",");
            this.typeCode(Key.ENTER);
            this.typeCode("templateUrl: \"./home.component.html\",");
            this.typeCode(Key.ENTER);
            this.typeCode("styleUrls: ['./home.component.css']");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("export class HomeComponent implements OnInit {");
            this.typeCode(Key.ENTER);
            this.typeCode("constructor() {");
            this.typeCode(Key.ENTER);
            this.typeCode("var arr = new NSArray();");
            this.typeCode(Key.ENTER);
            this.typeCode("var o = arr.objectAtIndex(\"5\");");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("ngOnInit(): void {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
        } else if (this.setupClass.typeOfProject.equals("js")) {
            this.typeCode("var frameModule = require(\"ui/frame\");");
            this.typeCode(Key.ENTER);
            this.typeCode("var HomeViewModel = require(\"./home-view-model\");");
            this.typeCode(Key.ENTER);
            this.typeCode("var homeViewModel = new HomeViewModel();");
            this.typeCode(Key.ENTER);
            this.typeCode("function pageLoaded(args) {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("var page = args.object;");
            this.typeCode(Key.ENTER);
            this.typeCode("page.bindingContext = homeViewModel;");
            this.typeCode(Key.ENTER);
            this.typeCode("var arr = new NSArray();");
            this.typeCode(Key.ENTER);
            this.typeCode("var o = arr.objectAtIndex(\"5\");");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.DOWN);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("exports.pageLoaded = pageLoaded;");
            this.typeCode(Key.ENTER);

        } else if (this.setupClass.typeOfProject.equals("tsc")) {
            this.typeCode("declare var NSArray : any;");
            this.typeCode(Key.ENTER);
            this.typeCode("import { Observable } from 'data/observable';");
            this.typeCode(Key.ENTER);
            this.typeCode("export class HomeViewModel extends Observable {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("constructor() {");
            this.typeCode(Key.ENTER);
            this.setupClass.s.type(Key.ENTER);
            this.typeCode("super();");
            this.typeCode(Key.ENTER);
            this.typeCode("var arr = new NSArray();");
            this.typeCode(Key.ENTER);
            this.typeCode("var o = arr.objectAtIndex(\"5\");");
            this.typeCode(Key.ENTER);
        }
        else if(this.setupClass.typeOfProject.equals("vue"))
        {
            this.deleteAllCode();
            this.typeCode("<template>");
            this.typeCode(Key.ENTER);
            this.typeCode("<Page class=\"page\">");
            this.typeCode(Key.ENTER);
            this.typeCode("<ActionBar title=\"Test</StackLayout>\" class=\"action-bar\" />");
            this.typeCode(Key.ENTER);
            this.typeCode("<ScrollView>");
            this.typeCode(Key.ENTER);
            this.typeCode("<StackLayout class=\"home-panel\">");
            this.typeCode(Key.ENTER);
            this.typeCode("<Label textWrap=\"true\" text=\"Testing Label!\" class=\"h2 description-label\" />");
            this.typeCode(Key.ENTER);
            this.typeCode("</StackLayout>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</ScrollView>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</Page>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);
            this.typeCode("</template>");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.ENTER);

            this.typeCode("<script>");
            this.typeCode(Key.ENTER);
            this.typeCode("export default {");
            this.typeCode(Key.ENTER);
            this.typeCode("mounted: function () {");
            this.typeCode(Key.ENTER);
            this.typeCode("var arr = new NSArray();");
            this.typeCode(Key.ENTER);
            this.typeCode("var o = arr.objectAtIndex(\"5\");");
            this.typeCode(Key.ENTER);
            this.typeCode(Key.DOWN);
            this.typeCode(",");
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
       setupClass.driver.findElements(By.xpath("//span[contains(.,'Device Logs')]")).get(0).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       return setupClass.driver.findElements(By.className("logs")).get(0).getText();
    }

    public void clearDeviceLogs()
    {
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
