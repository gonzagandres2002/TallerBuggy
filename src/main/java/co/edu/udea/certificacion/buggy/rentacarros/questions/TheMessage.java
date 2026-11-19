package co.edu.udea.certificacion.buggy.rentacarros.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.CarsPage.LBL_VOTE_MESSAGE;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.NavigationBar.LBL_GREETING;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.NavigationBar.LBL_LOGIN_ERROR;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.LBL_CONFIRM_PASSWORD_ERROR;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.LBL_ERROR;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.LBL_SUCCESS;

/**
 * Question que devuelve un String: el texto que la aplicacion muestra en pantalla.
 * La comparacion (equalTo / containsString) se hace en el StepDefinition con un Matcher.
 */
public class TheMessage implements Question<String> {

    private final Target target;

    public TheMessage(Target target) {
        this.target = target;
    }

    @Override
    public String answeredBy(Actor actor) {
        return target.resolveFor(actor).getText().trim();
    }

    @Override
    public String toString() {
        return "the " + target.getName();
    }

    public static TheMessage ofRegistrationSuccess() {
        return new TheMessage(LBL_SUCCESS);
    }

    public static TheMessage ofRegistrationError() {
        return new TheMessage(LBL_ERROR);
    }

    public static TheMessage ofPasswordConfirmation() {
        return new TheMessage(LBL_CONFIRM_PASSWORD_ERROR);
    }

    public static TheMessage ofTheGreeting() {
        return new TheMessage(LBL_GREETING);
    }

    public static TheMessage ofLoginError() {
        return new TheMessage(LBL_LOGIN_ERROR);
    }

    public static TheMessage ofTheVote() {
        return new TheMessage(LBL_VOTE_MESSAGE);
    }
}
