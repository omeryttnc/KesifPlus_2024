package stepDefinitions.backendStepDef;

import com.KesifPlus.api.ApiUtilities;
import com.KesifPlus.api.ForgotPassword;
import com.KesifPlus.ui.Utilities;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.Test;
import pages.CommonPage;
import pojos.RegisterInfo;
import utility.FakerData;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static com.KesifPlus.HooksImp.driver;
import static io.restassured.RestAssured.given;

public class week5 extends CommonPage {

    Response response;
    private String email;
    String token;

    @When("user create user through api")
    public void userCreateUserThroughApi() {
        FakerData fakerData = new FakerData();
        email = fakerData.getEmail();


        RegisterInfo registerInfo = new RegisterInfo(
                "omer",
                "ali",
                email,
                "EPUcJ5xLpC6nynr",
                "EPUcJ5xLpC6nynr"
        );

        response = given()
                .contentType(ContentType.JSON)
                .body(registerInfo)
                .when()
                .post("https://test.urbanicfarm.com/api/public/register");
        JsonPath jsonPath = response.jsonPath();
        token = jsonPath.getString("token");
    }

    @Then("we should be able to login through ui")
    public void weShouldBeAbleToLoginThroughUi() {
        driver.get("https://test.urbanicfarm.com/auth/login");
        getLoginPage().loginMethod(email, "EPUcJ5xLpC6nynr");
    }

    @And("assert you already login")
    public void assertYouAlreadyLogin() {
        Utilities.waitForClickablility(getWeeklyOrderPage().zipCode, 10);
        Assert.assertEquals(
                "https://test.urbanicfarm.com/account/weekly-order",
                driver.getCurrentUrl());
    }

    @When("user change password through api")
    public void userChangePasswordThroughApi() {
        ForgotPassword.changePassword(
                "EPUcJ5xLpC6nynr",
                "EPUcJ5xLpC6nynr123",
                token
        );
    }

    @And("user enter previous password")
    public void userEnterPreviousPassword() {
        getLoginPage().goTo();
        getLoginPage().loginMethod(email, "EPUcJ5xLpC6nynr");
    }

    @And("toast message colour should be red colour")
    public void toastMessageColourShouldBeRedColour() {
        Utilities.waitForClickablility(getHomePage().toastMessage, 10);
        System.out.println("getHomePage().toastMessage.getText() = " + getHomePage().toastMessage.getText());
    }


    @When("user change original password through api")
    public void userChangeOriginalPasswordThroughApi() {
        ForgotPassword.changePassword(
                "EPUcJ5xLpC6nynr123",
                "EPUcJ5xLpC6nynr",
                token
        );
    }

    @Given("login with local storage")
    public void loginWithLocalStorage() {
    }

    @And("enter zipcode through local storage")
    public void enterZipcodeThroughLocalStorage() {
    }

    @And("enter pop up message through local storage")
    public void enterPopUpMessageThroughLocalStorage() {
    }


}
