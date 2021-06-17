Feature: Testing basic search function

  Scenario Outline: Basic positive test
    Given I enter title <Title> into the search field
    And I click on the searched movie link with URL containing <URL> in the dropdown results
    Then movie page URL is correct

    Examples:
      | Title          | URL              |
      | Swiss Army Man | /title/tt4034354 |
      | The Godfather  | /title/tt0068646 |


  Scenario: Searching for a movie which title has been changed
    Given I enter movie title "The Boat That Rocked" into the search field
    And I click on the searched movie link in the dropdown results
    Then movie page URL is correct


  Scenario: Searching for an invalid title
    Given I enter title not a valid movie title into the search field
    Then "No results found" alert is displayed


  Scenario: Searching for invalid title and pressing the search button
    Given I enter title not a valid movie title into the search field
    And I press the search button
    Then page with no search results is displayed


  Scenario Outline: Searching for movie with clicking on the "All results" link
    Given I enter title <Title> into the search field
    And I click "All results" link
    And I click on the searched movie link with URL containing <URL> in the results
    Then movie URL is correct

    Examples:
      | Title          | URL              |
      | Swiss Army Man | /title/tt4034354 |
      | The Godfather  | /title/tt0068646 |


  Scenario Outline: Searching for movie with clicking the search button
    Given I enter title <Title> into the search field
    And I press the search button
    And I click on the searched movie link with URL containing <URL> in the results
    Then movie URL is correct

    Examples:
      | Title          | URL              |
      | Swiss Army Man | /title/tt4034354 |
      | The Godfather  | /title/tt0068646 |

  Scenario Outline: Searching for movie with incomplete queries and queries with typos
    Given I enter title <Title> into the search field
    And I click on the searched movie link with URL containing <URL> in the dropdown results
    Then movie page URL is correct

    Examples:
      | Title        | URL              |
      | Swissarmyman | /title/tt4034354 |
      | Swiss man    | /title/tt4034354 |
      | Swiss        | /title/tt4034354 |
      | The Gofather | /title/tt0068646 |
      | The God      | /title/tt0068646 |
      | Godf         | /title/tt0068646 |

  Scenario: Searching for the empty query
    Given I press the search button
    Then the corresponding message is displayed