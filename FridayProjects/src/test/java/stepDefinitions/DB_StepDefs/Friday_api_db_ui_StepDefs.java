package stepDefinitions.DB_StepDefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.CommonPage;

public class Friday_api_db_ui_StepDefs extends CommonPage {
    @Given("Add a new item to your products_services through api")
    public void addANewItemToYourProducts_servicesThroughApi() {
        getYourProductsPage().addNewVegetable();
    }

    @Given("update status as APPROVED for the item through database")
    public void updateStatusAsAPPROVEDForTheItemThroughDatabase() {
        getYourProductsPage().updateStatus();
    }

    @And("Click the {string} hub button")
    public void clickTheHubButton(String hubGroupName) {
        clickHubButton(hubGroupName);
    }

    @Then("Verify that the status is updated through ui")
    public void verifyThatTheStatusIsUpdatedThroughUi() {
        getYourProductsPage().verifyStatus();
    }
}
