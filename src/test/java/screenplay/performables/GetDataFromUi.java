package screenplay.performables;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import screenplay.pages.WeatherUiPage;

public class GetDataFromUi {

	public static Performable theWeatherUkPage() {
		return Task.where("{0} Getting page", Open.browserOn().the(WeatherUiPage.class));
	}
}
