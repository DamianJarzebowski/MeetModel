Feature: Test filter examples

  Scenario: Create a new model who will be correct looking scope of work, try find him and check if will be found.
    Given Create a new model with looking scope of work.
    When Try find created model.
    Then Check if was found.

    Scenario Outline: Create
      Given Create a new model with an age <Age>.
      When Try find models with age <From> <To>.
      Then Size collected list will be = <Size>.

      Examples:
      | Age | From |  To |  Size |
      | 18  | 18   |  18 |  1    |
      | 18  | 19   |  20 |  0    |
      | 18  | 18   |  18 |  1    |
      | 17  | 18   |  18 |  0    |
      | 35  | 25   |  30 |  0    |
      | 40  | 30   |  40 |  1    |

