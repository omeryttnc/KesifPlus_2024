package stepDefinitions.uiStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CommonPage;
import pages.HomePage;
import pages.LoginPage;
import utilities.BrowserUtilities;

import static stepDefinitions.Hooks.commonPage;
import static stepDefinitions.Hooks.driver;

public class week1_US002 extends CommonPage {
    HomePage homePage = new HomePage();

    @Given("user goes to home page")
    public void userGoesToHomePage() {
        driver.get("https://test.urbanicfarm.com/");
    }

    @When("user clicks on register button")
    public void userClicksOnRegisterButton() {
        WebElement registerButton = driver.findElement(By.cssSelector("[href*='auth'].mr-2"));
        registerButton.click();
    }

    @Then("assert title is Get Your Locally Sourced Veggies&Fruits from neighbors | Urbanic Farm")
    public void assertTitleIsGetYourLocallySourcedVeggiesFruitsFromNeighborsUrbanicFarm() {
        Assert.assertEquals(
                "Register and start selling your produce! | Urbanic Farm",
                driver.getTitle()
        );
    }


    @When("user clicks on login button")
    public void userClicksOnLoginButton() {
        // way 1
//        homePage.loginButton.click();

        // way 2
        // commonPage.getHomePage().loginButton.click();

        // way 3
        getHomePage().loginButton.click();
    }

    @Then("assert title {string}")
    public void assertTitle(String expectedTitle) {
        // way 1
        Assert.assertEquals(expectedTitle, driver.getTitle());

        // way 2
        BrowserUtilities.assertTitleIfEquals(expectedTitle);

        // way 3
        expectedTitle.assertTitleIfEquals();
        /**
         * Dont
         * Repeat
         * Yourself
         */

    }

    @When("user clicks on {int}")
    public void user_clicks_on(int index) {
        getHomePage().allTitles.get(index).click();

    }

    @Then("assert title {string} from outline")
    public void assertTitleFromOutline(String expectedTitle) {
      //  boolean contains = driver.getTitle().contains(expectedTitle);
        Assert.assertTrue(driver.getTitle().contains(expectedTitle));

    }
}
