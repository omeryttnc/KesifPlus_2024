package com.KesifPlus.ui;

import com.KesifPlus.utility.Driver;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.KesifPlus.HooksImp.driver;

public class Utilities {

    public static boolean filterLessThanFive(String str){
        return str.length() <= 5;
    }

    public static void printName() {
        System.out.println("com.KesifPlus.ui.Utility is called");
    }

    public static void printName(String str){
        System.out.println("name is " + str);
    }

    public static void printMail(String name) {
        System.out.println("print mail called");
        System.out.println(name + "@gmail.com");
    }

    public static void printKesif(){
        System.out.println("kesif plus");
    }

    public static void dogumYili(Integer yas) {
        System.out.println("dogum yiliniz: " + (2024 - yas));
    }

    public static void assertTitleIfEquals(String expectedTitle) {
        Assert.assertEquals(expectedTitle, driver.getTitle());
    }

    public static void assertBackgroundColor(WebElement webElement, String cssValue) {
        String actualCssValue = webElement.getCssValue("background-color");
        Assert.assertEquals(cssValue, actualCssValue);
    }

    /**
     * target dosyasindaki QRCode.png dosyasini siliyor
     */

    public static void deleteQRPicture() {
        File file = new File(getTargetPath());
        File[] files = file.listFiles();
        for (File innerFile : files) {
            if (innerFile.getName().equals("QRCode.png")) {
                innerFile.delete();
                break;
            }
        }
    }


    public static void assertQRPicture() {
        waitFor(2);
        boolean flag = false;
        File file = new File(getTargetPath());
        File[] files = file.listFiles();
        for (File innerFile : files) {
            if (innerFile.getName().equals("QRCode.png")) {
                flag = true;
                break;
            }
        }
        Assert.assertTrue("QRCode.png indirilememis", flag);
    }

    /**
     * local deki target yolunu veriyor
     *
     * @return target yolu
     * @author omeryttnc
     * @since 08.02.2024
     */
    public static String getTargetPath() {
        return System.getProperty("user.dir") + "\\target";
    }

    public static String getFlagPath(String flag) {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\userLogo\\" + flag + ".png";
    }

    /**
     * weblementin ekran goruntusunu alip target alitinda bir dosya olusturuyor
     * @param webElement ekran goruntusunu almak istedigim weblement
     * @param destinationName target dosyasi altinda olsuturacagim png uzantili dosyam
     * @author omeryttnc
     * @since 08.02.2024
     */
    public static void getScreenShotOfWebelement(WebElement webElement, String destinationName) {
        File screenshotAs = webElement.getScreenshotAs(OutputType.FILE);
        File destination = new File(getTargetPath() + "/" + destinationName + ".png");
        try {
            FileUtils.copyFile(screenshotAs, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForVisibility(WebElement webElement, int time){
        WebDriverWait webDriverWait=new WebDriverWait(driver, Duration.ofSeconds(time));
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
    public static void waitForVisibility(WebElement webElement){
        WebDriverWait webDriverWait=new WebDriverWait(driver,Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitFor(int time){
        try {
            Thread.sleep(Duration.ofSeconds(time));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static  void isPictureDifferent(String resim1,String resim2)  {
        BufferedImage image1, image2;
        File path1 = new File(getTargetPath() + "/"+resim1+".png");
        File path2 = new File(getTargetPath() + "/"+resim2+".png");

        try {
            image1 = ImageIO.read(path1);
            image2 = ImageIO.read(path2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ImageDiffer imageDiffer = new ImageDiffer();
        ImageDiff imageDiff = imageDiffer.makeDiff(image1, image2);

        boolean hasDifferent = imageDiff.hasDiff();
        Assert.assertTrue(hasDifferent);

    }

    public static boolean isExist(WebElement webElement) {

        boolean flag;
        try {
            flag = webElement.isDisplayed();
            System.out.println("try");
        } catch (Exception e) {
            System.out.println("catch");
            flag = false;
        }
        return flag;

    }

    public static void scrollAndClick(WebElement webElement){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)",webElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",webElement);
    }

    public static void clickElementByJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        jsexecutor.executeScript("arguments[0].click();", element);
    }

    public static String getTitleByJS() {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        String title = jsexecutor.executeScript("return document.title;").toString();
        return title;
    }

    public static void scrollDownByJS() {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        jsexecutor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
        waitFor(3);
    }

    public static void scrollAllUpByJS() {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");
    }

    public static void scrollIntoViewJS(WebElement element) {
        JavascriptExecutor jsexecutor = ((JavascriptExecutor) Driver.getDriver());
        jsexecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void changeColor(String color, WebElement element) {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
        javascriptExecutor.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void flash(WebElement element) {
        String bgColor = element.getCssValue("backgroundcolor");
        for (int i = 0; i < 5; i++) {
            changeColor("rgb(0,200,0", element);
            changeColor(bgColor, element);
        }
    }

    public static void generateAlert(String message) {
        JavascriptExecutor javascriptExecutor = ((JavascriptExecutor) Driver.getDriver());
        javascriptExecutor.executeScript("alert('" + message + "')");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeJScommand(WebElement element, String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command, element);
    }

    public static void executeJScommand(String command) {
        JavascriptExecutor jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript(command);
    }

    public static void setValueByJS(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
    }

    public static void getValueByJS(String idOfElement) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        String value = js.executeScript("return document.getElementById('" + idOfElement + "').value").toString();
        System.out.println(value);
    }

    public static void addBorderWithJS(WebElement element, String borderStyle) {
        JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
        js.executeScript("arguments[0].style.border='" + borderStyle + "'", element);
    }

    private static WebDriverWait wait;
    static Actions actions = new Actions(driver);

    public static String getScreenshot() {
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String target = System.getProperty("user.dir") + "/test-output/Screenshots/" + date + ".png";
        File finalDestination = new File(target);
        try {
            FileUtils.copyFile(source, finalDestination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return target;
    }

    public static Object switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return null;
            }
        }
        Driver.getDriver().switchTo().window(origin);
        return null;
    }

    public static void hover(WebElement element) {
        actions.moveToElement(element).perform();
    }

    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }


    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void scrollToElement(WebElement element) {
        ((JavascriptExecutor) Driver.getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        waitForVisibility(element);
    }

    public static void switchToWindow(int windowNumber) {
        List<String> list = new ArrayList<>(Driver.getDriver().getWindowHandles());
        Driver.getDriver().switchTo().window(list.get(windowNumber));
    }

    public static void scrollAndClickWithJS(WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", webElement);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", webElement);
    }


    public static void sendText(WebElement element, String text) {
        try {
            waitForClickablility(element, 15).sendKeys(text);
        } catch (ElementNotInteractableException e) {
            scrollToElement(element);
            sendText(element, text);
        }
    }

    public static void waitAndClickElement(WebElement element, int seconds){
        for (int i=0; i<seconds; i++){
            try {
                element.click();
                break;
            }catch (Exception e) {
                waitFor(1);
            }
        }
    }



}
