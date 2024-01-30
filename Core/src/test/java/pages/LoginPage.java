package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends CommonPage{
    @FindBy(css = "a")
    public WebElement denemeLo;
}
