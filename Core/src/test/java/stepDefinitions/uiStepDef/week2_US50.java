package stepDefinitions.uiStepDef;

import enums.USERINFO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import pages.CommonPage;
import utilities.BrowserUtilities;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static stepDefinitions.Hooks.actions;

public class week2_US50 extends CommonPage {
    //    String email = "peummonnemane-5141@yopmail.com";
//    String password = "e*y7G2xhsTVAi5u";
    @Given("buyer login")
    public void buyerLogin() {
        // way 1
//        getLoginPage().loginMethod(
//                USERINFO.BUYER.getEmail(),
//                USERINFO.BUYER.getPassword()
//        );
        // way 2
        //  getLoginPage().loginMethod(USERINFO.BUYER);
    }

    @And("user send {string} as zipcode")
    public void userSendAsZipcode(String zipcode) {
        // way 1
//        getWeeklyOrderPage().zipCode.sendKeys(zipcode);
//        actions.sendKeys(Keys.ENTER).perform();
        // way 2
        getWeeklyOrderPage().sendZipcode(zipcode);
    }

    @When("user clicks on Account button")
    public void userClicksOnAccountButton() {
        getHomePage().accountName_navbar.click();
    }

    @Then("user should be able to on Account page")
    public void userShouldBeAbleToOnAccountPage() {
        getAccountPage().assertUrl();
    }

    @When("user click on copy button")
    public void userClickOnCopyButton() {
        getAccountPage().copyButton.click();
    }

    @Then("user should be able to see {string} toast message")
    public void userShouldBeAbleToSeeToastMessage(String expectedToastMessage) {

        BrowserUtilities.waitForVisibility(getHomePage().toastMessage);
        Assert.assertEquals(expectedToastMessage, getHomePage().toastMessage.getText());
    }

    @And("toast message colour should be green colour")
    public void toastMessageColourShouldBeGreenColour() {
        // way 1
//        String actualCssValue = getHomePage().toastMessage.getCssValue("background-color");
//        String expectedCssValue= "rgba(112, 199, 69, 1)";
//        Assert.assertEquals(expectedCssValue,actualCssValue);

        // way 2
        BrowserUtilities.assertBackgroundColor(getHomePage().toastMessage, "rgba(7, 188, 12, 1)");
        // way 3
        // getHomePage().toastMessage.assertBackgroundColor("rgba(112, 199, 69, 1)");
    }


    @Given("user should delete QRCode.png file if exist")
    public void userShouldDeleteQRCodePngFileIfExist() {
        BrowserUtilities.deleteQRPicture();
    }

    @When("user click on download button")
    public void userClickOnDownloadButton() {
        getAccountPage().downloadButton.click();
    }

    @Then("user should have QRCode.png file on local")
    public void userShouldHaveQRCodePngFileOnLocal() {
        BrowserUtilities.assertQRPicture();
    }

    @When("user clicks on edit button")
    public void userClicksOnEditButton() {
        getAccountPage().editButton.click();
    }

    @Then("Choose file should be visible")
    public void chooseFileShouldBeVisible() {
       assertTrue(getAccountPage().chooseFile.isDisplayed());
    }

    @When("user change the first image")
    public void userChangeTheFirstImage() {
        getAccountPage().chooseFile.sendKeys(BrowserUtilities.getFlagPath("flagCanada"));
    }

    @And("user should take a copy of first image")
    public void userShouldTakeACopyOfFirstImage() {
        File screenshotAs = getAccountPage().flagImage.getScreenshotAs(OutputType.FILE);
        File destination = new File(BrowserUtilities.getTargetPath()+"/firstImage.png");
        try {
            FileUtils.copyFile(screenshotAs,destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BrowserUtilities.getScreenShotOfWebelement(getAccountPage().flagImage,"firstImage");

    }

    @And("user change the second image")
    public void userChangeTheSecondImage() {
        getAccountPage().chooseFile.sendKeys(BrowserUtilities.getFlagPath("flagTurkey"));
    }

    @And("user should take a copy of second image")
    public void userShouldTakeACopyOfSecondImage() {
        BrowserUtilities.getScreenShotOfWebelement(getAccountPage().flagImage,"secondImage");
    }
    @Then("assert that first and second image should be difference")
    public void assertThatFirstAndSecondImageShouldBeDifference() {
    }

    @Given("user should clear phone number")
    public void userShouldClearPhoneNumber() {
    }

    @When("user send new phone number")
    public void userSendNewPhoneNumber() {
    }

    @And("click on save button")
    public void clickOnSaveButton() {
    }

    @When("user clicks on toggle button")
    public void userClicksOnToggleButton() {
    }

    @Then("menu dimension should change")
    public void menuDimensionShouldChange() {
    }


}
