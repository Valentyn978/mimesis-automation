package cucumber.selenium.impl;

import cucumber.configuration.DriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectElementByType {
    protected WebDriver driver = DriverSetUp.getDefaultDriver();
    protected WebDriverWait wait;

    public SelectElementByType() {
        this.wait = new WebDriverWait(this.driver, 30L);
    }

    public By getelementbytype(String type, String access_name) {
        byte var4 = -1;
        switch(type.hashCode()) {
            case -1549184699:
                if(type.equals("tagName")) {
                    var4 = 7;
                }
                break;
            case 3355:
                if(type.equals("id")) {
                    var4 = 0;
                }
                break;
            case 98819:
                if(type.equals("css")) {
                    var4 = 4;
                }
                break;
            case 3373707:
                if(type.equals("name")) {
                    var4 = 1;
                }
                break;
            case 94742904:
                if(type.equals("class")) {
                    var4 = 2;
                }
                break;
            case 114256029:
                if(type.equals("xpath")) {
                    var4 = 3;
                }
                break;
            case 292026600:
                if(type.equals("partialLinkText")) {
                    var4 = 6;
                }
                break;
            case 1194187847:
                if(type.equals("linkText")) {
                    var4 = 5;
                }
        }

        switch(var4) {
            case 0:
                return By.id(access_name);
            case 1:
                return By.name(access_name);
            case 2:
                return By.className(access_name);
            case 3:
                return By.xpath(access_name);
            case 4:
                return By.cssSelector(access_name);
            case 5:
                return By.linkText(access_name);
            case 6:
                return By.partialLinkText(access_name);
            case 7:
                return By.tagName(access_name);
            default:
                return null;
        }
    }
}

