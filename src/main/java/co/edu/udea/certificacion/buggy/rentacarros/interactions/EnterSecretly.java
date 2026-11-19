package co.edu.udea.certificacion.buggy.rentacarros.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Seguridad: Enter.theValue(...) de Serenity escribe el valor en el titulo del paso del reporte
 * ("enters 'MiClave123' into ..."). Esta interaccion escribe la contrasena sin exponerla en el reporte.
 */
public class EnterSecretly implements Interaction {

    private final String secret;
    private final Target target;

    public EnterSecretly(String secret, Target target) {
        this.secret = secret;
        this.target = target;
    }

    @Override
    @Step("{0} types a protected value (******) into the #target")
    public <T extends Actor> void performAs(T actor) {
        target.resolveFor(actor).clear();
        target.resolveFor(actor).sendKeys(secret);
    }

    public static Builder theValue(String secret) {
        return new Builder(secret);
    }

    public static class Builder {
        private final String secret;

        private Builder(String secret) {
            this.secret = secret;
        }

        public EnterSecretly into(Target target) {
            return Tasks.instrumented(EnterSecretly.class, secret, target);
        }
    }
}
