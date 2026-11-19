package co.edu.udea.certificacion.buggy.rentacarros.tasks;

import co.edu.udea.certificacion.buggy.rentacarros.utils.Wait;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

import static co.edu.udea.certificacion.buggy.rentacarros.utils.Constants.BUGGY_HOME_PAGE;

/** Tarea de negocio: entrar al sitio. La URL vive en serenity.conf, no en el codigo. */
public class OpenTheBrowser implements Task {

    @Override
    @Step("{0} opens the Buggy Cars Rating home page")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Open.browserOn().thePageNamed(BUGGY_HOME_PAGE));
        Wait.aLittleBitTime();
    }

    public static OpenTheBrowser onTheBuggyHomePage() {
        return Tasks.instrumented(OpenTheBrowser.class);
    }
}
