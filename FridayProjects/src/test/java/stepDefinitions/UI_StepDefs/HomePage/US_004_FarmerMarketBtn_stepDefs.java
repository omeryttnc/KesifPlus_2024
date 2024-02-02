package stepDefinitions.UI_StepDefs.HomePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CommonPage;
import utilities.JSUtils;


public class US_004_FarmerMarketBtn_stepDefs extends CommonPage {


    @When("User clicks on the Farmer Market module")
    public void userClicksOnTheFarmerMarketModule() throws InterruptedException {
        JSUtils.clickElementByJS(getHomePage().FarmerMarketBtnAtButton);
        Thread.sleep(3000);

    }


    @Then("User must verify that the Farmers Market page appears")
    public void userMustVerifyThatTheFarmersMarketPageAppears() {
       getFarmerMarketPage().verifyFarmerMarketPage();
    }



}
