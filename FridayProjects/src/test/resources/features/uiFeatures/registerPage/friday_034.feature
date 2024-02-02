@UI
Feature: US_034

  Scenario: As a user, I should be able to check valid and invalid name, surname, email.

    Given User goes to homepage
    And Click the "Register" button on the main Page

    Then Verify that first name entries match the expected result
#       | First Name  | Expected |
        | empty       | false    |
        | Mehmet123   | false    |
        | Mehmet@     | false    |
        | Mehmet      | true     |

    Then Verify that middle name entries match the expected result
#     | Middle Name | Expected |
      | empty       | true     |
      | Emin456     | false    |
      | Emin#       | false    |
      | Emin        | true     |

    Then verify that last name entries match the expected result
#     | Last Name  | Expected Result |
      | empty      | false           |
      | Demir123   | false           |
      | Demir-     | false           |
      | Demir      | true            |

    Then Verify that email entries match the expected result
#     | Email                 | Expected |
      | empty                 | false    |
      | @                     | false    |
      | mehmetemin.com        | false    |
      | mehmet.emin@          | false    |
      | mehmet emin@demir.com | false    |
      | mehmet.emin@demir.com | true     |
