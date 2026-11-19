package co.edu.udea.certificacion.buggy.rentacarros.stepdefinitions;

import co.edu.udea.certificacion.buggy.rentacarros.exceptions.CommentNotRegisteredError;
import co.edu.udea.certificacion.buggy.rentacarros.exceptions.UnexpectedMessageError;
import co.edu.udea.certificacion.buggy.rentacarros.questions.TheComment;
import co.edu.udea.certificacion.buggy.rentacarros.questions.TheMessage;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.SelectTheCar;
import co.edu.udea.certificacion.buggy.rentacarros.tasks.VoteForTheCar;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RateCarStepDefinitions {

    @When("he selects the model {string} of the popular make")
    public void heSelectsTheModelOfThePopularMake(String model) {
        theActorInTheSpotlight().attemptsTo(SelectTheCar.fromThePopularMake(model));
    }

    @When("he votes for the car leaving the comment {string}")
    public void heVotesForTheCarLeavingTheComment(String comment) {
        theActorInTheSpotlight().attemptsTo(VoteForTheCar.leavingTheComment(comment));
    }

    @Then("he should see the vote confirmation {string}")
    public void heShouldSeeTheVoteConfirmation(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheMessage.ofTheVote(), equalTo(expectedMessage))
                        .orComplainWith(UnexpectedMessageError.class, UnexpectedMessageError.MESSAGE));
    }

    @Then("he should see the vote restriction {string}")
    public void heShouldSeeTheVoteRestriction(String expectedMessage) {
        heShouldSeeTheVoteConfirmation(expectedMessage);
    }

    @Then("his comment should be registered in the comments of the car")
    public void hisCommentShouldBeRegisteredInTheCommentsOfTheCar() {
        theActorInTheSpotlight().should(
                seeThat(TheComment.isRegisteredInTheCar(), is(true))
                        .orComplainWith(CommentNotRegisteredError.class, CommentNotRegisteredError.MESSAGE));
    }
}
