package cucumber;

import cucumber.api.cli.Main;

public class RunMe {
    public static void main(String[] args) throws Throwable {
        Main.main(new String[]{"-g", "userStepDefinitions", "src/test/resources"});
    }
}
