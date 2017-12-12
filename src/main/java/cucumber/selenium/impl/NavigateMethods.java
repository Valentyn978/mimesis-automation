package cucumber.selenium.impl;

import cucumber.selenium.AbstractTest;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;

public class NavigateMethods extends SelectElementByType implements AbstractTest {
    private WebElement element = null;
    private String old_win = null;
    private String lastWinHandle;

    public NavigateMethods() {
    }

    public void navigateTo(String url) {
        this.driver.get(url);
    }

    public void navigate(String direction) {
        if(direction.equals("back")) {
            this.driver.navigate().back();
        } else {
            this.driver.navigate().forward();
        }

    }

    public void closeDriver() {
        this.driver.close();
    }

    public Keys getKey() {
        String os = System.getProperty("os.name").toLowerCase();
        return os.contains("win")?Keys.CONTROL:(!os.contains("nux") && !os.contains("nix")?(os.contains("mac")?Keys.COMMAND:null):Keys.CONTROL);
    }

    public void zoomInOut(String inOut) {
        WebElement Sel = this.driver.findElement(this.getelementbytype("tagName", "html"));
        if(inOut.equals("ADD")) {
            Sel.sendKeys(new CharSequence[]{Keys.chord(new CharSequence[]{this.getKey(), Keys.ADD})});
        } else if(inOut.equals("SUBTRACT")) {
            Sel.sendKeys(new CharSequence[]{Keys.chord(new CharSequence[]{this.getKey(), Keys.SUBTRACT})});
        } else if(inOut.equals("reset")) {
            Sel.sendKeys(new CharSequence[]{Keys.chord(new CharSequence[]{this.getKey(), Keys.NUMPAD0})});
        }

    }

    public void zoomInOutTillElementDisplay(String accessType, String inOut, String accessName) {
        Actions action = new Actions(this.driver);
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));

        while(!this.element.isDisplayed()) {
            action.keyDown(this.getKey()).sendKeys(new CharSequence[]{inOut}).keyUp(this.getKey()).perform();
        }

    }

    public void resizeBrowser(int width, int height) {
        this.driver.manage().window().setSize(new Dimension(width, height));
    }

    public void maximizeBrowser() {
        this.driver.manage().window().maximize();
    }

    public void hoverOverElement(String accessType, String accessName) {
        Actions action = new Actions(this.driver);
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        action.moveToElement(this.element).perform();
    }

    public void scrollToElement(String accessType, String accessName) {
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        JavascriptExecutor executor = (JavascriptExecutor)this.driver;
        executor.executeScript("arguments[0].scrollIntoView();", new Object[]{this.element});
    }

    public void scrollPage(String to) throws Exception {
        JavascriptExecutor executor = (JavascriptExecutor)this.driver;
        if(to.equals("end")) {
            executor.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));", new Object[0]);
        } else {
            if(!to.equals("top")) {
                throw new Exception("Exception : Invalid Direction (only scroll \"top\" or \"end\")");
            }

            executor.executeScript("window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight),0);", new Object[0]);
        }

    }

    public void switchToNewWindow() {
        this.old_win = this.driver.getWindowHandle();

        String winHandle;
        for(Iterator var1 = this.driver.getWindowHandles().iterator(); var1.hasNext(); this.lastWinHandle = winHandle) {
            winHandle = (String)var1.next();
        }

        this.driver.switchTo().window(this.lastWinHandle);
    }

    public void switchToOldWindow() {
        this.driver.switchTo().window(this.old_win);
    }

    public void switchToWindowByTitle(String windowTitle) throws Exception {
        this.old_win = this.driver.getWindowHandle();
        boolean winFound = false;
        Iterator var3 = this.driver.getWindowHandles().iterator();

        while(var3.hasNext()) {
            String winHandle = (String)var3.next();
            String str = this.driver.switchTo().window(winHandle).getTitle();
            if(str.equals(windowTitle)) {
                winFound = true;
                break;
            }
        }

        if(!winFound) {
            throw new Exception("Window having title " + windowTitle + " not found");
        }
    }

    public void closeNewWindow() {
        this.driver.close();
    }

    public void switchFrame(String accessType, String accessName) {
        if(accessType.equalsIgnoreCase("index")) {
            this.driver.switchTo().frame(accessName);
        } else {
            this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
            this.driver.switchTo().frame(this.element);
        }

    }

    public void switchToDefaultContent() {
        this.driver.switchTo().defaultContent();
    }
}

