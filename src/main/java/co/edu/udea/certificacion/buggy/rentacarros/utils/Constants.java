package co.edu.udea.certificacion.buggy.rentacarros.utils;

/** Constantes compartidas por toda la automatizacion. */
public final class Constants {

    /** Nombre de la pagina configurada en serenity.conf (bloque pages). */
    public static final String BUGGY_HOME_PAGE = "pages.buggyUrl";

    /** Llaves de la memoria del actor (actor.remember / actor.recall). */
    public static final String REGISTERED_USER = "registeredUser";
    public static final String POSTED_COMMENT = "postedComment";

    public static final String DEFAULT_FIRST_NAME = "John";
    public static final String DEFAULT_LAST_NAME = "Tester";

    private Constants() {
    }
}
