Feature: TilePlacement

  Scenario: A player wants to place a tile on an empty board
    Given It is the first turn
      And The board is empty
      And The player has drawn a tile
    When The player places the tile
    Then The tile is placed on the board