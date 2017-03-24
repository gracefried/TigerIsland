Feature: CreateSettlement

  Scenario: A player wants to create a new settlement
    Given There is at least one tile on the board
    When The player chooses a valid location
    Then The location receives 1 villager
      And The player removes 1 villager from their inventory