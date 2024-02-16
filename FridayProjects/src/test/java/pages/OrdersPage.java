package pages;

import com.KesifPlus.utility.Driver;
import com.KesifPlus.utility.Utilities;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.Set;
import java.util.List;
import java.util.Random;

public class OrdersPage extends CommonPage {

    static int orderNo;
    static String sellerName;
    static String sellerMapText;
    static String largerMapText;
    static String currentHandle;

    @FindBy(xpath = "//article[@class='row']//a")
    private List<WebElement> orderList;
    @FindBy (xpath = "//div[@class='mr-3']//span")
    private List<WebElement> orderSummaryList;
    @FindBy (xpath = "//span/a")
    private WebElement sellerPageBtn;
    @FindBy (xpath = "(//tbody//span)[1]")
    private WebElement sellerNameText;
    @FindBy (xpath = "//span[@class='h6']")
    private WebElement sellerFirstName;
    @FindBy (xpath = "//iframe[@class='d-block']")
    private WebElement iFrame;
    @FindBy (xpath = "//div[@class='google-maps-link']/a")
    private WebElement largerMapBtn;
    @FindBy (xpath = "//div[@class='place-name']")
    private WebElement sellerMapCoord;
    @FindBy (xpath = "//div[@style='padding-bottom:1rem']/iframe")
    private WebElement sellerMapFrame;

    public void verifyOrdersPage(){
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("orders"));
    }

    public void selectOrder(){
        int orderListSize = orderList.size();
        Random random = new Random();
        orderNo = random.nextInt(orderListSize);
    }

    public void verifyDetailsBtn(){
        Assert.assertTrue(orderList.get(orderNo).isEnabled());
    }

    public void clickDetailBtn(){
        orderList.get(orderNo).click();
    }

    public void verifyOrderDetailsPage(){
        Utilities.waitFor(1);
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("order-details"));
    }

    public void verifyOrderSummary(){
    List<String> expectedSummary = List.of("Items total", "Shipping total", "Applied promotion", "Grand total");
    expectedSummary.stream().forEach(t -> Assert.assertTrue(verifySummaryText(t)));
    }

    public boolean verifySummaryText(String text){
        return orderSummaryList.stream()
                .map(WebElement::getText)
                .anyMatch(t -> t.contains(text));
    }

    public void clickSellerPageBtn(){
        sellerName = sellerNameText.getAttribute("textContent");
        Utilities.scrollAndClickWithJS(sellerPageBtn);  // buton ekranda görünmediğinde
    }

    public void verifySellerName(){
        Assert.assertTrue(sellerName.contains(sellerFirstName.getText()));
        Utilities.waitFor(3);
        Utilities.scrollDownByJS();
        Driver.getDriver().switchTo().frame(sellerMapFrame);
        sellerMapText = sellerMapCoord.getText();
    }

    public void navigateToBack(){
        Driver.getDriver().navigate().back();
        Utilities.waitFor(3);
    }

    public void verifyAddressMap(){
        Utilities.scrollDownByJS();
        Driver.getDriver().switchTo().frame(iFrame);
        Utilities.waitForVisibility(largerMapBtn);
        Assert.assertTrue(largerMapBtn.isEnabled());
    }

    public void clickMapButton(){
        Utilities.scrollAndClickWithJS(largerMapBtn);
    }

    public void getSellerMapCoord(){
        WebElement element = Driver.getDriver().findElement(By.xpath(
                "//h1[@class='DUwDvf lfPIob']"));
        largerMapText = element.getText();
    }

    public void getNewWindow(){
        currentHandle = Driver.getDriver().getWindowHandle();
        Set<String> handles = Driver.getDriver().getWindowHandles();
        for (String handle : handles){
            if(!handle.equals(currentHandle)){
                Driver.getDriver().switchTo().window(handle);
                break;
            }
        }
    }

    public void verifyCoordinates(){
        Assert.assertEquals(sellerMapText, largerMapText);
    }
}
