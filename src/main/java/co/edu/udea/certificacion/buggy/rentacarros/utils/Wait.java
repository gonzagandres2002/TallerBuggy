package co.edu.udea.certificacion.buggy.rentacarros.utils;

/**
 * Pausa entre paso y paso para observar la prueba durante la sustentacion (requisito del taller).
 * Basada en la utilidad que el profesor compartio en la clase 2.
 *
 * El tiempo por defecto se configura sin tocar codigo:
 *   gradle clean test -Ddemo.delay.ms=2000   (mas lento, para exponer)
 *   gradle clean test -Ddemo.delay.ms=0      (sin pausas, para CI)
 *
 * Solo tiene fines academicos: en la industria no se usan sleeps entre pasos.
 */
public final class Wait {

    private static final int DEFAULT_DELAY_MS = 1500;

    private Wait() {
    }

    public static void aLittleBitTime() {
        aLittleBitTime(Integer.getInteger("demo.delay.ms", DEFAULT_DELAY_MS));
    }

    public static void aLittleBitTime(Integer millis) {
        if (millis == null || millis <= 0) {
            return;
        }
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
