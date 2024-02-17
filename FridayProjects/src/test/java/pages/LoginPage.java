package pages;

import com.KesifPlus.ui.Utilities;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends CommonPage{

    @FindBy(xpath = "//input[@name='email']")
    private WebElement input_email;
    @FindBy(xpath = "//input[@name='password']")
    private WebElement input_password;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submit_button;

    public void loginWithCredentials(String email, String password){
        Utilities.sendText(input_email, email);
        Utilities.sendText(input_password, password);
        Utilities.waitAndClickElement(submit_button, 5);
    }
}
