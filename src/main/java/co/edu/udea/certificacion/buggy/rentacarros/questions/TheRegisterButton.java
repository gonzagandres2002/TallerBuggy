package co.edu.udea.certificacion.buggy.rentacarros.questions;

import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.RegisterPage.BTN_REGISTER;

/** Question que devuelve un Boolean: si el boton Register esta habilitado. */
public class TheRegisterButton implements Question<Boolean> {

    @Override
    @Subject("whether the register button is enabled")
    public Boolean answeredBy(Actor actor) {
        return BTN_REGISTER.resolveFor(actor).isEnabled();
    }

    public static TheRegisterButton isEnabled() {
        return new TheRegisterButton();
    }
}
