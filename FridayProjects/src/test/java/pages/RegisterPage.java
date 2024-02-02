package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Driver;
import utilities.ReusableMethods;

import java.util.List;

public class RegisterPage extends CommonPage{

    @FindBy (id ="registration_form_firstname")
    private WebElement firstNameInput;

    @FindBy (id ="registration_form_middlename")
    private WebElement middleNameInput;

    @FindBy(id="registration_form_lastname")
    private WebElement lastNameInput;

    @FindBy (id ="registration_form_email")
    private WebElement eMailInput;

    @FindBy (xpath = "//button[contains(text(), 'Register')]")
    private WebElement registerBtn;

    public void clickMainButton(String btnName){
        WebElement element = Driver.getDriver().findElement(By.xpath("//div/a[contains(text(), '" +
                btnName + "')]"));
        element.click();
    }

    public void verifyFirstNames(List<List<String>> listItems){
        String text="";
        String isTrue;
        Boolean isValid;
        for(int i=0; i<listItems.size(); i++){
            text = ((listItems.get(i).get(0)).equals("empty")) ? text : listItems.get(i).get(0);
            isTrue = listItems.get(i).get(1);

            firstNameInput.clear();
            ReusableMethods.sendText(firstNameInput,text);   //elemente scroll yapılma ihtiyacı için ara metod eklendi
            ReusableMethods.waitAndClickElement(registerBtn, 2);   //her saniye click denemesi yapılması için eklendi

            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            isValid = (Boolean) js.executeScript("return arguments[0].validity.valid", firstNameInput);
            Assert.assertEquals(Boolean.parseBoolean(isTrue), isValid);
        }
    }

    public void verifyMiddleNames(List<List<String>> listItems){
        String text="";
        String isTrue;
        Boolean isValid;
        for(int i=0; i<listItems.size(); i++){
            text = ((listItems.get(i).get(0)).equals("empty")) ? text : listItems.get(i).get(0);
            isTrue = listItems.get(i).get(1);

            middleNameInput.clear();
            ReusableMethods.sendText(middleNameInput,text);   // elemente scroll yapılma ihtiyacı için eklendi
            ReusableMethods.waitAndClickElement(registerBtn, 2);   // her saniye click denemesi yapılması için eklendi

            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            isValid = (Boolean) js.executeScript("return arguments[0].validity.valid", middleNameInput);
            Assert.assertEquals(Boolean.parseBoolean(isTrue), isValid);
        }
    }

    public void verifyLastNames(List<List<String>> listItems){
        String text="";
        String isTrue;
        Boolean isValid;
        for (int i=0; i<listItems.size(); i++) {
            text = ((listItems.get(i).get(0)).equals("empty")) ? text : listItems.get(i).get(0);
            isTrue = listItems.get(i).get(1);

            lastNameInput.clear();
            ReusableMethods.sendText(lastNameInput,text);   // elemente scroll yapılma ihtiyacı için eklendi
            ReusableMethods.waitAndClickElement(registerBtn, 2);   // her saniye click denemesi yapılması için eklendi

            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            isValid = (Boolean) js.executeScript("return arguments[0].validity.valid", lastNameInput);
            Assert.assertEquals(Boolean.parseBoolean(isTrue), isValid);
        }
    }

    public void verifyEmails(List<List<String>> listItems){
        String text="";
        String isTrue;
        Boolean isValid;
        String message;
        for(int i=0; i<listItems.size(); i++){
            text = ((listItems.get(i).get(0)).equals("empty")) ? text : listItems.get(i).get(0);
            isTrue = listItems.get(i).get(1);

            eMailInput.clear();
            ReusableMethods.sendText(eMailInput,text);   // elemente scroll yapılma ihtiyacı için ara metod eklendi
            ReusableMethods.waitAndClickElement(registerBtn, 2);   // her saniye click denemesi yapılması için eklendi

            JavascriptExecutor js = (JavascriptExecutor) Driver.getDriver();
            isValid = (Boolean) js.executeScript("return arguments[0].validity.valid", eMailInput);
            Assert.assertEquals(Boolean.parseBoolean(isTrue), isValid);

            message = (String) js.executeScript("return arguments[0].validationMessage", eMailInput);
            verifyMessage(text, message);
        }
    }

    public void verifyMessage(String email, String message){
        if ((!email.contains("@")) && (!email.isEmpty())){
            Assert.assertTrue(message.contains("@"));
        }
    }
}
