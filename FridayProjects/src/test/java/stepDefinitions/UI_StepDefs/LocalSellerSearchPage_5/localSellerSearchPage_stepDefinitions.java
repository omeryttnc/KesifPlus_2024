package stepDefinitions.UI_StepDefs.LocalSellerSearchPage_5;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CommonPage;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.JSUtils;
import utilities.ReusableMethods;

import static stepDefinitions.Hooks.driver;

public class localSellerSearchPage_stepDefinitions extends CommonPage {

    @When("User click to WhatsNearYou button")
    public void userClickToWhatsNearYouButton() throws InterruptedException {
       JSUtils.scrollIntoViewJS(getHomePage().reduceWasteStrength);
        //ReusableMethods.hover(getHomePage().Whats_Near_YouButton);
        //Thread.sleep(3000);
        ReusableMethods.waitForVisibility(getHomePage().Whats_Near_YouButton, 5000);
        ReusableMethods.clickElement(getHomePage().Whats_Near_YouButton);


    }

    @Then("User should be able to reach Seller page")
    public void userShouldBeAbleToReachSellerPage() throws InterruptedException {
        ReusableMethods.waitForPageToLoad(5);
        Assert.assertTrue(getLocalSellerSearchPage().WhatsNearYouPageCheckText.isDisplayed());





    }


    @When("User click to PostYourGoods button")
    public void userClickToPostYourGoodsButton() throws InterruptedException {
        Driver.getDriver().get(ConfigurationReader.getProperty("urlTest"));
        ReusableMethods.waitForPageToLoad(5);

       ReusableMethods.hover(getHomePage().Post_Your_GoodsButton);

        ReusableMethods.waitForVisibility(getHomePage().Post_Your_GoodsButton, 5000);
        ReusableMethods.clickElement(getHomePage().Post_Your_GoodsButton);

    }

    @Then("User should be able to reach relevant page")
    public void userShouldBeAbleToReachRelevantPage() {

        ReusableMethods.waitForPageToLoad(5);
        Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("register"));


    }
}
