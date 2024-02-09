package utilities;


import manifold.ext.rt.api.This;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import static stepDefinitions.Hooks.driver;

public class BrowserUtilities {
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
        File destination = new File(BrowserUtilities.getTargetPath() + "/" + destinationName + ".png");
        try {
            FileUtils.copyFile(screenshotAs, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void waitForVisibility(WebElement webElement, int time){
        WebDriverWait webDriverWait=new WebDriverWait(driver,Duration.ofSeconds(time));
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
}
