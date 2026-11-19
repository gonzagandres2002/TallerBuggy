package co.edu.udea.certificacion.buggy.rentacarros.questions;

import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.edu.udea.certificacion.buggy.rentacarros.userinterfaces.CarsPage.CELL_COMMENT;
import static co.edu.udea.certificacion.buggy.rentacarros.utils.Constants.POSTED_COMMENT;

/**
 * Question que devuelve un Boolean (estilo de la clase 2): compara desde aqui y retorna true/false.
 * Busca en la tabla de comentarios del auto el comentario que el actor publico (lo recuerda en su memoria).
 */
public class TheComment implements Question<Boolean> {

    @Override
    @Subject("whether the posted comment is registered in the comments of the car")
    public Boolean answeredBy(Actor actor) {
        String postedComment = actor.recall(POSTED_COMMENT);
        if (postedComment == null) {
            return false;
        }
        return !CELL_COMMENT.of(postedComment).resolveAllFor(actor).isEmpty();
    }

    public static TheComment isRegisteredInTheCar() {
        return new TheComment();
    }
}
