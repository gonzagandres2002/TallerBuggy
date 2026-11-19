package co.edu.udea.certificacion.buggy.rentacarros.tasks;

import co.edu.udea.certificacion.buggy.rentacarros.interactions.WriteTheComment;
import co.edu.udea.certificacion.buggy.rentacarros.utils.TestDataFactory;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import static co.edu.udea.certificacion.buggy.rentacarros.utils.Constants.POSTED_COMMENT;

/**
 * Tarea de negocio: votar por el auto dejando un comentario.
 * El comentario publicado queda en la memoria del actor para validarlo despues en la Question.
 */
public class VoteForTheCar implements Task {

    private final String comment;

    public VoteForTheCar(String comment) {
        this.comment = comment;
    }

    @Override
    @Step("{0} votes for the car leaving the comment '#comment'")
    public <T extends Actor> void performAs(T actor) {
        String uniqueComment = TestDataFactory.uniqueComment(comment);
        actor.remember(POSTED_COMMENT, uniqueComment);
        actor.attemptsTo(WriteTheComment.withTheText(uniqueComment));
    }

    public static VoteForTheCar leavingTheComment(String comment) {
        return Tasks.instrumented(VoteForTheCar.class, comment);
    }
}
