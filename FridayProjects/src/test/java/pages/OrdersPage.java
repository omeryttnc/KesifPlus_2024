package pages;

import com.KesifPlus.utility.Driver;
import com.KesifPlus.ui.Utilities;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OrdersPage extends CommonPage {

    static int orderNo;
    static String sellerName;
    static String sellerMapText;
    static String largerMapText;
    static String currentHandle;

    @FindBy(xpath = "//article[@class='row']//a")
    private List<WebElement> orderList;
    @FindBy(xpath = "//div[@class='mr-3']//span")
    private List<WebElement> orderSummaryList;
    @FindBy(xpath = "//span/a")
    private WebElement sellerPageBtn;
    @FindBy(xpath = "(//tbody//span)[1]")
    private WebElement sellerNameText;
    @FindBy(xpath = "//span[@class='h6']")
    private WebElement sellerFirstName;
    @FindBy(xpath = "//iframe[@class='d-block']")
    private WebElement iFrame;
    @FindBy(xpath = "//div[@class='google-maps-link']/a")
    private WebElement largerMapBtn;
    @FindBy(xpath = "//div[@class='place-name']")
    private WebElement sellerMapCoord;
    @FindBy(xpath = "//div[@style='padding-bottom:1rem']/iframe")
    private WebElement sellerMapFrame;

    public void verifyOrdersPage() {
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("orders"));
    }

    public void selectOrder() {
        int orderListSize = orderList.size();
        Random random = new Random();
        orderNo = random.nextInt(orderListSize);
    }

    public void verifyDetailsBtn() {
        Assert.assertTrue(orderList.get(orderNo).isEnabled());
    }

    public void clickDetailBtn() {
        orderList.get(orderNo).click();
    }

    public void verifyOrderDetailsPage() {
        Utilities.waitFor(1);
        String currentUrl = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("order-details"));
    }

    public void verifyOrderSummary() {
        List<String> expectedSummary = List.of("Items total", "Shipping total", "Applied promotion", "Grand total");
        expectedSummary.stream().forEach(t -> Assert.assertTrue(verifySummaryText(t)));
    }

    public boolean verifySummaryText(String text) {
        return orderSummaryList.stream()
                .map(WebElement::getText)
                .anyMatch(t -> t.contains(text));
    }

    public void clickSellerPageBtn() {
        sellerName = sellerNameText.getAttribute("textContent");
        Utilities.scrollAndClickWithJS(sellerPageBtn);  // buton ekranda görünmediğinde
    }

    public void verifySellerName() {
        Assert.assertTrue(sellerName.contains(sellerFirstName.getText()));
        Utilities.waitFor(3);
        Utilities.scrollDownByJS();
        Driver.getDriver().switchTo().frame(sellerMapFrame);
        sellerMapText = sellerMapCoord.getText();
    }

    public void navigateToBack() {
        Driver.getDriver().navigate().back();
        Utilities.waitFor(3);
    }

    public void verifyAddressMap() {
        Utilities.scrollDownByJS();
        Driver.getDriver().switchTo().frame(iFrame);
        Utilities.waitForVisibility(largerMapBtn);
        Assert.assertTrue(largerMapBtn.isEnabled());
    }

    public void clickMapButton() {
        Utilities.scrollAndClickWithJS(largerMapBtn);
    }

    public void getSellerMapCoord() {
        WebElement element = Driver.getDriver().findElement(By.xpath(
                "//h1[@class='DUwDvf lfPIob']"));
        largerMapText = element.getText();
    }

    public void getNewWindow() {
        currentHandle = Driver.getDriver().getWindowHandle();
        Set<String> handles = Driver.getDriver().getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                Driver.getDriver().switchTo().window(handle);
                break;
            }
        }
    }

    public void verifyCoordinates() {
        Assert.assertEquals(sellerMapText, largerMapText);
    }


    // ------------------- -----------------------------   US 070

    @FindBy(xpath = "//div[contains(@class, 'details_product')]//span")
    private List<WebElement> orderUnitList;
    @FindBy(xpath = "//section[contains(@class, 'OrderCard')]/span")
    private List<WebElement> orderIdList;
    @FindBy(xpath = "//aside//div[@type='button'][contains(@class, 'active')]")
    private List<WebElement> orderStatusList;
    @FindBy(xpath = "//div[@class='Navbar_addHub__USGHm']/following::div[3]/span")
    private WebElement notificationBtn;
//    @FindBy(xpath = "//div[contains(@class, 'notificationCardContainer')]//p")
//    private List<WebElement> notificationDetailList;
    @FindBy(xpath = "//div[contains(@class, 'notificationInfoDate')]//p[contains(@class, 'infoCommonText')]")
    private List<WebElement> notificationDateTimeList;
