package screenplay.performables;

import java.util.function.Consumer;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.OpenAt;
import net.serenitybdd.screenplay.actions.PerformOn;
import org.openqa.selenium.WebElement;

public class ConnectToService {

	public static Performable weatherService() {
		final Consumer<WebElementFacade> webElementFacadeConsumer = null;
		return Task.where("{0} connect to the Weather service", PerformOn.eachMatching("", webElementFacadeConsumer));
	}
}
