Background:
Given Create a new photographer.
When Read created photographer.

Scenario: Should create a new photographer and read him, where read photographer should by as created.
Then Created photographer should be as read.

Scenario: Should create a new photographer and update him personal information and check if new data is as we want.
When Update personal information.
Then Check correct data change general.

Scenario: Should create a new photographer and update him scope of work and check if new data is as we want.
When Update scope of work
Then check correct date change scope of work.

Scenario: Should create a new model and update him list achievement and check if new data is as we want.
When Update lists
Then Check correct data change lists.

Scenario: Should create a new photographer and try delete him.
When Deleting photographer.
Then Try read id deleted photographer, if return HTTP response status code NotFound will be ok.