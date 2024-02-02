package stepDefinitions.UI_StepDefs.HomePage;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import utilities.JSUtils;

public class US_006_AboutUsBtn_stepDefs extends CommonPage {


    @When("User clicks on About Us module")
    public void user_clicks_on_about_us_module() {
        JSUtils.clickElementByJS(getHomePage().AboutUsBtnAtButton);

    }


    @Then("User must verify that the About Us page appears")
    public void user_must_verify_that_the_about_us_page_appears() {
       getAboutUsPage().verifyAboutUsPageDisplayed();

    }


}
