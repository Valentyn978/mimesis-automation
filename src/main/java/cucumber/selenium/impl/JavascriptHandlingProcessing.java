package cucumber.selenium.impl;

import cucumber.configuration.DriverSetUp;
import cucumber.selenium.AbstractTest;
import org.openqa.selenium.WebDriver;

public class JavascriptHandlingProcessing implements AbstractTest {
    protected WebDriver driver = DriverSetUp.getDefaultDriver();

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
