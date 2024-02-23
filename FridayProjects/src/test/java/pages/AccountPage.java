package pages;

import com.KesifPlus.utility.Driver;
import com.KesifPlus.ui.Utilities;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends CommonPage {

    @FindBy(css = "input[type='text']")
    private WebElement inputZipCode;
    @FindBy(xpath = "//button[contains(text(), 'go')]")
    private WebElement zipCodeBtn;

    public By getByPanelButton(String btnName) {
        return By.xpath("//div/a[.='" + btnName + "']");
    }

    public void addZipCode(String zipCode) {
        try {
            Utilities.sendText(inputZipCode, zipCode);
            zipCodeBtn.click();
            Utilities.waitFor(5);
        } catch (Exception e) {
        }

    }

    public void verifyLeftPanelBtn(String btnName) {
        WebElement element = Driver.getDriver().findElement(getByPanelButton(btnName));
        Assert.assertTrue(element.isEnabled());
    }

    public void clickLeftPanelBtn(String btnName) {
        WebElement element = Driver.getDriver().findElement(getByPanelButton(btnName));
        element.click();
    }

    //---- US 070

    @FindBy(xpath = "//nav/a[contains(text(), 'Logout')]")
    WebElement logoutButton;

    public void clickLogoutButton() {
        logoutButton.click();
    }


}
