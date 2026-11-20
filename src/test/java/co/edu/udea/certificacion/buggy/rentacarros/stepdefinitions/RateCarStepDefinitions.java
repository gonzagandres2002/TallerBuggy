package co.edu.udea.certificacion.buggy.rentacarros.stepdefinitions;

import co.edu.udea.certificacion.buggy.rentacarros.exceptions.CommentNotRegisteredError;
import co.edu.udea.certificacion.buggy.rentacarros.models.User;
import co.edu.udea.certificacion.buggy.rentacarros.questions.TheComment;
import co.edu.udea.certificacion.buggy.rentacarros.questions.TheMessage;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.OpenTheBrowser;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.SelectTheCar;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.SignIn;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.SignUp;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.VoteForTheCar;
import co.edu.udea.certificacion.buggy.rentacarros.utils.TestDataFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import static co.edu.udea.certificacion.buggy.rentacarros.utils.Constants.REGISTERED_USER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * El "pegante": conecta cada frase del rate_car.feature con el codigo (Tasks y Questions).
 * Given = Arrange, When = Act, Then = Assert.
 */
public class RateCarStepDefinitions {

    private static final String REGISTRATION_SUCCESS = "Registration is successful";

    /** Antes de cada escenario: OnlineCast le da al actor la habilidad de usar el navegador (serenity.conf). */
    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @After
    public void drawTheCurtain() {
        OnStage.drawTheCurtain();
    }

    // ---------- Given: precondiciones ----------

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
        // Se verifica la precondicion: si el registro falla, el escenario se detiene aqui
        actor.should(seeThat(TheMessage.ofRegistrationSuccess(), equalTo(REGISTRATION_SUCCESS)));
    }

    @Given("he signs in with his credentials")
    public void heSignsInWithHisCredentials() {
        Actor actor = theActorInTheSpotlight();
        User user = actor.recall(REGISTERED_USER);
        actor.attemptsTo(SignIn.withTheCredentialsOf(user));
    }

    // ---------- When: acciones ----------

    @When("he selects the model {string} of the popular make")
    public void heSelectsTheModelOfThePopularMake(String model) {
        theActorInTheSpotlight().attemptsTo(SelectTheCar.fromThePopularMake(model));
    }

    @When("he votes for the car leaving the comment {string}")
    public void heVotesForTheCarLeavingTheComment(String comment) {
        theActorInTheSpotlight().attemptsTo(VoteForTheCar.leavingTheComment(comment));
    }

    // ---------- Then: validaciones ----------

    @Then("he should see the message {string}")
    public void heShouldSeeTheMessage(String expectedMessage) {
        theActorInTheSpotlight().should(seeThat(TheMessage.ofTheVote(), equalTo(expectedMessage)));
    }

    @Then("his comment should be registered in the comments of the car")
    public void hisCommentShouldBeRegisteredInTheCommentsOfTheCar() {
        theActorInTheSpotlight().should(
                seeThat(TheComment.isRegisteredInTheCar(), is(true))
                        .orComplainWith(CommentNotRegisteredError.class, CommentNotRegisteredError.MESSAGE));
    }
}
