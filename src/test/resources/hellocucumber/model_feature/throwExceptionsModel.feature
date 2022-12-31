Feature: Testing CRUD Model, when should throws exceptions

  Background:
    Given Delete all records models
    Given Create a new model.

  Scenario: Should if NotFoundException is throw for no exist id and message is correct.
    Then Check if throws NotFoundException for not exist id and message is as we want

