package pages;

import myInterface.IPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

import static com.KesifPlus.HooksImp.driver;


public class ProductPage extends CommonPage implements IPage {
    @FindBy(xpath = "//div[@class='row m-0 p-2 align-items-center']/span")
    public List<WebElement> allhubs;

    @FindBy(xpath = "//a[contains(@class,'HubManagement_productTitle__2ke7R')]")
    public List<WebElement> allAddedProducts;


    @FindBy(xpath = "//datalist[@id='productName']/option")
    public List<WebElement> allProductsNames;

    @FindBy(xpath = "//span[contains(@class,'HubManagement_badge__2jPOb')]")
    public List<WebElement> allProductsStatus;

    @FindBy(xpath = "//input[@id='selectProductName']")
    public WebElement addNewProduct_Name;


    @FindBy(xpath = "//input[@id='hubManagePrice']")
    public WebElement addNewProduct_Price;


    @FindBy(xpath = "//input[@id='hubManageStock']")
    public WebElement addNewProduct_Stock;

    @FindBy(xpath = "//select[@id='selectProductUnit']")
    public WebElement addNewProduct_Unit;


    @FindBy(xpath = "//button[@id='hubManageSaveButton']")
    public WebElement addNewProduct_Submit;


    @FindBy(xpath = "//button[@class='btn btn-success']")
    public WebElement update;


    @FindBy(xpath = "//a[@class='btn btn-danger']")
    public WebElement delete;

    @FindBy(xpath = "(//div[@class='text-center mb-2']/a)[1]")
    public WebElement yes;


    @FindBy(xpath = "(//div[@class='text-center mb-2']/a)[2]")
    public WebElement no;


    public String eklenebilecekUrun() {
        List<String> allProducts = allProductsNames
                .stream()
                .map(t -> t.getAttribute("value"))
                .collect(Collectors.toList());

        List<String> addedProducts = allAddedProducts
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());

        allProducts.removeAll(addedProducts);
        String eklenecekUrun = allProducts.getFirst();
        return eklenecekUrun;
    }

    public String getState(String productName) {
        return driver.findElement(By.xpath("//a[@title='" + productName + "']/../../span")).getText();

    }


    @Override
    public void goTo() {
        driver.get("https://test.urbanicfarm.com/account/hub");

    }

    @Override
    public void assertUrl() {

    }
}
