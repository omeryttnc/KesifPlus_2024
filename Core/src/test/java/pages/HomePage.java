package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static stepDefinitions.Hooks.driver;

public class HomePage extends CommonPage {

    @FindBy(css = "[href*='login'].mr-4")
    public WebElement loginButton; // TODO ilerde sorun yasamak icin tekrar ac =driver.findElement(By.cssSelector("[href*='auth'].mr-2"));

    @FindBy(css = ".mr-4,.mr-2")
    public List<WebElement> allTitles;

}
