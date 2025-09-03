Feature: Ability to display rewards screen

  Scenario: User sees rewards screen
    Given user has "1253" points available
    And has has available rewards
      | id | name       | image           | cost | activatedCount |
      | 0  | Headphones | headphonesImage | 1000 | 0              |
      | 1  | Camera     | cameraImage     | 500  | 1              |
      | 2  | High Five  | broken          | 2000 | 0              |
    When user launches the application
    Then available points section is visible with value of "1253"
    And rewards section is visible with rewards
      | name       | image           | cost | activated | available |
      | Headphones | headphonesImage | 1000 | no        | yes       |
      | Camera     | cameraImage     | 500  | yes       |           |
      | High Five  | placeholder     | 2000 | no        | no        |
