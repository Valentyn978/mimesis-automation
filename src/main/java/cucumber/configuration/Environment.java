package cucumber.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.FileInputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Environment {
    static WebDriver driver = null;
    static String browserName = null;
    static String cloudBrowserConfigFile = null;
    static String cloudPlatformConfigFile = null;
    static String currentPath = System.getProperty("user.dir");
    static Properties prop = new Properties();

    public Environment() {
    }

    public static String getBrowserName() {
        browserName = System.getProperty("browser");
        cloudBrowserConfigFile = "windows_chrome";
//        cloudBrowserConfigFile = System.getProperty("cloud_config");
        if(cloudBrowserConfigFile != null) {
            System.out.println("reading config file");

            try {
                browserName = cloudBrowserConfigFile.split("_")[0];
                FileInputStream e = new FileInputStream(currentPath + "/src/main/java/cucumber.cloudBrowserConfigs/" + cloudBrowserConfigFile + ".properties");
                e.close();
            } catch (Exception var1) {
                var1.printStackTrace();
                System.exit(0);
            }
        } else if(browserName == null) {
            browserName = "ff";
        }

        return browserName;
    }

    public static WebDriver WebDriverSetUp() {
        System.out.println("Creating Web Driver");

        try {
            FileInputStream e = new FileInputStream(currentPath + "/src/main/java/cucumber.cloudPlatformConfigs/platforms.properties");
            prop.load(e);
            HashMap driverConfig = new HashMap();
            Enumeration enuKeys = prop.keys();

            String url;
            while(enuKeys.hasMoreElements()) {
                url = (String)enuKeys.nextElement();
                String remoteDriverURL = prop.getProperty(url);
                driverConfig.put(url, remoteDriverURL);
            }

            e.close();
            prop.clear();
            url = (String)driverConfig.get("protocol") + "://" + (String)driverConfig.get("username") + ":" + (String)driverConfig.get("access_key") + (String)driverConfig.get("url");
            System.out.println("url :" + url);
            URL remoteDriverURL1 = new URL(url);
            DesiredCapabilities capability = new DesiredCapabilities();
            e = new FileInputStream(currentPath + "/src/main/java/cucumber.cloudBrowserConfigs/" + cloudBrowserConfigFile + ".properties");
            prop.load(e);
            enuKeys = prop.keys();

            while(enuKeys.hasMoreElements()) {
                String key = (String)enuKeys.nextElement();
                String value = prop.getProperty(key);
                System.out.println("key :" + key + " Value :" + value);
                capability.setCapability(key, value);
            }

            e.close();
            driver = new RemoteWebDriver(remoteDriverURL1, capability);
        } catch (Exception var8) {
            var8.printStackTrace();
            System.exit(0);
        }

        return driver;
    }

    public static WebDriver CreateWebDriver(String browser) {
        System.out.println("Browser: " + browser);
        String var1 = browser.toLowerCase();
        byte var2 = -1;
        switch(var1.hashCode()) {
            case -1361128838:
                if(var1.equals("chrome")) {
                    var2 = 3;
                }
                break;
            case -909897856:
                if(var1.equals("safari")) {
                    var2 = 6;
                }
                break;
            case -849452327:
                if(var1.equals("firefox")) {
                    var2 = 1;
                }
                break;
            case 3173:
                if(var1.equals("ch")) {
                    var2 = 2;
                }
                break;
            case 3264:
                if(var1.equals("ff")) {
                    var2 = 0;
                }
                break;
            case 3356:
                if(var1.equals("ie")) {
                    var2 = 4;
                }
                break;
            case 397430400:
                if(var1.equals("internetexplorer")) {
                    var2 = 5;
                }
        }

        switch(var2) {
            case 0:
            case 1:
                driver = new FirefoxDriver();
                break;
            case 2:
            case 3:
                driver = new ChromeDriver();
                break;
            case 4:
            case 5:
                driver = new InternetExplorerDriver();
                break;
            case 6:
                driver = new SafariDriver();
                break;
            case 7:
                driver = WebDriverSetUp();
                break;
            default:
                System.out.println("Invalid browser name " + browser);
                System.exit(0);
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(60L, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20L, TimeUnit.SECONDS);
        return driver;
    }
}
