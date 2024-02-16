package stepDefinitions.uiStepDef;

import com.KesifPlus.utility.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.CommonPage;
import com.KesifPlus.utility.DatabaseUtilities;

import static com.KesifPlus.HooksImp.driver;


public class week3_US058 extends CommonPage {
    String eklenecekUrun;

    @And("User goes to Your products-services")
    public void userGoesToYourProductsServices() {
        getHomePage().yourProductService_navbar.click();
    }

    @And("User click on random Hubs")
    public void userClickOnRandomHubs() {
//        int allHubSize = getProductPage().allhubs.size(); // 7 su an ki hub sayim
//        Random random = new Random();
//        int randomHub = random.nextInt(allHubSize); // 0 dan 7 ye kadar bir sayi verdi -> 3
//        getProductPage().allhubs.get(randomHub).click(); // index 3 e click yaptim
        getProductPage().allhubs.get(0).click();
    }

    @When("Product Name, Price, Stock, Unit information added products should be displayed.")
    public void productNamePriceStockUnitInformationAddedProductsShouldBeDisplayed() {
        Assert.assertTrue(getProductPage().addNewProduct_Price.isDisplayed());
        Assert.assertTrue(getProductPage().addNewProduct_Name.isDisplayed());
    }

    enum STATE {
        APPROVED,
        IN_REVIEW,
        REJECTED
    }

    @Then("One of the APPROVED, IN_REVIEW, REJECTED options for each product should be visible on the file.")
    public void oneOfTheAPPROVEDIN_REVIEWREJECTEDOptionsForEachProductShouldBeVisibleOnTheFile() {
        // way 1
        getProductPage().allProductsStatus
                .stream()
                .forEach(t -> {
                    Assert.assertTrue(
                            t.getText().equals(STATE.APPROVED.name()) ||
                                    t.getText().equals(STATE.IN_REVIEW.name()) ||
                                    t.getText().equals(STATE.REJECTED.name())
                    );
                });

        // way 2
        getProductPage().allProductsStatus
                .stream()
                .forEach(week3_US058::assertState);
    }

    public static void assertState(WebElement t) {
        Assert.assertTrue(
                t.getText().equals(STATE.APPROVED.name()) ||
                        t.getText().equals(STATE.IN_REVIEW.name()) ||
                        t.getText().equals(STATE.REJECTED.name())
        );
    }

    @When("User add new product")
    public void userAddNewProduct() {
        eklenecekUrun = getProductPage().eklenebilecekUrun();
        getProductPage().addNewProduct_Name.sendKeys(eklenecekUrun);
        getProductPage().addNewProduct_Price.sendKeys("15");
        getProductPage().addNewProduct_Stock.sendKeys("15");

        Select select = new Select(getProductPage().addNewProduct_Unit);
        // way 1 by index
        // select.selectByIndex(0);
        // way 2 by value
        // select.selectByValue("UNIT_LIBRE");
        // way 3 by visible text
        select.selectByVisibleText("Pound");

        Utilities.scrollAndClick(getProductPage().addNewProduct_Submit);
        Utilities.waitFor(2);
    }

    @Then("In-Review should be visible on the added product.")
    public void inReviewShouldBeVisibleOnTheAddedProduct() {
        String actualState = getProductPage().getState(eklenecekUrun);
        String expectedState = STATE.IN_REVIEW.name();
        Assert.assertEquals(expectedState, actualState);

    }

    @When("User approve last added product from database")
    public void userApproveLastAddedProductFromDatabase() {
        DatabaseUtilities.approveLastProduct();
    }

    @Then("Approved should be visible on the added product.")
    public void approvedShouldBeVisibleOnTheAddedProduct() {
        driver.navigate().back();
        Utilities.waitFor(2);
        driver.navigate().forward();

        String actualState = getProductPage().getState(eklenecekUrun);
        String expectedState = STATE.APPROVED.name();
        Assert.assertEquals(expectedState, actualState);

    }

    @And("It should be possible to click on the product name on the file related to the previously added product.")
    public void itShouldBePossibleToClickOnTheProductNameOnTheFileRelatedToThePreviouslyAddedProduct() {
    }

    @When("The product name is clicked.")
    public void theProductNameIsClicked() {
    }

    @Then("the update-delete button will be displayed.")
    public void theUpdateDeleteButtonWillBeDisplayed() {
    }

    @When("the update option is clicked")
    public void theUpdateOptionIsClicked() {
    }

    @Then("user should be able to see {string} as part of toast message")
    public void userShouldBeAbleToSeeAsPartOfToastMessage(String arg0) {
    }

    @And("user should be able to see product name as part of toast message")
    public void userShouldBeAbleToSeeProductNameAsPartOfToastMessage() {
    }

    @When("the Delete button is clicked.")
    public void theDeleteButtonIsClicked() {
    }

    @Then("Yes-No options should appear.")
    public void yesNoOptionsShouldAppear() {
    }

    @When("Yes option is selected")
    public void yesOptionIsSelected() {
    }

    @And("the product should be removed from the page.")
    public void theProductShouldBeRemovedFromThePage() {
    }
}
