package stepDefinitions.UI_StepDefs.HomePage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import utilities.JSUtils;

public class US_005_ExploreBtn_stepDefs extends CommonPage {

    @When("User clicks on Explore module")
    public void user_clicks_on_explore_module() throws InterruptedException {

        JSUtils.clickElementByJS(getHomePage().ExploreBtnAtButton);
    }


    @Then("User must verify that the Explore page appears")
    public void user_must_verify_that_the_explore_page_appears() {
        getExplorePage().verifyExplorePage();

    }

}
