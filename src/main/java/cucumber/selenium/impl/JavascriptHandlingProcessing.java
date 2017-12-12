package cucumber.selenium.impl;

import cucumber.configuration.DriverCucumber;
import cucumber.selenium.AbstractTest;
import org.openqa.selenium.WebDriver;

public class JavascriptHandlingProcessing implements AbstractTest {
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
}
