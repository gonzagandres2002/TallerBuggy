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

/**
 * Tarea de negocio para el camino excepcional: diligenciar el registro con contrasenas que no coinciden.
 * No se da clic en Register porque la aplicacion debe mantener el boton deshabilitado.
 */
public class AttemptToSignUp implements Task {

    private final User user;

    public AttemptToSignUp(User user) {
        this.user = user;
    }

    @Override
    @Step("{0} fills the sign up form with passwords that do not match")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(LNK_REGISTER),
                FillTheRegistrationForm.withAConfirmationThatDoesNotMatch(user)
        );
        Wait.aLittleBitTime();
    }

    public static AttemptToSignUp withPasswordsThatDoNotMatch(User user) {
        return Tasks.instrumented(AttemptToSignUp.class, user);
    }
}
