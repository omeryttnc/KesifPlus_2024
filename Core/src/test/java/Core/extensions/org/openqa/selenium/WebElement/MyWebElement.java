package Core.extensions.org.openqa.selenium.WebElement;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

@Extension
public class MyWebElement {
    public static void helloWorld(@This WebElement thiz) {
        System.out.println("hello world!");
    }

    public static void assertBackgroundColor(@This WebElement webElement, String cssValue) {
        String actualCssValue = webElement.getCssValue("background-color");
        Assert.assertEquals(cssValue, actualCssValue);
    }

}