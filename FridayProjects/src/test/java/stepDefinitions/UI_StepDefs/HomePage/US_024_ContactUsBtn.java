package stepDefinitions.UI_StepDefs.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.CommonPage;
import utilities.JSUtils;
import utilities.ReusableMethods;

import static stepDefinitions.Hooks.driver;

public class US_024_ContactUsBtn extends CommonPage {
    @Given("User goes to homepage")
    public void userGoesToHomepage() {
        driver.get("https://test.urbanicfarm.com/");
        ReusableMethods.waitForPageToLoad(5);
    }

    @When("User click to Contact US btn at the botton")
    public void userClickToContactUSBtnAtTheBotton() throws InterruptedException {
        JSUtils.scrollDownByJS();
        ReusableMethods.waitForVisibility(getHomePage().ContactUsBtnAtBotton,5000);
        Thread.sleep(3000);
        getHomePage().ContactUsBtnAtBotton.click();
    }

    @Then("User should be able to see relevant page")
    public void userShouldBeAbleToSeeRelevantPage() {
        ReusableMethods.waitForPageToLoad(5);
        Assert.assertEquals("https://test.urbanicfarm.com/contact-us",driver.getCurrentUrl());
        Assert.assertTrue(getContactUsPage().getInTouchText.isDisplayed());

    }

}
