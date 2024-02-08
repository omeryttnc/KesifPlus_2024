package pages;

import enums.USERINFO;
import myInterface.IPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static stepDefinitions.Hooks.driver;

public class LoginPage extends CommonPage implements IPage {
    @FindBy(css = "[name='email']")
    private WebElement emailText;

    @FindBy(css = "[name='password']")
    private WebElement passwordText;

    @FindBy(css = "[type='submit']")
    private WebElement submitButton;

    public void loginMethod(String email, String password) {
        emailText.sendKeys(email);
        passwordText.sendKeys(password);
        submitButton.click();
    }
    public void loginMethod(USERINFO userinfo) {
        goTo();
        emailText.sendKeys(userinfo.getEmail());
        passwordText.sendKeys(userinfo.getPassword());
        submitButton.click();
    }

    @Override
    public void goTo() {
        driver.get("https://test.urbanicfarm.com/auth/login");
    }

    @Override
    public void assertUrl() {

    }
}
