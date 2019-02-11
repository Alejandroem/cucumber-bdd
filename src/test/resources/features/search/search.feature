Feature: Main search

  Narrative:
  In order find available flights
  As a client
  I want to be able to search

  Scenario: Initial search
    Given I'm main page
    When I try to find a fly
      | origin  | destination | outbound  | returnDate | passengers|
      | Almería | Alicante    | NEXT_WEEK |            | 1         |
    Then I get available flight
