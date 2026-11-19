package co.edu.udea.certificacion.buggy.rentacarros.stepdefinitions;

import co.edu.udea.certificacion.buggy.rentacarros.exceptions.UnexpectedMessageError;
import co.edu.udea.certificacion.buggy.rentacarros.models.User;
import co.edu.udea.certificacion.buggy.rentacarros.questions.TheMessage;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.SignIn;
import co.edu.udea.certificacion.buggy.rentacarros.utils.TestDataFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static co.edu.udea.certificacion.buggy.rentacarros.utils.Constants.REGISTERED_USER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;

public class LoginStepDefinitions {

    @When("he signs in with a wrong password")
    public void heSignsInWithAWrongPassword() {
        Actor actor = theActorInTheSpotlight();
        User registeredUser = actor.recall(REGISTERED_USER);
        actor.attemptsTo(SignIn.withTheCredentialsOf(registeredUser.withPassword(TestDataFactory.strongPassword())));
    }

    @When("he signs in with a login that is not registered")
    public void heSignsInWithALoginThatIsNotRegistered() {
        theActorInTheSpotlight().attemptsTo(SignIn.withTheCredentialsOf(TestDataFactory.aNewValidUser()));
    }

    @Then("he should be greeted by his first name")
    public void heShouldBeGreetedByHisFirstName() {
        Actor actor = theActorInTheSpotlight();
        User user = actor.recall(REGISTERED_USER);
        actor.should(seeThat(TheMessage.ofTheGreeting(), equalTo("Hi, " + user.getFirstName()))
                .orComplainWith(UnexpectedMessageError.class, "The user was not signed in"));
    }

    @Then("he should see the login error {string}")
    public void heShouldSeeTheLoginError(String expectedError) {
        theActorInTheSpotlight().should(
                seeThat(TheMessage.ofLoginError(), equalTo(expectedError))
                        .orComplainWith(UnexpectedMessageError.class, UnexpectedMessageError.MESSAGE));
    }
}
