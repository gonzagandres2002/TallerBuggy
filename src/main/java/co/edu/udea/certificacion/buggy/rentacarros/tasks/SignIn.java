package co.edu.udea.certificacion.buggy.rentacarros.tasks;

import co.edu.udea.certificacion.buggy.rentacarros.interactions.Authenticate;
import co.edu.udea.certificacion.buggy.rentacarros.models.User;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

/** Tarea de negocio: iniciar sesion (Sign In). El detalle (escribir, clic) esta en la interaccion Authenticate. */
public class SignIn implements Task {

    private final User user;
    private final String login;

    public SignIn(User user) {
        this.user = user;
        this.login = user.getLogin();
    }

    @Override
    @Step("{0} signs in as #login")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Authenticate.withTheCredentialsOf(user));
    }

    public static SignIn withTheCredentialsOf(User user) {
        return Tasks.instrumented(SignIn.class, user);
    }
}
