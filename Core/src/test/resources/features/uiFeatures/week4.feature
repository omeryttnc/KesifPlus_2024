#Delivery type-Seller flexible delivery
#All menus in the relevant area should be functional and appropriate options should be selected.
#Free Delivery Range (mile) should be 0-1000. Alert should be seen on different selections
#Minimum Order Amount For Free Delivery($) should be 0-10000. Alert should be seen on different selections
#Per Mile Cost ($) should be 0-5. Alert should be seen in different selections
#Maximum Delivery Range (mile) should be 0-1000. Alert should be seen on different selections
#A suitable day must be selected for the Order Begin menu.
#Time menu should work correctly
#A suitable day must be selected for the Order End menu.
#Time menu should work correctly
#A suitable day must be selected for the Deliver By menu
#Time menu should work correctly
#Your delivery settings have been succesfully updated alert should appear


@UI
@buyerLogin
Feature:
  Scenario: checkBox
#    Given User login as Seller
    And user send "95109" as zipcode
    When user clicks on Account button
    And user clicks on delivery and pickup settings
    And unselect all checkbox
    And user clicks on seller flexible

    Then user verifies web elements with following ids displays correct text
      | freeFlexibleDeliveryRange    | Free Delivery Range (mile) :               |
      | minFreeFlexibleDeliveryOrder | Minimum Order Amount For Free Delivery($) : |
      | perMileCostFlex              | Per Mile Cost ($) :                        |
      | maxFlexibleDeliveryRange     | Maximum Delivery Range (mile) :            |

    When user enter address fields
    And enter some order times
    Then days should be appropriate
      | OrderBegin | OrderEnd | Delivery  | ErrorMessage                                                                     | isOK  |
      | Monday     | Tuesday  | Wednesday | Your delivery settings have been successfully updated                            | true  |
      | Tuesday    | Monday   | Thursday  | At the 1. line, The end time of order must be later than the start time of order | false |
      | Saturday   | Monday   | Monday    | At the 1. line, The end time of order must be later than the start time of order | false |
      | Tuesday    | Thursday | Sunday    | Your delivery settings have been successfully updated                            | true  |


  Scenario Template: TC003 - time day
    And user send "95109" as zipcode
    When user clicks on Account button
    And user clicks on delivery and pickup settings
    And unselect all checkbox
    And user clicks on seller flexible
    And enter some order times

    When user selects "<orderBegin>" for orderBegin
    And user enters input to order begin "<beginTime>"
    And user selects "<orderEnd>" for orderEnd
    And user enters input to order end "<endTime>"
    And user selects <deliverBy> for deliverBy
    And user enters input to deliver by "<deliverTime>"
    And user clicks on update button
    Then user verifies the toast message is "<toastMessage>"
    And local clear
    Scenarios:
      | orderBegin | beginTime | orderEnd | endTime | deliverBy | deliverTime | toastMessage                                                                     |
      | 6          | 0205pm    | 3        | 0306pm  | 2         | 0306pm      | At the 1. line, The end time of order must be later than the start time of order |
      | 2          | 0205pm    | 3        | 0205am  | 2         | 0306pm      | At the 1. line, The end time of order must be later than the start time of order |
      | 3          | 0205pm    | 6        | 0306pm  | 2         | 0306pm      | Your delivery settings have been successfully updated                            |



