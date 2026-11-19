package co.edu.udea.certificacion.buggy.rentacarros.stepdefinitions;

import co.edu.udea.certificacion.buggy.rentacarros.exceptions.UnexpectedMessageError;
import co.edu.udea.certificacion.buggy.rentacarros.models.User;
import co.edu.udea.certificacion.buggy.rentacarros.questions.TheMessage;
import co.edu.udea.certificacion.buggy.rentacarros.questions.TheRegisterButton;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.AttemptToSignUp;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.OpenTheBrowser;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.SignUp;
import co.edu.udea.certificacion.buggy.rentacarros.utils.TestDataFactory;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

import static co.edu.udea.certificacion.buggy.rentacarros.utils.Constants.REGISTERED_USER;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RegisterUserStepDefinitions {

    @When("he registers a new valid account")
    public void heRegistersANewValidAccount() {
        Actor actor = theActorInTheSpotlight();
        User user = TestDataFactory.aNewValidUser();
        actor.remember(REGISTERED_USER, user);
        actor.attemptsTo(SignUp.withTheData(user));
    }

    @When("he tries to register an account with the password {string}")
    public void heTriesToRegisterAnAccountWithThePassword(String password) {
        User user = TestDataFactory.aNewValidUser().withPassword(password);
        theActorInTheSpotlight().attemptsTo(SignUp.withTheData(user));
    }

    @When("he tries to register again with the same login")
    public void heTriesToRegisterAgainWithTheSameLogin() {
        Actor actor = theActorInTheSpotlight();
        User registeredUser = actor.recall(REGISTERED_USER);
        actor.attemptsTo(
                OpenTheBrowser.onTheBuggyHomePage(),
                SignUp.withTheData(registeredUser.withPassword(TestDataFactory.strongPassword()))
        );
    }

    @When("he fills the registration form with passwords that do not match")
    public void heFillsTheRegistrationFormWithPasswordsThatDoNotMatch() {
        theActorInTheSpotlight().attemptsTo(
                AttemptToSignUp.withPasswordsThatDoNotMatch(TestDataFactory.aNewValidUser()));
    }

    @Then("he should see the registration message {string}")
    public void heShouldSeeTheRegistrationMessage(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheMessage.ofRegistrationSuccess(), equalTo(expectedMessage))
                        .orComplainWith(UnexpectedMessageError.class, UnexpectedMessageError.MESSAGE));
    }

    @Then("he should see a registration error containing {string}")
    public void heShouldSeeARegistrationErrorContaining(String expectedError) {
        theActorInTheSpotlight().should(
                seeThat(TheMessage.ofRegistrationError(), containsString(expectedError))
                        .orComplainWith(UnexpectedMessageError.class, UnexpectedMessageError.MESSAGE));
    }

    @Then("he should see the validation message {string}")
    public void heShouldSeeTheValidationMessage(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheMessage.ofPasswordConfirmation(), equalTo(expectedMessage)));
    }

    @Then("the register button should be disabled")
    public void theRegisterButtonShouldBeDisabled() {
        theActorInTheSpotlight().should(seeThat(TheRegisterButton.isEnabled(), is(false)));
    }
}
