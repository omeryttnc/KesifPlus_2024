package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LocalSellerSearchPage extends CommonPage {

    @FindBy(xpath = "//div[@class='Results_subContainer__nyuUd']/span")
    public WebElement WhatsNearYouPageCheckText;
}
