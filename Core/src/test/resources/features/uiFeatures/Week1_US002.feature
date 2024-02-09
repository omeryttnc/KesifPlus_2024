Business Need: week 2


  Background: week 2 hazirlik
    Given user goes to home page

  @UI
  Scenario: check register title is Get Your Locally Sourced Veggies&Fruits from neighbors | Urbanic Farm
    When user clicks on register button
    Then assert title is Register and start selling your produce! | Urbanic Farm

  @UI
  Scenario: check login title
    When user clicks on login button
    Then assert title "Login and start selling your produce! | Urbanic Farm"

  @UI
  Scenario Outline: check titles
    When user clicks on <index>
    Then assert title "<string>" from outline
    Examples:
      | index | string                                   |
      | 6     | Register and start selling your produce! |
      | 5     | Login and start selling your produce!    |
      | 4     | Contact                                  |
      | 3     | About                                    |


