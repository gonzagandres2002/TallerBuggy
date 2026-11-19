package co.edu.udea.certificacion.buggy.rentacarros.utils;

import co.edu.udea.certificacion.buggy.rentacarros.models.User;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static co.edu.udea.certificacion.buggy.rentacarros.utils.Constants.DEFAULT_FIRST_NAME;
import static co.edu.udea.certificacion.buggy.rentacarros.utils.Constants.DEFAULT_LAST_NAME;

/**
 * Genera datos de prueba en tiempo de ejecucion.
 * - Principio FIRST (Independent / Repeatable): cada escenario crea su propio usuario unico,
 *   asi ningun escenario depende de otro ni de datos creados en ejecuciones anteriores.
 * - Seguridad: no hay credenciales reales escritas en el codigo ni en los .feature.
 */
public final class TestDataFactory {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final String LETTERS = "abcdefghijkmnpqrstuvwxyz";
    private static final String SYMBOLS = "!#$%*";
    private static final DateTimeFormatter STAMP = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");

    private TestDataFactory() {
    }

    public static User aNewValidUser() {
        return new User(uniqueLogin(), DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, strongPassword());
    }

    /** Login unico: prefijo + fecha/hora con milisegundos + 3 digitos aleatorios (max 50 caracteres). */
    public static String uniqueLogin() {
        return "qa_udea_" + LocalDateTime.now().format(STAMP) + RANDOM.nextInt(1000);
    }

    /** Contrasena que cumple la politica de Buggy: mayuscula, minusculas, numero, simbolo, >= 8. */
    public static String strongPassword() {
        StringBuilder password = new StringBuilder("Qa");
        for (int i = 0; i < 6; i++) {
            password.append(LETTERS.charAt(RANDOM.nextInt(LETTERS.length())));
        }
        password.append(RANDOM.nextInt(10)).append(RANDOM.nextInt(10));
        password.append(SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length())));
        return password.toString();
    }

    /** Agrega una marca unica al comentario para poder encontrarlo sin ambiguedad en la tabla. */
    public static String uniqueComment(String comment) {
        return comment + " [run " + LocalDateTime.now().format(STAMP) + "]";
    }
}
