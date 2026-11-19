package co.edu.udea.certificacion.buggy.rentacarros.tasks;

import co.edu.udea.certificacion.buggy.rentacarros.interactions.FillTheRegistrationForm;
import co.edu.udea.certificacion.buggy.rentacarros.models.User;
import co.edu.udea.certificacion.buggy.rentacarros.utils.Wait;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;

import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.NavigationBar.LNK_REGISTER;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.BTN_REGISTER;

/** Tarea de negocio: registrarse (Sign Up) en Buggy Cars Rating. */
public class SignUp implements Task {

    private final User user;
    private final String login;

    public SignUp(User user) {
        this.user = user;
        this.login = user.getLogin();
    }

    @Override
    @Step("{0} signs up with the login #login")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LNK_REGISTER),
                FillTheRegistrationForm.with(user),
                Click.on(BTN_REGISTER)
        );
        Wait.aLittleBitTime();
    }

    public static SignUp withTheData(User user) {
        return Tasks.instrumented(SignUp.class, user);
    }
}
