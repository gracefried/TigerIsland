Feature: TilePlacement

  Scenario: A player wants to place a tile on an empty board
    Given The board is empty
      And The player has drawn a tile
    When The player chooses a location
    Then The tile is placed on the board