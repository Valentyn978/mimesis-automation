package cucumber.selenium.impl;

import cucumber.selenium.AbstractTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ClickElementsMethods extends SelectElementByType implements AbstractTest {
    private WebElement element = null;

    public ClickElementsMethods() {
    }

    public void click(String accessType, String accessName) {
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        this.element.click();
    }

    public void clickForcefully(String accessType, String accessName) {
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        JavascriptExecutor executor = (JavascriptExecutor)this.driver;
        executor.executeScript("arguments[0].click();", new Object[]{this.element});
    }

    public void doubleClick(String accessType, String accessValue) {
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessValue)));
        Actions action = new Actions(this.driver);
        action.moveToElement(this.element).doubleClick().perform();
    }
}

