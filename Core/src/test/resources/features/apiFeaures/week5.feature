Business Need: week 5 api create user and change password testi

  @UI
  Scenario: can we create user through api
    When user create user through api
    Then we should be able to login through ui
    And assert you already login

  @UI
  Scenario: can we change password through api
    Given user create user through api
    When user change password through api
    And user enter previous password
    And user clicks on login button
    Then user should be able to see "Invalid creds" toast message
    And toast message colour should be red colour
    When user change original password through api


#    after test we should be return origin password

  Scenario: login with local storage
    Given login with local storage
    And enter zipcode through local storage
    And enter pop up message through local storage

