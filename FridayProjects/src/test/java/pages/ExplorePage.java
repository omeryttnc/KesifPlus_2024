package pages;

import org.junit.Assert;

import static stepDefinitions.Hooks.driver;

public class ExplorePage extends CommonPage{

    public void verifyExplorePage() {
        String expectedUrl = "https://test.urbanicfarm.com/explore";
        String actualUrl = driver.getCurrentUrl();
        System.out.println(actualUrl);
        Assert.assertEquals(expectedUrl, actualUrl);
    }

}
