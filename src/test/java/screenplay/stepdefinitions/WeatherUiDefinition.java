package screenplay.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import screenplay.performables.GetDataFromUi;

import static com.helpers.ScenarioContext.SCENARIO_CONTEXT;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;

public class WeatherUiDefinition {

	@Before
	public void setTheStage() {
		OnStage.setTheStage(new OnlineCast());
	}

	@Given("^\"(.*)\" open connection to the Service")
	public void serviceUserOpenConnectionToTheService(String actor) {
		theActorCalled(actor).attemptsTo();
	}

	@When("^He checks if the service connection is stable")
	public void heChecksIfTheServiceCOnnectionIsStable() {

	}

	@Then("^data is getting and save")
	public void dataIsGettingAndSave() {
		SCENARIO_CONTEXT.put("serviceData", "");
	}

	@Given("^web page opened by the \"(.*)\"")
	public void webPageOpenedByThe(String userName) {
		theActorCalled(userName).attemptsTo(GetDataFromUi.theWeatherUkPage());
	}

	@When("^He gets data from UI is correctly")
	public void heGetsDataFromUiIsCorrectly() {

	}

	@Then("^\"(.*)\" compare data with Service")
	public void userCompareDataWithService(String userName) {

	}
}
