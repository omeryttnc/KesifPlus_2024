package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutUsPage extends CommonPage {

    @FindBy(xpath = "(//h2[@class='Slider_text__2tB3Y Slider_title__1RahK'])[1]")
    public WebElement getAboutUsText;


    public void verifyAboutUsPageDisplayed(){

        Assert.assertTrue(getAboutUsPage().getAboutUsText.isDisplayed());
    }


}
