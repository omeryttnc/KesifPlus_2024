package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.KesifPlus.HooksImp.driver;

public class DeliveryAndPickupSettings extends CommonPage {


    @FindBy(id = "SELLER_FLEXIBLE")
    public WebElement seller_flexible;

    @FindBy(css = "#freeFlexibleDeliveryRange")
    public WebElement freeFlexibleDeliveryRange;

    @FindBy(css = "#minFreeFlexibleDeliveryOrder")
    public WebElement minFreeFlexibleDeliveryOrder;

    @FindBy(css = "#perMileCostFlex")
    public WebElement perMileCostFlex;

    @FindBy(css = "#maxFlexibleDeliveryRange")
    public WebElement maxFlexibleDeliveryRange;

    @FindBy(css = "[type='submit']")
    public WebElement update;

    @FindBy(css = "#_deliveryBeginDay")
    public WebElement deliveryBeginDay;
    @FindBy(css = "#_deliveryBeginTime")
    public WebElement deliveryBeginTime;
    @FindBy(css = "#_deliveryEndDay")
    public WebElement deliveryEndDay;
    @FindBy(css = "#_deliveryEndTime")
    public WebElement deliveryEndTime;
    @FindBy(css = "#_orderByDay")
    public WebElement orderByDay;
    @FindBy(css = "#_orderByTime")
    public WebElement orderByTime;
    @FindBy(css = ".custom-checkbox input[type='checkbox']")
    public List<WebElement> checkBoxList;


    public void assertLabelWithWebelement(String expectedLabel,String webElementFor){
        String actualLabel = driver.findElement(By.xpath("//label[@for='"+webElementFor+"']")).getText();
        Assert.assertTrue("actual : " +actualLabel +" , expected "+expectedLabel,actualLabel.contains(expectedLabel));
    }


    public void unCheckDeliverySettings(){
        checkBoxList
                .stream()
                .forEach(t->{
                    if (t.isSelected()){
                        t.click();
                    }
                });
    }

    public void printName(String str){
        System.out.println("str = " + str);
    }

}