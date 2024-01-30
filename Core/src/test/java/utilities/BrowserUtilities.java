package utilities;


import org.junit.Assert;

import static stepDefinitions.Hooks.driver;

public class BrowserUtilities {
    public static void dogumYili(Integer yas) {
        System.out.println("dogum yiliniz: " + (2024 - yas));
    }

    public static void assertTitleIfEquals(String expectedTitle) {
        Assert.assertEquals(expectedTitle, driver.getTitle());
    }
}
