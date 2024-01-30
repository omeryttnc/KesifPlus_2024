package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends CommonPage {

    @FindBy(css = "[href*='login'].mr-4")
    public WebElement loginButton; // TODO ilerde sorun yasamak icin tekrar ac =driver.findElement(By.cssSelector("[href*='auth'].mr-2"));

    @FindBy(css = ".mr-4,.mr-2")
    public List<WebElement> allTitles;

}
