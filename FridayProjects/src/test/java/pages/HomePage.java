package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class HomePage extends CommonPage {

    @FindBy(xpath = "//li/a[.='contact us'][@href='/contact-us']")
    public WebElement ContactUsBtnAtBotton;

    @FindBy(css = ".mr-4+[href='/auth/login']")
    public WebElement loginSegment;

    @FindBy(css = ".section-7_right__16a5T button")
    public List<WebElement> reduceWasteStrengthYourCommunityTab;

    @FindBy(xpath = "//div[@class='section-7_right__16a5T']/p")
    public WebElement reduceWasteStrength;

    @FindBy(css = ".section-7_btn1__YvVpM")
    public WebElement Whats_Near_YouButton;

    @FindBy(css = ".section-7_btn2__2lv_r")
    public WebElement Post_Your_GoodsButton;

    @FindBy(css = "[href='/terms-of-service']" )
    public WebElement TermOfService;

    @FindBy(xpath = "//*[@class='text-center mb-5']" )
    public WebElement TermOfServiceIsDiplayed;

    @FindBy(xpath = "//div/a[@href='/account/weekly-order']")
    public WebElement FarmerMarketBtnAtButton;

    @FindBy(xpath = "//div/a[@href='/explore']")
    public WebElement ExploreBtnAtButton;

    @FindBy(xpath = "//div/a[@href='/about']")
    public WebElement AboutUsBtnAtButton;
   @FindBy(xpath = "//a[@class='d-block']")
    public WebElement AdressButton27;

}
