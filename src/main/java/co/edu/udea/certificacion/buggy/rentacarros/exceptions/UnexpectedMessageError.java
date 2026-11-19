package co.edu.udea.certificacion.buggy.rentacarros.exceptions;

/** Falla funcional: la aplicacion mostro un mensaje distinto al esperado por el criterio de aceptacion. */
public class UnexpectedMessageError extends AssertionError {

    public static final String MESSAGE = "The application did not show the expected message";

    public UnexpectedMessageError(String message, Throwable cause) {
        super(message, cause);
    }
}
