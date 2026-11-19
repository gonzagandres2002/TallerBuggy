package co.edu.udea.certificacion.buggy.rentacarros.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/** Runner de la prueba E2E principal del taller: Sign Up, Sign In, seleccionar auto y comentar. */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/rate_car/rate_car.feature",
        glue = "co.edu.udea.certificacion.buggy.rentacarros.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RateCarRunner {
}
