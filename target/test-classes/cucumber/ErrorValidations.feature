
@tag
Feature: Error Valdiations
  I want to use this template for my feature file


  @ErrorValdiation
  Scenario Outline: Login Error Validations
    Given I landed on Eccomerce Page
    And Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed


    Examples: 
      | name  									| password 					|
      | apupatil50@gmail.com 		|     Apu@11 		|
