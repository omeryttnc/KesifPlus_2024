package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactUsPage extends CommonPage {

    @FindBy(xpath = "//h2[@class='ContactUs_title__elsQ3']")
    public WebElement getInTouchText;
}
