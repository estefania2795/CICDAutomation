
@tag
Feature: Login error from Ecommerce Website
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page
      
   @ErrorValidation
   Scenario Outline: Incorrect email or password msg match
  
    Given Logged in with username <name> and password <password>
    Then "Incorrect email or password." Message is displayed

    Examples: 
      | name                  | password    | 
      | anshika@gmail.com     | Iamki000    | 
