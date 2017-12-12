package cucumber.selenium;


import cucumber.selenium.impl.*;

public interface AbstractTest {
    MiscMethods commonObj = new MiscMethods();
    NavigateMethods navigationObj = new NavigateMethods();
    AssertionMethods assertionObj = new AssertionMethods();
    ClickElementsMethods clickObj = new ClickElementsMethods();
    InputMethods inputObj = new InputMethods();
    ProgressMethods progressObj = new ProgressMethods();
    JavascriptHandlingProcessing javascriptObj = new JavascriptHandlingProcessing();
    ScreenShotProcessing screenshotObj = new ScreenShotProcessing();
}
