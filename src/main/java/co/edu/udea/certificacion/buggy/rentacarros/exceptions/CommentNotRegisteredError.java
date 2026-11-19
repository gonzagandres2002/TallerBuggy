package co.edu.udea.certificacion.buggy.rentacarros.exceptions;

/**
 * Falla funcional: el voto se hizo pero el comentario no aparece en la tabla de comentarios.
 * (El profesor diferencia "falla" = la funcionalidad no hizo lo esperado, de "error" = no se pudo ejecutar.)
 */
public class CommentNotRegisteredError extends AssertionError {

    public static final String MESSAGE = "The comment was not found in the comments table of the car";

    public CommentNotRegisteredError(String message, Throwable cause) {
        super(message, cause);
    }
}
