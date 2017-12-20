package userStepDefinitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumberHtmlReport"},
        features = "classpath:google_check.feature",
        glue = {"userStepDefinitions"}
)

public class RunCukeTest {
}
