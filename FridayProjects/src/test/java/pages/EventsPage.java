package pages;

import com.KesifPlus.ui.Utilities;
import com.KesifPlus.utility.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import stepDefinitions.Hooks;

import static com.KesifPlus.HooksImp.driver;

public class EventsPage extends CommonPage {

    @FindBy (css = "input[type='number']")
    private WebElement inputNumberOfAttendees;
    @FindBy (css = "input[type='checkbox']")
    private WebElement checkBoxTerms;
    @FindBy (css = "button[type='submit']")
    private WebElement approveButton;
    @FindBy (css = "div[role='alert']")
    private WebElement alertMessage;
    @FindBy (xpath = "//button[.='Registered Events']")
    private WebElement registeredEventsBtn;

    JavascriptExecutor js = (JavascriptExecutor) driver;

    public void clickRegisterBtn() {
        String titleText = getMyEventsPage().getMapTitle();
        WebElement element = driver.findElement(By.xpath(
                "//h5[.='" + titleText + "']/..//button[.='Register']"));
        Utilities.scrollAndClickWithJS(element);
    }

    public void verifyApproveTerms(){
        clickApproveButton();
        String expectedMsg = "lütfen bu kutuyu işaretleyin.";
        String actualMsg = (String) js.executeScript("return arguments[0].validationMessage", checkBoxTerms);
        Assert.assertTrue(actualMsg.contains(expectedMsg));

        checkBoxTerms.click();
    }

    public void clickApproveButton(){
        Utilities.scrollAndClickWithJS(approveButton);
    }

    public void verifyNumberOfAttendees(){
        String script = "document.querySelector(\"input[type='number']\").valueAsNumber = arguments[0];";
        js.executeScript(script, 0);

        clickApproveButton();
        String expectedMsg = "must be bigger";
        Utilities.waitForVisibility(alertMessage, 10);
        String actualMsg = alertMessage.getText();
        Assert.assertTrue(actualMsg.contains(expectedMsg));

        js.executeScript(script, 2);
    }

    public void verifyEventIsRegistered(){
        Utilities.scrollAndClickWithJS(registeredEventsBtn);
        String title = getMyEventsPage().getMapTitle();
        WebElement newEventText = driver.findElement(By.xpath(
                "//div[contains(@class, 'attendedEvents')]//h5[.='" + title + "']/../span"));
        Utilities.waitForVisibility(newEventText, 10);
        Assert.assertTrue(newEventText.isDisplayed());
    }




}
