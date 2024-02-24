package stepDefinitions.uiStepDef;

import com.KesifPlus.ui.Utilities;
import enums.COLOR;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.eo.Se;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import pages.CommonPage;

import java.util.List;
import java.util.Map;

import static com.KesifPlus.HooksImp.driver;

public class week4 extends CommonPage {
    @And("user clicks on delivery and pickup settings")
    public void userClicksOnDeliveryAndPickupSettings() {
        getHomePage().deliverySettings_sidebar.click();
    }

    @And("unselect all checkbox")
    public void unselectAllCheckbox() {
        getDeliveryAndPickupSettings().unCheckDeliverySettings();
    }

    @And("user clicks on seller flexible")
    public void userClicksOnSellerFlexible() {
        getDeliveryAndPickupSettings().seller_flexible.click();
    }

    @Then("user verifies web elements with following ids displays correct text")
    public void userVerifiesWebElementsWithFollowingIdsDisplaysCorrectText(DataTable dataTable) {
        List<String> webElementFor = dataTable.column(0);
        List<String> labels = dataTable.column(1);

        for (int i = 0; i < labels.size(); i++) {
            getDeliveryAndPickupSettings().assertLabelWithWebelement(
                    labels.get(i),
                    webElementFor.get(i)
            );

        }
        String name = "omer";
        String surName = "omer";

        getDeliveryAndPickupSettings().printName(name);
        getDeliveryAndPickupSettings().printName(surName);

    }

    @When("user enter address fields")
    public void userEnterAddressFields() {
        // way 1
        getDeliveryAndPickupSettings().freeFlexibleDeliveryRange.clear();
        getDeliveryAndPickupSettings().freeFlexibleDeliveryRange.sendKeys("10");
//        Actions actions = new Actions(driver);
//        actions.sendKeys(Keys.ARROW_UP);
//        String increasedValue = getDeliveryAndPickupSettings().freeFlexibleDeliveryRange.getAttribute("value");
//        Assert.assertEquals("10.01",increasedValue);
        // way 2 extention

        getDeliveryAndPickupSettings().minFreeFlexibleDeliveryOrder.clearAndSend("15");
        getDeliveryAndPickupSettings().perMileCostFlex.clearAndSend("1");
        getDeliveryAndPickupSettings().maxFlexibleDeliveryRange.clearAndSend("20");

    }

    @And("enter some order times")
    public void enterSomeOrderTimes() {
        getDeliveryAndPickupSettings().deliveryBeginTime.sendKeys("0205pm");
        getDeliveryAndPickupSettings().deliveryEndTime.sendKeys("0305pm");
        getDeliveryAndPickupSettings().orderByTime.sendKeys("0405pm");
    }

    @Then("days should be appropriate")
    public void daysShouldBeAppropriate(DataTable table) {
//        // way 1
//        Select select = new Select(getDeliveryAndPickupSettings().deliveryBeginDay);
//        select.selectByVisibleText("Monday");
//        // way 2 extention
//        getDeliveryAndPickupSettings().deliveryEndDay.selectByVisibleText("Tuesday");

        List<Map<String, String>> maps = table.asMaps();
        for (int i = 0; i < maps.size(); i++) {
            String orderBegin = maps.get(i).get("OrderBegin");
            String orderEnd = maps.get(i).get("OrderEnd");
            String delivery = maps.get(i).get("Delivery");
            String errorMessage = maps.get(i).get("ErrorMessage");
            boolean isOK = Boolean.parseBoolean(maps.get(i).get("isOK"));

            getDeliveryAndPickupSettings().deliveryBeginDay.selectByVisibleText(orderBegin);
            getDeliveryAndPickupSettings().deliveryEndDay.selectByVisibleText(orderEnd);
            getDeliveryAndPickupSettings().orderByDay.selectByVisibleText(delivery);
            getDeliveryAndPickupSettings().update.click();

            Utilities.waitForClickablility(getHomePage().toastMessage, 10);
            Assert.assertEquals(
                    errorMessage,
                    getHomePage().toastMessage.getText()
            );

            if (isOK) {
                Assert.assertEquals(
                        COLOR.GREEN.getRgba(),
                        getHomePage().toastMessage.getCssValue("background-color")
                );
            } else {
                Assert.assertEquals(
                        COLOR.RED.getRgba(),
                        getHomePage().toastMessage.getCssValue("background-color")
                );
            }


            getHomePage().toastMessage.click();


        }

    }

    @When("user selects {string} for orderBegin")
    public void userSelectsForOrderBegin(String value) {
        // way 3 utilities
        Utilities.selectByValue(
                getDeliveryAndPickupSettings().deliveryBeginDay,
                value
        );
    }

    @And("user enters input to order begin {string}")
    public void userEntersInputToOrderBegin(String time) {
        getDeliveryAndPickupSettings().deliveryBeginTime.sendKeys(time);
    }

    @And("user selects {string} for orderEnd")
    public void userSelectsForOrderEnd(String value) {
        // way 1 bu da olur methodu
//        Select select = new Select(webElement);
//        select.selectByValue(value);
        // way 2 extention
        getDeliveryAndPickupSettings().deliveryEndDay.selectByValue(value);
    }

    @And("user enters input to order end {string}")
    public void userEntersInputToOrderEnd(String time) {
        getDeliveryAndPickupSettings().deliveryEndTime.sendKeys(time);
    }

    @And("user selects {int} for deliverBy")
    public void userSelectsDeliverByForDeliverBy(int index) {
        getDeliveryAndPickupSettings().orderByDay.selectByIndex(index);
    }


    @And("user enters input to deliver by {string}")
    public void userEntersInputToDeliverBy(String time) {
        getDeliveryAndPickupSettings().orderByTime.sendKeys(time);
    }

    @And("user clicks on update button")
    public void userClicksOnUpdateButton() {
        getDeliveryAndPickupSettings().update.click();
    }

    @Then("user verifies the toast message is {string}")
    public void userVerifiesTheToastMessageIs(String expectedToastMessage) {
        Utilities.waitForClickablility(getHomePage().toastMessage, 10);
        Assert.assertEquals(getHomePage().toastMessage.getText(),
                expectedToastMessage);
    }

    @And("local clear")
    public void localClear() {

        LocalStorage localStorage = ((WebStorage) driver).getLocalStorage();
        localStorage.clear();
        driver.navigate().refresh();

    }


}
