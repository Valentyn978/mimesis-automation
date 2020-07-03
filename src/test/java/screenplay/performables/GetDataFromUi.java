package screenplay.performables;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.Text;
import screenplay.pages.WeatherUiPage;
import screenplay.screens.WeatherOfLondonForm;

public class GetDataFromUi {

	public static Performable theWeatherUkPage() {
		return Task.where("{0} Getting page", Open.browserOn().the(WeatherUiPage.class));
	}

	public static Question<String> tempOfLondonCity() {
		return actor -> Text.of(WeatherOfLondonForm.TEMP_OF_LONDON_LOCATOR).viewedBy(actor).asString();
	}
}
