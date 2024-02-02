package pages;

import enums.USERCREDENTIAL;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static enums.URLOGIN.LOGIN_URL;
import static stepDefinitions.Hooks.driver;

public class LoginPage extends CommonPage{

    @FindBy(xpath = "//input[@name='email']")
    public WebElement input_email;
    @FindBy(xpath = "//input[@name='password']")
    public WebElement input_password;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submit_button;

    public void validCredential(){
        driver.get(LOGIN_URL.getLink());
        input_email.sendKeys(USERCREDENTIAL.USER1.getUsername());
        input_password.sendKeys(USERCREDENTIAL.USER1.getPassword());
        submit_button.click();



    }


}
