package co.edu.udea.certificacion.buggy.rentacarros.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/**
 * Prepara el escenario antes de cada prueba: OnlineCast le da a cada actor la habilidad
 * BrowseTheWeb con el navegador configurado en serenity.conf (webdriver.driver = chrome).
 */
public class Hooks {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void drawTheCurtain() {
        OnStage.drawTheCurtain();
    }
}
