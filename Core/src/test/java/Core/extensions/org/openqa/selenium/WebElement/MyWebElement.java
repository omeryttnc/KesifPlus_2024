package Core.extensions.org.openqa.selenium.WebElement;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

@Extension
public class MyWebElement {
    public static void helloWorld(@This WebElement thiz) {
        System.out.println("hello world!");
    }

    public static void assertBackgroundColor(@This WebElement webElement, String cssValue) {
        String actualCssValue = webElement.getCssValue("background-color");
        Assert.assertEquals(cssValue, actualCssValue);
    }

    public static void clearAndSend(@This WebElement webElement, String str) {
        webElement.clear();
        webElement.sendKeys(str);
    }

    public static void selectByVisibleText(@This WebElement webElement,String text){
        Select select = new Select(webElement);
        select.selectByVisibleText(text);
    }

    public static void selectByValue(@This WebElement webElement,String value){
        Select select = new Select(webElement);
        select.selectByValue(value);

    } public static void selectByIndex(@This WebElement webElement,int index){
        Select select = new Select(webElement);
        select.selectByIndex(index);

    }
}