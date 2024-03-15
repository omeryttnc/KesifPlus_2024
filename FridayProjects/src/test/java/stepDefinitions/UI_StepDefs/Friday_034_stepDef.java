package stepDefinitions.UI_StepDefs;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.RegisterPage;

import java.util.List;

import static com.KesifPlus.HooksImp.driver;


public class Friday_034_stepDef {

    RegisterPage rp = new RegisterPage();

    @Given("User goes to homepage")
    public void userGoesToHomepage() {
        driver.get("https://test.urbanicfarm.com/");
    }

    @And("Click the {string} button on the main Page")
    public void clickTheButtonOnTheMainPage(String text) {
        rp.clickMainButton(text);
    }

    @Then("Verify that first name entries match the expected result")
    public void verifyThatFirstNameEntriesMatchTheExpectedResult(DataTable dataTable) {
        List<List<String>> listItems = dataTable.asLists(String.class);
        rp.verifyFirstNames(listItems);
    }

    @Then("Verify that middle name entries match the expected result")
    public void verifyThatMiddleNameEntriesMatchTheExpectedResult(DataTable dataTable) {
        List<List<String>> listItems = dataTable.asLists(String.class);
        rp.verifyMiddleNames(listItems);
    }

    @Then("verify that last name entries match the expected result")
    public void verifyThatLastNameEntriesMatchTheExpectedResult(DataTable dataTable) {
        List<List<String>> listItems = dataTable.asLists(String.class);
        rp.verifyLastNames(listItems);
    }

    @Then("Verify that email entries match the expected result")
    public void verifyThatEmailEntriesMatchTheExpectedResult(DataTable dataTable) {
        List<List<String>> listItems = dataTable.asLists(String.class);
        rp.verifyEmails(listItems);
    }

}
