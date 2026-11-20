package co.edu.udea.certificacion.buggy.rentacarros.interactions;

import co.edu.udea.certificacion.buggy.rentacarros.models.User;
import co.edu.udea.certificacion.buggy.rentacarros.utils.Wait;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;

import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.TXT_CONFIRM_PASSWORD;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.TXT_FIRST_NAME;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.TXT_LAST_NAME;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.TXT_LOGIN;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.TXT_PASSWORD;

/** "La menuda" del registro: llenar login, nombre, apellido, contrasena y confirmacion. */
public class FillTheRegistrationForm implements Interaction {

    private final User user;
    private final String login;

    public FillTheRegistrationForm(User user) {
        this.user = user;
        this.login = user.getLogin();
    }

    @Override
    @Step("{0} fills the registration form for the login #login")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(user.getLogin()).into(TXT_LOGIN));
        Wait.aLittleBitTime();
        actor.attemptsTo(Enter.theValue(user.getFirstName()).into(TXT_FIRST_NAME));
        Wait.aLittleBitTime();
        actor.attemptsTo(Enter.theValue(user.getLastName()).into(TXT_LAST_NAME));
        Wait.aLittleBitTime();
        actor.attemptsTo(EnterSecretly.theValue(user.getPassword()).into(TXT_PASSWORD));
        Wait.aLittleBitTime();
        actor.attemptsTo(EnterSecretly.theValue(user.getPassword()).into(TXT_CONFIRM_PASSWORD));
        Wait.aLittleBitTime();
    }

    public static FillTheRegistrationForm with(User user) {
        return Tasks.instrumented(FillTheRegistrationForm.class, user);
    }
}
