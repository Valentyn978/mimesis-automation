package cucumber.selenium.impl;

import cucumber.configuration.DriverCucumber;
import cucumber.selenium.AbstractTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class JavascriptHandlingProcessing extends SelectElementByType implements AbstractTest {
    protected WebDriver driver = DriverCucumber.getDefaultDriver();

    public JavascriptHandlingProcessing() {
    }

    public void handleAlert(String decision) {
        if (decision.equals("accept")) {
            this.driver.switchTo().alert().accept();
        } else {
            this.driver.switchTo().alert().dismiss();
        }

    }

    public void pressKeyboardButton(String button) {
        Actions action = new Actions(this.driver);
        if (button.toLowerCase().equals("enter")) {
            action.sendKeys(Keys.ENTER).perform();
        }

    }
}
