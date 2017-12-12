package cucumber.selenium.impl;

import cucumber.selenium.AbstractTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProgressMethods extends SelectElementByType implements AbstractTest {
    public ProgressMethods() {
    }

    public void wait(String time) throws NumberFormatException, InterruptedException {
        Thread.sleep((long)(Integer.parseInt(time) * 1000));
    }

    public void waitForElementToDisplay(String accessType, String accessName, String duration) {
        By byEle = this.getelementbytype(accessType, accessName);
        WebDriverWait wait = new WebDriverWait(this.driver, (long)(Integer.parseInt(duration) * 1000));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byEle));
    }

    public void waitForElementToClick(String accessType, String accessName, String duration) {
        By byEle = this.getelementbytype(accessType, accessName);
        WebDriverWait wait = new WebDriverWait(this.driver, (long)(Integer.parseInt(duration) * 1000));
        wait.until(ExpectedConditions.elementToBeClickable(byEle));
    }
}

