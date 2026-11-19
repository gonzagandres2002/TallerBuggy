Feature: Sign in to Buggy Cars Rating
  As a registered user of Buggy Cars Rating
  I want to sign in with my credentials
  So that I can access the options to vote and comment

  Background:
    Given John is on the Buggy Cars Rating home page

  Scenario: Successful sign in with a registered account
    Given he has registered a new valid account
    When he signs in with his credentials
    Then he should be greeted by his first name

  Scenario: Sign in rejected with a wrong password
    Given he has registered a new valid account
    When he signs in with a wrong password
    Then he should see the login error "Invalid username/password"

  Scenario: Sign in rejected for a login that does not exist
    When he signs in with a login that is not registered
    Then he should see the login error "Invalid username/password"
