package pages;

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
}
