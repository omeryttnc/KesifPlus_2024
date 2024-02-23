package stepDefinitions.UI_StepDefs.RegisterPage;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.CommonPage;

public class Friday_070_stepDef extends CommonPage {
    @And("Login as {string}")
    public void loginAs(String user) {
        getLoginPage().loginUserProfile(user);
    }

    @And("Enter zipCode as {string}")
    public void enterZipCodeAs(String zipCode) {
        getAccountPage().addZipCode(zipCode);
    }

    @And("Verify that the order is available in Orders")
    public void verifyThatTheOrderIsAvailableInOrders() {
        getOrdersPage().verifyOrderIsAvailable();
    }

    @And("Verify that the order status is {string}")
    public void verifyThatTheOrderStatusIs(String status) {
        getOrdersPage().verifyOrderStatus(status);
    }

    @And("Click the buyer notifications icon")
    public void clickTheBuyerNotificationsIcon() {
        getOrdersPage().clickNotificationBtn();
    }

    @And("Verify that the message {string} is available in Notifications")
    public void verifyThatTheMessageIsAvailableInNotifications(String notice) {
        getOrdersPage().verifyNotification(notice);
    }

    @Then("User logout")
    public void userLogout() {
        getAccountPage().clickLogoutButton();
    }

    @And("Verify that the item is available in Sold Items")
    public void verifyThatTheItemIsAvailableInSoldItems() {
        getSoldItemsPage().verifySoldItemIsAvailable();
    }

    @And("Verify that the item status is {string}")
    public void verifyThatTheItemStatusIs(String status) {
        getSoldItemsPage().verifySoldItemStatus(status);
    }

    @And("Update the item status as {string}")
    public void updateTheItemStatusAs(String newStatus) {
        getSoldItemsPage().updateStatus(newStatus);
    }

    @And("Click the seller notifications icon")
    public void clickTheSellerNotificationsIcon() {
        getSoldItemsPage().clickNotificationBtn();
    }

    @And("Verify that mark all as read button is functional in Notifications")
    public void verifyThatMarkAllAsReadButtonIsFunctionalInNotifications() {
    }

    @Then("Verify that X button is functional in Notifications")
    public void verifyThatXButtonIsFunctionalInNotifications() {
    }

    @And("Verify that mark all as read button gives correct result")
    public void verifyThatMarkAllAsReadButtonGivesCorrectResult() {
    }
}
