package cucumber.selenium.impl;

import cucumber.configuration.DriverCucumber;
import cucumber.selenium.AbstractTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenShotProcessing implements AbstractTest {
    protected WebDriver driver = DriverCucumber.getDefaultDriver();

    public ScreenShotProcessing() {
    }

    public void takeScreenShot() throws IOException {
        File scrFile = (File)((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar cal = Calendar.getInstance();
        System.out.println(dateFormat.format(cal.getTime()));
        String scrFilepath = scrFile.getAbsolutePath();
        System.out.println("scrFilepath: " + scrFilepath);
        File currentDirFile = new File("Screenshots");
        String path = currentDirFile.getAbsolutePath();
        System.out.println("path: " + path + "+++");
        System.out.println("****\n" + path + "\\screenshot" + dateFormat.format(cal.getTime()) + ".png");
        FileUtils.copyFile(scrFile, new File(path + "\\screenshot" + dateFormat.format(cal.getTime()) + ".png"));
    }
}
