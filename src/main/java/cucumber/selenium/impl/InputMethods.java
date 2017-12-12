package cucumber.selenium.impl;

import cucumber.selenium.AbstractTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.Iterator;
import java.util.List;

public class InputMethods extends SelectElementByType implements AbstractTest {
    private WebElement dropdown = null;
    private Select selectList = null;

    public InputMethods() {
    }

    public void enterText(String accessType, String text, String accessName) {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        this.driver.findElement(this.getelementbytype(accessType, accessName)).sendKeys(new CharSequence[]{text});
    }

    public void clearText(String accessType, String accessName) {
        this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        this.driver.findElement(this.getelementbytype(accessType, accessName)).clear();
    }

    public void selectelementfromdropdownbytype(Select select_list, String bytype, String option) {
        if(bytype.equals("selectByIndex")) {
            int index = Integer.parseInt(option);
            select_list.selectByIndex(index - 1);
        } else if(bytype.equals("value")) {
            select_list.selectByValue(option);
        } else if(bytype.equals("text")) {
            select_list.selectByVisibleText(option);
        }

    }

    public void selectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
        this.dropdown = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        this.selectList = new Select(this.dropdown);
        if(optionBy.equals("selectByIndex")) {
            this.selectList.selectByIndex(Integer.parseInt(option) - 1);
        } else if(optionBy.equals("value")) {
            this.selectList.selectByValue(option);
        } else if(optionBy.equals("text")) {
            this.selectList.selectByVisibleText(option);
        }

    }

    public void unselectAllOptionFromMultiselectDropdown(String accessType, String accessName) {
        this.dropdown = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        this.selectList = new Select(this.dropdown);
        this.selectList.deselectAll();
    }

    public void deselectOptionFromDropdown(String accessType, String optionBy, String option, String accessName) {
        this.dropdown = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        this.selectList = new Select(this.dropdown);
        if(optionBy.equals("selectByIndex")) {
            this.selectList.deselectByIndex(Integer.parseInt(option) - 1);
        } else if(optionBy.equals("value")) {
            this.selectList.deselectByValue(option);
        } else if(optionBy.equals("text")) {
            this.selectList.deselectByVisibleText(option);
        }

    }

    public void checkCheckbox(String accessType, String accessName) {
        WebElement checkbox = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        if(!checkbox.isSelected()) {
            checkbox.click();
        }

    }

    public void uncheckCheckbox(String accessType, String accessName) {
        WebElement checkbox = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        if(checkbox.isSelected()) {
            checkbox.click();
        }

    }

    public void toggleCheckbox(String accessType, String accessName) {
        ((WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)))).click();
    }

    public void selectRadioButton(String accessType, String accessName) {
        WebElement radioButton = (WebElement)this.wait.until(ExpectedConditions.presenceOfElementLocated(this.getelementbytype(accessType, accessName)));
        if(!radioButton.isSelected()) {
            radioButton.click();
        }

    }

    public void selectOptionFromRadioButtonGroup(String accessType, String option, String by, String accessName) {
        List radioButtonGroup = this.driver.findElements(this.getelementbytype(accessType, accessName));
        Iterator var6 = radioButtonGroup.iterator();

        while(var6.hasNext()) {
            WebElement rb = (WebElement)var6.next();
            if(by.equals("value")) {
                if(rb.getAttribute("value").equals(option) && !rb.isSelected()) {
                    rb.click();
                }
            } else if(by.equals("text") && rb.getText().equals(option) && !rb.isSelected()) {
                rb.click();
            }
        }

    }
}

