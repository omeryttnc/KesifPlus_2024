package pages;

import com.KesifPlus.ui.Utilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static stepDefinitions.Hooks.actions;

public class WeeklyOrderPage extends CommonPage {
    @FindBy(css = "[name='zipcode']")
    public WebElement zipCode;

    public void sendZipcode(String zipcode) {
        Utilities.waitForVisibility(zipCode);
        zipCode.sendKeys(zipcode);
        actions.sendKeys(Keys.ENTER).perform();
    }
}
