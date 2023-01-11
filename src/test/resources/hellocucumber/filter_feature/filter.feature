Feature: Test filter examples

  Background:
    Given Delete all records models

  Scenario Outline: Create a new model and check whether you can find it for the work ranges given in the examples.    Given Create a new model with looking scope of work.
    Given Create a new model from data dateToCreateModel.
    When Try find created model using data examples from table <Act> <CoveredNudity> <Editorial> <Fashion> <Glamour> <MakeUpAndStylization> <Portrait>
    Then Check if result looking is as expected: <Expected>

    Examples:
      | Act   | CoveredNudity | Editorial | Fashion | Glamour | MakeUpAndStylization | Portrait | Expected |
      | false | false         | false     | false   | true    | false                | true     | true     |
      | true  |               | true      | true    | true    | true                 | true     | false    |
      | false |               |           | false   | true    | false                | true     | true     |
      | true  | false         | true      | true    | true    | true                 | true     | false    |
      |       |               |           |         |         |                      |          | true     |
      | true  | true          | true      | true    | false   | true                 | false    | false    |
      | true  | false         | false     | false   | false   | true                 | false    | false    |

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

  Scenario Outline: Create a new model using dataToCreateModel and check whether you can find him if you use examples data from table.
    Given Create a new model from data dateToCreateModel.
    When Try find created model using data examples experience: <Experience>
    Then Check if result looking is as expected: <Expected>

    Examples:

    | Experience  | Expected |
    | BEGINNER    | false    |
    | SMALL       | true     |
    | MEDIUM      | false    |
    | BIG         | false    |

    Scenario Outline: Create a new model using dataToCreateModel and check whether you can find him if you use examples data from table.
      Given Create a new model from data dateToCreateModel.
      When Try find created model using data examples hair color: <HAIR_COLOR>
      Then Check if result looking is as expected: <Expected>

      Examples:

      | HAIR_COLOR  | Expected |
      | BROWN       | false    |
      | LIGHT_BLOND | true     |
      | BLACK       | false    |
      | WHITE       | false    |
      | DARK_BLONDE | false    |
      | RED         | false    |
      | DIFFERENT   | false    |
