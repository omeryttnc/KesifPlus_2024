package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static pages.OrdersPage.orderId;

public class SoldItemsPage extends CommonPage {

    // -------------------------------------------------------------- US_070

    @FindBy(xpath = "//span[contains(text(), 'Checkout ID')]/span")
    private List<WebElement> soldItemIdList;
    @FindBy(xpath = "//p/select")
    private List<WebElement> soldItemStatusList;
    @FindBy(xpath = "(//span[contains(@class, 'notificationIconContainer')])[1]")
    private WebElement notificationBtn;
    @FindBy(xpath = "//div[contains(@class, 'notificationCardContainer')]//p")
    private List<WebElement> notificationDetailList;
    @FindBy(xpath = "//div[contains(@class, 'notificationCardContainer')]//a")
    private List<WebElement> notificationNoticeList;
    @FindBy(xpath = "//span[contains(@class, 'Notifications_slider')]")
    private List<WebElement> notificationReadMarkList;
    @FindBy(xpath = "//span[contains(text(), 'Mark all as read')]")
    private WebElement notificationMarkAllBtn;
    @FindBy(xpath = "//span[.='View all']")
    private WebElement notificationViewAllBtn;
    @FindBy(xpath = "//div[contains(@class, 'filterButton')]")
    private WebElement notificationFilterBtn;
    @FindBy(xpath = "//span[contains(@class, 'notificationDownIcon')]")
    private WebElement notificationDownBtn;
    @FindBy(xpath = "(//div[contains(@class, 'notificationNumContainer')])[1]")
    private WebElement notificationNumContainer;

    public static final String itemName = "Apple";
    public static final String itemPrice = "$1.00";
    public static final String itemQuantity = "5lb";
    public static String soldItemId = "";
    public static int soldItemIndex;

    public void verifySoldItemIsAvailable() {
        String expectedId = orderId.replace("ORDER #", "");
        Assert.assertTrue(hasItemId(expectedId));
    }

    public boolean hasItemId(String expectedId) {
        for (int i = 0; i < soldItemIdList.size(); i++) {
            if (soldItemIdList.get(i).getText().equals(expectedId)) {
                soldItemIndex = i;
                return true;
            }
        }
        return false;
    }

    public void verifySoldItemStatus(String status) {
        String soldStatusNo = soldItemStatusList.get(soldItemIndex).getAttribute(("selectedIndex"));
        String actualStatus = "";
        switch (soldStatusNo){
            case "0" : actualStatus = "In Progress" ; break;
            case "1" : actualStatus = "On Delivery" ; break;
            case "2" : actualStatus = "Completed" ; break;
        }
        Assert.assertEquals(status, actualStatus);
    }

    public void updateStatus(String newStatus){
        WebElement itemStatusDropDown = soldItemStatusList.get(soldItemIndex);
        Select select = new Select(itemStatusDropDown);

        switch (newStatus){
            case "In Progress" : select.selectByIndex(0); break;
            case "On Delivery" : select.selectByIndex(1); break;
            case "Completed" : select.selectByIndex(2); break;

        }

    }

    public void clickNotificationBtn(){
        notificationBtn.click();
    }



}
