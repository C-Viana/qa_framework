@TestSortable
Feature: Sortable

  @TestSortable01
  Scenario: Arrange elements in crescent order
    Given I navigate to "Interactions"
    And open the 'Sortable'
    When I arrange all elements in the following order
      | One | Two | Three | Four | Five | Six |
    Then all positions will be chenged accordingly
