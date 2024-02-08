package pages;

import myInterface.IPage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static stepDefinitions.Hooks.driver;

public class HomePage extends CommonPage implements IPage {

    @FindBy(css = "[href*='login'].mr-4")
    public WebElement loginButton; // TODO ilerde sorun yasamak icin tekrar ac =driver.findElement(By.cssSelector("[href*='auth'].mr-2"));

    @FindBy(css = ".mr-4,.mr-2")
    public List<WebElement> allTitles;

    @FindBy(css = "nav>a[href^='/account/home']")
    public WebElement accountName_navbar;

    @FindBy(css = ".Toastify__toast ")
    public WebElement toastMessage;

    @Override
    public void goTo() {
        driver.get("https://test.urbanicfarm.com/home");
    }

    @Override
    public void assertUrl() {
        Assert.assertEquals(
                "https://test.urbanicfarm.com/home",
                driver.getCurrentUrl()
        );

    }
}
