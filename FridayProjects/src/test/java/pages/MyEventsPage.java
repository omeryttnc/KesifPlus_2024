package pages;

import com.KesifPlus.ui.Utilities;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import stepDefinitions.Hooks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.KesifPlus.HooksImp.driver;

public class MyEventsPage extends CommonPage {

    private Map<String, String> map;
    public MyEventsPage() {
        map = new LinkedHashMap<>();
        map.put("title", "Fruit Festival");
        map.put("time", "10:00");
        map.put("duration", "240");
        map.put("attendeeLimit", "300");
        map.put("tac", "No terms or conditions");
    }

    public String getMapTitle(){
        return map.get("title");
    }
    public void fillEventForm() {
        for (Map.Entry<String, String> pairs : map.entrySet()){
            WebElement inputArea = driver.findElement(By.id(pairs.getKey()));
            inputArea.sendKeys(pairs.getValue());
        }
        fillAddress();
        fillDate();
        fillOptionals();
    }

    public void fillAddress(){
        WebElement element = driver.findElement(By.id("address2"));
        String addressValue = "San Jose, Kaliforniya 95170, Amerika Birleşik Devletleri";
        element.sendKeys(addressValue);
    }

    public void fillDate(){
        WebElement element = driver.findElement(By.id("date"));
        element.click();
        String strDate = getStringDate();
        element.sendKeys(strDate);
    }

    public void fillOptionals(){
        driver.findElement(By.id("schedule")).sendKeys("Between 10:00 and 15:00");
        driver.findElement(By.id("description")).sendKeys("Local Summer Fruits");
    }

    public String getStringDate(){
        LocalDate localDate = LocalDate.now().plusDays(20);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return localDate.format(formatter);
    }

    public void verifyFields(){
        map.entrySet().stream().forEach(t -> verifyField(t.getKey()));
        verifyAddress();
        verifyDate();
    }

    public void verifyField(String key){
        verifyFormNotSent(key);
    }

    public void verifyAddress(){
        verifyFormNotSent("address2");
        clickButton("Submit");
    }

    public void verifyDate(){
        verifyFormNotSent("date");
    }

    public void verifyFormNotSent(String key){
        WebElement inputArea = driver.findElement(By.id(key));
        String text = key.equals("date") ? getStringDate() : inputArea.getAttribute("value");
        deleteText(inputArea);

        clickButton("Submit");
        verifyInvalidMessage(key);

        sendOldText(key, text);
        Utilities.waitFor(1);
    }

    public void deleteText(WebElement inputArea){
        Utilities.scrollAndClickWithJS(inputArea);
        inputArea.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.BACK_SPACE);
    }

    public void verifyInvalidMessage(String key){
        WebElement element = driver.findElement(By.xpath(
                "//*[@id='" + key + "'][contains(@class, 'invalid')]"));
    }

    public void sendOldText(String key, String text){
        WebElement inputArea = driver.findElement(By.id(key));
        Utilities.scrollAndClickWithJS(inputArea);
        Utilities.sendText(inputArea, text);
    }

    public void verifyMessage(String message){
        WebElement alertElement = driver.findElement(By.xpath(
                "//div[@role='alert'][.='" + message + "']"));
        Utilities.waitForVisibility(alertElement, 5);
        Assert.assertTrue(alertElement.isDisplayed());
    }

    public void verifyNewEvent(){
        WebElement newEventText = driver.findElement(By.xpath(
                "//h5[.='" + map.get("title") + "']/../span"));
        Assert.assertEquals(newEventText.getText(), "NEW");
    }

    @FindBy(xpath = "//div[@style='visibility: visible;']//button[.='Yes']")
    private WebElement popUpYesBtn;
    @FindBy (xpath = "//div[@style='visibility: visible;']//button[.='No']")
    private WebElement popUpNoBtn;
    public void deleteEventAndVerify(){
        Utilities.scrollAndClickWithJS(getDeleteButton());
        verifyNoButton();
        verifyYesButton();
        Assert.assertTrue(hasEventDeleted());
    }

    public void verifyNoButton(){
        Assert.assertTrue(popUpNoBtn.isEnabled());
        Utilities.scrollAndClickWithJS(popUpNoBtn);
        Utilities.scrollAndClickWithJS(getDeleteButton());
    }

    public void verifyYesButton(){
        Assert.assertTrue(popUpYesBtn.isEnabled());
        Utilities.scrollAndClickWithJS(popUpYesBtn);
        verifyMessage("Event deleted");
    }

    public WebElement getDeleteButton(){
        String title = getMyEventsPage().getMapTitle();
        return driver.findElement(By.xpath(
                "//div[contains(@class, 'ScheduledEvents')]//h5[.='" + title + "']/..//button[@name='delete']"));
    }

    public boolean hasEventDeleted(){
        try {
            Utilities.scrollAndClickWithJS(getDeleteButton());
            return false;
        } catch (Exception e) {
            return true;
        }
    }

}
