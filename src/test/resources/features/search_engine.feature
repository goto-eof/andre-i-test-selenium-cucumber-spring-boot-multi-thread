Feature: Testing search function

  Background: database clean up and restoring status
    Given cleaning database
    And restoring session
    And saving data on db

  Scenario Outline: Check if search engine is returning the corretc result
    When searching in the search engine the project name "<project_name>"
    Then the result is "<result>"

    Examples:
      | project_name            | result                               |
      | it does not exists      |                                      |
      | ares guard              | Ares Guard (Java)                    |
      | europea library client  | Europea Library Client (TypeScript)  |
      | europea library server  | Europea Library Server (Java)        |
      | minerva europass client | Minerva Europass Client (TypeScript) |
      | minerva europass server | Minerva Europass Server (Java)       |
      | o-reshare server        | O-ReShare Server (Java)              |
