Feature: Order the new comp

  Scenario Outline:
    Given I am start on product page
    And I configure new comp with parameters <processor> and <ram> and <operationSystem> and <hdd>
    When I open shopping cart
    Then I see comp with parameters <processor> and <ram> and <operationSystem> and <hdd>

    Examples:
      | processor    | ram                      | operationSystem | hdd|
      | "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]" | "8GB [+$60.00]" |"Vista Home"|"320 GB"|
