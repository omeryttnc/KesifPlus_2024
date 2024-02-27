package stepDefinitions.UI_StepDefs.RegisterPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;

import java.util.List;

public class Friday_065_stepDef extends CommonPage {
    @When("Click the {string} button in the main Page")
    public void clickTheButtonInTheMainPage(String btnName) {
        getRegisterPage().clickMainButton(btnName);
    }

    @And("Login with credential and zipCode")
    public void loginWithCredentialAndZipCode(DataTable dataTable) {
        List<List<String>> listItems = dataTable.asLists(String.class);
        getLoginPage().loginWithCredentials(listItems.get(0).get(0), listItems.get(0).get(1));
        getAccountPage().addZipCode(listItems.get(0).get(2));
    }

    @And("Click the {string} button in the main panel")
    public void clickTheButtonInTheMainPanel(String btnName) {
         getAccountPage().verifyLeftPanelBtn(btnName);
         getAccountPage().clickLeftPanelBtn(btnName);
    }

    @And("Verify that user is in the Orders Page")
    public void verifyThatUserIsInTheOrdersPage() {
        getOrdersPage().verifyOrdersPage();
    }

    @And("Select any order")
    public void selectAnyOrder() {
        getOrdersPage().selectOrder();
    }

    @And("Click View order details button for selected order")
    public void clickViewOrderDetailsButtonForSelectedOrder() {
        getOrdersPage().verifyDetailsBtn();
        getOrdersPage().clickDetailBtn();
    }

    @And("Verify that the order details page and order summary are visible")
    public void verifyThatTheOrderDetailsPageAndOrderSummaryAreVisible() {
        getOrdersPage().verifyOrderDetailsPage();
        getOrdersPage().verifyOrderSummary();
    }

    @And("Click Seller Page button in Order Details page")
    public void clickSellerPageButtonInOrderDetailsPage() {
        getOrdersPage().clickSellerPageBtn();
    }

    @And("Verify that seller name is correct in seller page")
    public void verifyThatSellerNameIsCorrectInSellerPage() {
        getOrdersPage().verifySellerName();
    }

    @And("User navigate to previous page")
    public void userNavigateToPreviousPage() {
        getOrdersPage().navigateToBack();
    }

    @And("Click seller's address map")
    public void clickSellerSAddressMap() {
        getOrdersPage().verifyAddressMap();
        getOrdersPage().clickMapButton();
        getOrdersPage().getNewWindow();
    }

    @Then("verify seller map matches with the larger map")
    public void verifySellerMapMatchesWithTheLargerMap() {
        getOrdersPage().getSellerMapCoord();
        getOrdersPage().verifyCoordinates();
    }
}
