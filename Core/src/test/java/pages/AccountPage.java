package pages;

import myInterface.IPage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static stepDefinitions.Hooks.driver;

public class AccountPage extends CommonPage implements IPage {
    @FindBy(css = "svg.cursor-pointer:last-of-type")
    public WebElement downloadButton;

    @FindBy(css = "svg.cursor-pointer:nth-of-type(1)")
    public WebElement copyButton;

    @FindBy(css = ".btn-outline-warning")
    public WebElement editButton;

    @FindBy(css = "[name='phone']")
    public WebElement phoneNumber;

    @FindBy(css = ".btn-outline-primary")
    public WebElement saveButton;

    @FindBy(css = "input.Profile_imgInput__3CXq7")
    public WebElement chooseFile;


    @FindBy(css = ".rounded-circle.bg-white[alt*='ert']")
    public WebElement flagImage;


    @FindBy(css = "span.AccountLayout_backtoTop__1FvtX")
    public WebElement toggleButton;

    @Override
    public void goTo() {
        driver.get("https://test.urbanicfarm.com/account/home");
    }

    @Override
    public void assertUrl() {
        Assert.assertEquals(
                "https://test.urbanicfarm.com/account/home",
                driver.getCurrentUrl()
        );

    }
}
