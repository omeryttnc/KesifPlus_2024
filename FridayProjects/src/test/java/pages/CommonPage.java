package pages;

import com.KesifPlus.ui.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.KesifPlus.HooksImp.driver;


public abstract class CommonPage {

    public CommonPage() {
        PageFactory.initElements(driver, this);
    }

    private HomePage homePage;
    private RegisterPage registerPage;
    private AccountPage accountPage;
    private OrdersPage ordersPage;
    private LoginPage loginPage;
    private SoldItemsPage soldItemsPage;
    private EventsPage eventsPage;
    private MyEventsPage myEventsPage;
    private YourProductsPage yourProductsPage;

    public YourProductsPage getYourProductsPage(){
        if(yourProductsPage == null){
            yourProductsPage = new YourProductsPage();
        }
        return yourProductsPage;
    }

    public EventsPage getEventsPage() {
        if (eventsPage == null){
            eventsPage = new EventsPage();
        }
        return eventsPage;
    }

    public MyEventsPage getMyEventsPage() {
        if (myEventsPage == null){
            myEventsPage = new MyEventsPage();
        }
        return myEventsPage;
    }

    public SoldItemsPage getSoldItemsPage() {
        if (soldItemsPage == null){
            soldItemsPage = new SoldItemsPage();
        }
        return soldItemsPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null){
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public AccountPage getAccountPage() {
        if (accountPage == null) {
            accountPage = new AccountPage();
        }
        return accountPage;
    }

    public OrdersPage getOrdersPage() {
        if (ordersPage == null){
            ordersPage = new OrdersPage();
        }
        return ordersPage;
    }

    public RegisterPage getRegisterPage() {
        if (registerPage == null) {
            registerPage = new RegisterPage();
        }
        return registerPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public void clickButton(String buttonTitle){
        WebElement element = driver.findElement(By.xpath(
                "//Button[.='" + buttonTitle +"']"));
        Utilities.scrollAndClick(element);
    }

    public void clickHubButton(String title){
        WebElement element = driver.findElement(By.xpath(
                "//span[.='" + title + "']"));
        Utilities.scrollAndClick(element);
    }

}
