Feature: User registration in Buggy Cars Rating
  As a visitor of Buggy Cars Rating
  I want to create an account
  So that I can vote for cars and leave comments about them

  Background:
    Given John is on the Buggy Cars Rating home page

  Scenario: Successful registration with valid data
    When he registers a new valid account
    Then he should see the registration message "Registration is successful"

  Scenario Outline: Registration rejected by the password policy
    When he tries to register an account with the password "<password>"
    Then he should see a registration error containing "<error>"

    Examples:
      | password | error                                   |
      | abc      | minimum field size of 6                 |
      | abcdefgh | Password must have uppercase characters |

  Scenario: Registration rejected when the login already exists
    Given he has registered a new valid account
    When he tries to register again with the same login
    Then he should see a registration error containing "User already exists"

  Scenario: Register button disabled when the passwords do not match
    When he fills the registration form with passwords that do not match
    Then he should see the validation message "Passwords do not match"
    And the register button should be disabled
