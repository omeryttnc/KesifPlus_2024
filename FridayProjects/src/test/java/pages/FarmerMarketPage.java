package pages;

import org.junit.Assert;

import static stepDefinitions.Hooks.driver;

public class FarmerMarketPage extends CommonPage{


    public void verifyFarmerMarketPage() {
        String expectedUrl = "https://test.urbanicfarm.com/account/weekly-order";
        String actualUrl = driver.getCurrentUrl();
        System.out.println(actualUrl);
        Assert.assertEquals(expectedUrl, actualUrl);
    }
}
