package stepDefinitions.UI_StepDefs.LoginPage;

import io.cucumber.java.en.Given;
import pages.CommonPage;

public class US_50AccountPage extends CommonPage {
    @Given("user goes to Login Page")
    public void userGoesToLoginPage() {
       getLoginPage().validCredential();
    }
}
