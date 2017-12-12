package cucumber.selenium.impl;

import java.util.Arrays;

public class MiscMethods {
    public MiscMethods() {
    }

    public boolean valid_locator_type(String type) {
        return Arrays.asList(new String[]{"id", "class", "css", "name", "xpath"}).contains(type);
    }

    public void validateLocator(String type) throws Exception {
        if(!this.valid_locator_type(type)) {
            throw new Exception("Invalid locator type - " + type);
        }
    }

    public boolean valid_option_by(String option_by) {
        return Arrays.asList(new String[]{"text", "value", "index"}).contains(option_by);
    }

    public void validateOptionBy(String optionBy) throws Exception {
        if(!this.valid_option_by(optionBy)) {
            throw new Exception("Invalid option by - " + optionBy);
        }
    }
}

