package stepDefinitions;

import com.KesifPlus.HooksImp;
import com.KesifPlus.database.DatabaseUtilities;
import com.KesifPlus.utility.ConfigurationReader;
import com.KesifPlus.utility.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.CommonPage;

import static com.KesifPlus.HooksImp.driver;


public class Hooks {

    public static CommonPage commonPage;
    public static Actions actions;

    @Before(value = "@headless", order = 0)
    public void setIsHeadless() {
        HooksImp.isHeadless = true;
    }

    @Before(value = "@firefox", order = 0)
    public void setIsFirefox() {
        HooksImp.browserType = "firefox";
    }

    @Before(value = "@iPhone12", order = 0)
    public void setiPhone12() {
      HooksImp.  isFullScreen = false;
     HooksImp.   width = 390;
      HooksImp.  height = 844;
    }

    @Before(order = 1, value = "@UI")
    public void setup() {

       driver = Driver.getDriver();
        commonPage = new CommonPage() {
        };
        actions = new Actions(driver);
    }

    @After(value = "@UI")
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshots");
        }
//        Driver.closeDriver();
    }

    @Before("@DB")
    public void setupDatabase() {
            DatabaseUtilities.createConnection();
    }

    @After("@DB")
    public void closeDatabase() {
           DatabaseUtilities.closeConnection();
    }

    @Before("@user1")
    public void denemeLogin() {
        System.out.println(
                "email : " + ConfigurationReader.getProperty("user1_email") +
                        " password : " + ConfigurationReader.getProperty("user1_password")
        );
    }

}