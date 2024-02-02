package stepDefinitions.UI_StepDefs.HomePage;

import io.cucumber.java.en.When;
import pages.CommonPage;
import utilities.JSUtils;
import utilities.ReusableMethods;

public class US_27 extends CommonPage {
    @When("User click to Adress")
    public void userClickToAdress() {
        JSUtils.scrollDownByJS();
        getHomePage().AdressButton27.isDisplayed();
        ReusableMethods.waitFor(5);
    }


}
