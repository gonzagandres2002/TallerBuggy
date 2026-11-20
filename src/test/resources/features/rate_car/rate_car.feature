Feature: Rate a car leaving a comment
  As a user of Buggy Cars Rating
  I want to vote for a car and leave a comment about it
  So that other users can read my opinion before renting it

  Background:
    Given John is on the Buggy Cars Rating home page

  Scenario Outline: A new user signs up, signs in, votes for a car and his comment is registered
    Given he has registered a new valid account
    And he signs in with his credentials
    When he selects the model "<model>" of the popular make
    And he votes for the car leaving the comment "<comment>"
    Then he should see the message "Thank you for your vote!"
    And his comment should be registered in the comments of the car

    Examples:
      | model     | comment                                |
      | Veneno    | Great design and impressive top speed  |
      | AVENTADOR | Comfortable car with a powerful engine |

  Scenario: A visitor without session cannot vote for a car
    When he selects the model "Veneno" of the popular make
    Then he should see the message "You need to be logged in to vote."
