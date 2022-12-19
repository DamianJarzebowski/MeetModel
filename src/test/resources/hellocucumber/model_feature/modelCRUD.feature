Feature: Testing CRUD Model Api

  Background:
    Given Create a new model.
    When Read created model.

  Scenario: Should create a new model and read him, where  read model should by as created.
    Then Created model should be as read.

  Scenario: Should create a new model and update him personal information and check if new data is as we want.
    When Update personal information.
    Then Check correct data change general.

  Scenario: Should create a new model and update him sizes and check if new data is as we want.
    When Update sizes
    Then Check correct data change sizes.

  Scenario: Should create a new model and update him list skills, achievement, characteristic and check if new data is as we want.
    When Update lists
    Then Check correct data change lists.

  Scenario: Should create a new model and try delete him.
    When Deleting model.
    Then Try read id deleted model, if return HTTP response status code NotFound will be ok.

