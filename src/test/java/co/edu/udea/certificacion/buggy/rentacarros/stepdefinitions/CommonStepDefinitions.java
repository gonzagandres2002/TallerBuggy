package co.edu.udea.certificacion.buggy.rentacarros.stepdefinitions;

import co.edu.udea.certificacion.buggy.rentacarros.exceptions.UnexpectedMessageError;
import co.edu.udea.certificacion.buggy.rentacarros.models.User;
import co.edu.udea.certificacion.buggy.rentacarros.questions.TheMessage;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.OpenTheBrowser;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.SignIn;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.SignUp;
import co.edu.udea.certificacion.buggy.rentacarros.utils.TestDataFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static co.edu.udea.certificacion.buggy.rentacarros.utils.Constants.REGISTERED_USER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

/** Pasos que se reutilizan en varios features (registro, login y voto). */
public class CommonStepDefinitions {

    private static final String REGISTRATION_SUCCESS = "Registration is successful";

    @Given("{word} is on the Buggy Cars Rating home page")
    public void isOnTheBuggyCarsRatingHomePage(String actorName) {
        theActorCalled(actorName).wasAbleTo(OpenTheBrowser.onTheBuggyHomePage());
    }

    @Given("he has registered a new valid account")
    public void heHasRegisteredANewValidAccount() {
        Actor actor = theActorInTheSpotlight();
        User user = TestDataFactory.aNewValidUser();
        actor.remember(REGISTERED_USER, user);
        actor.wasAbleTo(SignUp.withTheData(user));
        // Precondicion verificada: si el registro falla, el escenario se detiene aqui con un mensaje claro
        actor.should(seeThat(TheMessage.ofRegistrationSuccess(), equalTo(REGISTRATION_SUCCESS))
                .orComplainWith(UnexpectedMessageError.class, "The precondition failed: the account could not be registered"));
    }

    @When("he signs in with his credentials")
    public void heSignsInWithHisCredentials() {
        Actor actor = theActorInTheSpotlight();
        User user = actor.recall(REGISTERED_USER);
        actor.attemptsTo(SignIn.withTheCredentialsOf(user));
    }
}
