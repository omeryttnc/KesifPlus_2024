package stepDefinitions.uiStepDef;

import enums.USERINFO;
import io.cucumber.java.en.Given;

public class week6 {
    @Given("go to weekly orderpage")
    public void goToWeeklyOrderpage() {
        USERINFO.CANLI_SATICI.loginWithToken("account/weekly-order");
    }
}
