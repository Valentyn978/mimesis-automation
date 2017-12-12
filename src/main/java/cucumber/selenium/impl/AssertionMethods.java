package cucumber.selenium.impl;

import cucumber.selenium.AbstractTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

public class AssertionMethods extends SelectElementByType implements AbstractTest {
    private WebElement element = null;

    public AssertionMethods() {
    }

    public String getPageTitle() {
        return this.driver.getTitle();
    }

    public void checkTitle(String title, boolean testCase) throws TestCaseFailed {
        String pageTitle = this.getPageTitle();
        if(testCase) {
            if(!pageTitle.equals(title)) {
                throw new TestCaseFailed("Page Title Not Matched, Actual Page Title : " + pageTitle);
            }
        } else if(pageTitle.equals(title)) {
            throw new TestCaseFailed("Page Title Matched, Actual Page Title : " + pageTitle);
        }

    }

    public void checkPartialTitle(String partialTitle, boolean testCase) throws TestCaseFailed {
        String pageTitle = this.getPageTitle();
        if(testCase) {
            if(!pageTitle.contains(partialTitle)) {
                throw new TestCaseFailed("Partial Page Title Not Present, Actual Page Title : " + pageTitle);
            }
        } else if(pageTitle.contains(partialTitle)) {
            throw new TestCaseFailed("Partial Page Title Present, Actual Page Title : " + pageTitle);
        }

    }

    public String getElementText(String accessType, String accessName) {
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        return this.element.getText();
    }

    public void checkElementText(String accessType, String actualValue, String accessName, boolean testCase) throws TestCaseFailed {
        String elementText = this.getElementText(accessType, accessName);
        if(testCase) {
            if(!elementText.equals(actualValue)) {
                throw new TestCaseFailed("Text Not Matched");
            }
        } else if(elementText.equals(actualValue)) {
            throw new TestCaseFailed("Text Matched");
        }

    }

    public void checkElementPartialText(String accessType, String actualValue, String accessName, boolean testCase) throws TestCaseFailed {
        String elementText = this.getElementText(accessType, accessName);
        if(testCase) {
            if(!elementText.contains(actualValue)) {
                throw new TestCaseFailed("Text Not Matched");
            }
        } else if(elementText.contains(actualValue)) {
            throw new TestCaseFailed("Text Matched");
        }

    }

    public boolean isElementEnabled(String accessType, String accessName) {
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        return this.element.isEnabled();
    }

    public void checkElementEnable(String accessType, String accessName, boolean testCase) throws TestCaseFailed {
        boolean result = this.isElementEnabled(accessType, accessName);
        if(testCase) {
            if(!result) {
                throw new TestCaseFailed("Element Not Enabled");
            }
        } else if(result) {
            throw new TestCaseFailed("Element Enabled");
        }

    }

    public String getElementAttribute(String accessType, String accessName, String attributeName) {
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        return this.element.getAttribute(attributeName);
    }

    public void checkElementAttribute(String accessType, String attributeName, String attributeValue, String accessName, boolean testCase) throws TestCaseFailed {
        String attrVal = this.getElementAttribute(accessType, accessName, attributeName);
        if(testCase) {
            if(!attrVal.equals(attributeValue)) {
                throw new TestCaseFailed("Attribute Value Not Matched");
            }
        } else if(attrVal.equals(attributeValue)) {
            throw new TestCaseFailed("Attribute Value Matched");
        }

    }

    public boolean isElementDisplayed(String accessType, String accessName) {
        this.element = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        return this.element.isDisplayed();
    }

    public void checkElementPresence(String accessType, String accessName, boolean testCase) throws TestCaseFailed {
        if(testCase) {
            if(!this.isElementDisplayed(accessType, accessName)) {
                throw new TestCaseFailed("Element Not Present");
            }
        } else {
            try {
                if(this.isElementDisplayed(accessType, accessName)) {
                    throw new Exception("Present");
                }
            } catch (Exception var5) {
                if(var5.getMessage().equals("Present")) {
                    throw new TestCaseFailed("Element Present");
                }
            }
        }

    }

    public void isCheckboxChecked(String accessType, String accessName, boolean shouldBeChecked) throws TestCaseFailed {
        WebElement checkbox = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        if(!checkbox.isSelected() && shouldBeChecked) {
            throw new TestCaseFailed("Checkbox is not checked");
        } else if(checkbox.isSelected() && !shouldBeChecked) {
            throw new TestCaseFailed("Checkbox is checked");
        }
    }

    public void isRadioButtonSelected(String accessType, String accessName, boolean shouldBeSelected) throws TestCaseFailed {
        WebElement radioButton = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        if(!radioButton.isSelected() && shouldBeSelected) {
            throw new TestCaseFailed("Radio Button not selected");
        } else if(radioButton.isSelected() && !shouldBeSelected) {
            throw new TestCaseFailed("Radio Button is selected");
        }
    }

    public void isOptionFromRadioButtonGroupSelected(String accessType, String by, String option, String accessName, boolean shouldBeSelected) throws TestCaseFailed {
        List radioButtonGroup = (List)this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(this.getelementbytype(accessType, accessName)));
        Iterator var7 = radioButtonGroup.iterator();

        while(var7.hasNext()) {
            WebElement rb = (WebElement)var7.next();
            if(by.equals("value")) {
                if(rb.getAttribute("value").equals(option)) {
                    if(!rb.isSelected() && shouldBeSelected) {
                        throw new TestCaseFailed("Radio Button not selected");
                    }

                    if(rb.isSelected() && !shouldBeSelected) {
                        throw new TestCaseFailed("Radio Button is selected");
                    }
                }
            } else if(rb.getText().equals(option)) {
                if(!rb.isSelected() && shouldBeSelected) {
                    throw new TestCaseFailed("Radio Button not selected");
                }

                if(rb.isSelected() && !shouldBeSelected) {
                    throw new TestCaseFailed("Radio Button is selected");
                }
            }
        }

    }

    public String getAlertText() {
        return this.driver.switchTo().alert().getText();
    }

    public void checkAlertText(String text) throws TestCaseFailed {
        if(!this.getAlertText().equals(text)) {
            throw new TestCaseFailed("Text on alert pop up not matched");
        }
    }

    public void isOptionFromDropdownSelected(String accessType, String by, String option, String accessName, boolean shouldBeSelected) throws TestCaseFailed {
        Select selectList = null;
        WebElement dropdown = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        selectList = new Select(dropdown);
        String actualValue = "";
        if(by.equals("text")) {
            actualValue = selectList.getFirstSelectedOption().getText();
        } else {
            actualValue = selectList.getFirstSelectedOption().getAttribute("value");
        }

        if(!actualValue.equals(option) && shouldBeSelected) {
            throw new TestCaseFailed("Option Not Selected From Dropwdown");
        } else if(actualValue.equals(option) && !shouldBeSelected) {
            throw new TestCaseFailed("Option Selected From Dropwdown");
        }
    }
}
