package co.edu.udea.certificacion.buggy.rentacarros.interactions;

import co.edu.udea.certificacion.buggy.rentacarros.utils.Wait;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;

import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.CarsPage.BTN_VOTE;
import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.CarsPage.TXT_COMMENT;

/** "La menuda" del voto: escribir el comentario y dar clic en Vote!. */
public class WriteTheComment implements Interaction {

    private final String comment;

    public WriteTheComment(String comment) {
        this.comment = comment;
    }

    @Override
    @Step("{0} writes the comment '#comment' and clicks on Vote!")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Scroll.to(TXT_COMMENT), Enter.theValue(comment).into(TXT_COMMENT));
        Wait.aLittleBitTime();
        actor.attemptsTo(Click.on(BTN_VOTE));
        Wait.aLittleBitTime();
    }

    public static WriteTheComment withTheText(String comment) {
        return Tasks.instrumented(WriteTheComment.class, comment);
    }
}
