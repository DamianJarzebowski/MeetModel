Feature: Test filter examples

  Background:
    Given Delete all records models

  Scenario Outline: Create a new model and check whether you can find it for the work ranges given in the examples.    Given Create a new model with looking scope of work.
    Given Create a new model from data dateToCreateModel.
    When Try find created model using data examples from table <Act> <CoveredNudity> <Editorial> <Fashion> <Glamour> <MakeUpAndStylization> <Portrait>
    Then Check if was found <Found>

    Examples:
    | Act   | CoveredNudity | Editorial  | Fashion  | Glamour | MakeUpAndStylization  | Portrait |  Found |
    | false |      false    |    false   |   false  |  true   |        false          |   true   |  true  |
    | true  |               |    true    |   true   |  true   |        true           |   true   |  false |
    | false |               |            |   false  |  true   |        false          |   true   |  true  |
    | true  |      false    |    true    |   true   |  true   |        true           |   true   |  false |
    |       |               |            |          |         |                       |          |  true  |
    | true  |      true     |    true    |   true   |  false  |        true           |   false  |  false |
    | true  |      false    |    false   |   false  |  false  |        true           |   false  |  false |

  Scenario Outline: Create multiple models of different ages and try to filter for different age ranges, expecting or not finding a model.
    Given Create a new model with an age <Age>
    When Try find models with age from <From> to <To>
    Then Size collected list will be equal <Size>

    Examples:
      | Age | From | To | Size |
      | 18  | 18   | 18 | 1    |
      | 25  | 20   | 30 | 1    |
      | 18  | 19   | 20 | 0    |
      | 17  | 18   | 18 | 0    |
      | 35  | 25   | 30 | 0    |
      | 40  | 30   | 40 | 1    |