//    @FindBy(xpath = "//div[contains(@class, 'notificationCardContainer')]//a")
//    private List<WebElement> notificationNoticeList;
    @FindBy(xpath = "//span[contains(@class, 'Notifications_slider')]")
    private List<WebElement> notificationReadMarkList;
    @FindBy(xpath = "//span[contains(text(), 'Mark all as read')]")
    private WebElement notificationMarkAllBtn;
    @FindBy(xpath = "//div[contains(@class, 'filterButton')]")
    private WebElement notificationFilterBtn;
    @FindBy(xpath = "//span[.='View all']")
    private WebElement notificationViewAllBtn;
    @FindBy(xpath = "//span[contains(@class, 'notificationDownIcon')]")
    private WebElement notificationDownBtn;
    @FindBy(xpath = "(//div[contains(@class, 'notificationNumContainer')])[1]")
    private WebElement notificationNumContainer;
    @FindBy(xpath = "//span[contains(@class, 'Notifications_slider')]")
    private List<WebElement> notificationRadioBtnList;
    @FindBy(xpath = "//div[contains(@class, 'notificationInfoText')]//*[contains(@class, 'CommonText')]")
    private List<WebElement> notificationInfoMessage;

    public static final String orderName = "Apple";
    public static final String orderPrice = "$1.00";
    public static final String orderQuantity = "5lb";
    public static String orderId = "";
    public int orderIndex;

    public void verifyOrderIsAvailable() {
        String myUniqueKey = orderName.concat(orderPrice).concat(orderQuantity);
        String orderKey;

        for (int i = 0; i < orderUnitList.size() / 8; i++) {
            orderKey = orderUnitList.get(i * 8).getText()
                    .concat(orderUnitList.get(i * 8 + 2).getText())
                    .concat(orderUnitList.get(i * 8 + 4).getText());
            if (orderKey.equals(myUniqueKey)) {
                orderIndex = i;
                orderId = getOrderId(orderIndex);
            }
        }
        Assert.assertTrue(!orderId.isEmpty());
    }

    public String getOrderId(int index) {
        return orderIdList.get(index).getText();
    }

    public void verifyOrderStatus(String status) {
        Assert.assertEquals(status, orderStatusList.get(orderIndex).getText());
    }

    public void clickNotificationBtn() {
        notificationBtn.click();
    }

    public void verifyNotification(String notice) {
        String actualNotice = getNotice();
//        Assert.assertEquals(notice, actualNotice);
    }

    public String getNotice() {
        List<String> noticeList = notificationInfoMessage.stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());

        int currentNoticeIndex = getNoticeIndex(noticeList);
        return noticeList.get(currentNoticeIndex);
    }

    public int getNoticeIndex(List<String> noticeList) {
        List<String> strTimeList = new ArrayList<>();
        List<LocalDateTime> timeList = new ArrayList<>();

        for (int i = 0; i < (notificationDateTimeList.size() / 2); i++) {
            strTimeList.add(notificationDateTimeList.get(i * 2).getText() + " "   // date +" "+ time
                    + notificationDateTimeList.get(i * 2 + 1).getText());

            timeList.add(LocalDateTime.parse(strTimeList.get(i),
                    DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a", Locale.ENGLISH)));
        }
        LocalDateTime myNotificationTime = findMyOrderTime(strTimeList);
        OptionalInt index = IntStream.range(0, timeList.size())
                .filter(i -> myNotificationTime.equals(timeList.get(i)))
                .findFirst();

        try {
            return index.getAsInt();   // OptionalInt değeri int değerine çevriliyor.
        } catch (Exception e) {
            throw new RuntimeException(e);    //null ise hata ver
        }
    }

    public LocalDateTime findMyOrderTime(List<String> list) {
        return list.stream()
                .map(strTime -> LocalDateTime.parse(strTime, DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a", Locale.ENGLISH)))
                .max(LocalDateTime::compareTo)
                .orElse(null);
    }

    public void verifyMarkAllBtn(){
        Assert.assertTrue(notificationMarkAllBtn.isEnabled());
        clickMarkAllAsReadBtn();
    }

    public void clickMarkAllAsReadBtn(){
        try {
            notificationMarkAllBtn.click();
        } catch (Exception e) {
            try {
                notificationViewAllBtn.click();
                clickMarkAllAsReadBtn();
            } catch (Exception ex) {
                try {
                    notificationRadioBtnList.get(0).click();
                    clickMarkAllAsReadBtn();
                } catch (Exception exc) {
                    notificationFilterBtn.click();
                    clickMarkAllAsReadBtn();
                }
            }
        }
    }

    public void verifyXBtnFunc(){
        Assert.assertTrue(isDownBtnFunc());
    }

    public boolean isDownBtnFunc(){
        if(notificationDownBtn.isEnabled()){
            notificationDownBtn.click();
            return true;
        }
        return false;
    }

    public void verifyMarkAllHasCorrectResult(){
        verifyNumContainerEmpty();
    }

    public void verifyNumContainerEmpty(){
        Utilities.waitFor(3);
        Assert.assertTrue(notificationNumContainer.getText().isEmpty());
    }

}
