package co.edu.udea.certificacion.buggy.rentacarros.interactions;

import co.edu.udea.certificacion.buggy.rentacarros.models.User;
import co.edu.udea.certificacion.buggy.rentacarros.utils.Wait;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.NavigationBar.BTN_LOGIN;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.NavigationBar.TXT_LOGIN;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.NavigationBar.TXT_PASSWORD;

/** "La menuda" del login: escribir el login, escribir la contrasena y dar clic en Login. */
public class Authenticate implements Interaction {

    private final User user;
    private final String login;

    public Authenticate(User user) {
        this.user = user;
        this.login = user.getLogin();
    }

    @Override
    @Step("{0} authenticates with the login #login")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Enter.theValue(user.getLogin()).into(TXT_LOGIN));
        Wait.aLittleBitTime();
        actor.attemptsTo(EnterSecretly.theValue(user.getPassword()).into(TXT_PASSWORD));
        Wait.aLittleBitTime();
        actor.attemptsTo(Click.on(BTN_LOGIN));
        Wait.aLittleBitTime();
    }

    public static Authenticate withTheCredentialsOf(User user) {
        return Tasks.instrumented(Authenticate.class, user);
    }
}
