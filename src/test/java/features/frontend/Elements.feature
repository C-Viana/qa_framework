@TestElements
Feature: Elements

  @TestElements01
  Scenario: Create a single registry
    Given I navigate to "Elements"
    And open the 'Web Tables'
    When I create 1 new registries
    Then the new lines will be persisted

  @TestElements02
  Scenario: Edit a single registry
    Given I navigate to "Elements"
    And open the 'Web Tables'
    When I edit registries with the following data
      | Search             | First Name | Last Name | Email                | Age | Salary | Department |
      | cierra@example.com | Cierra     | Vega      | cierra41@example.com |  41 |  11596 | Investmens |
    Then the changes will be persisted

  @TestElements03
  Scenario: Delete a single registry
    Given I navigate to "Elements"
    And open the 'Web Tables'
    When I delete registries with the following data
      | Search             |
      | cierra@example.com |
    Then the registries will no more be available

  @TestElements04
  Scenario: Create a single registry
    Given I navigate to "Elements"
    And open the 'Web Tables'
    When I create 12 new registries
    Then the new lines will be persisted
    And I can delete all these registries
