package co.edu.udea.certificacion.buggy.rentacarros.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/** Punto de entrada de la prueba: une el .feature con los StepDefinitions (el "pegante"). */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/register_user/register_user.feature",
        glue = "co.edu.udea.certificacion.buggy.rentacarros.stepdefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RegisterUserRunner {
}
