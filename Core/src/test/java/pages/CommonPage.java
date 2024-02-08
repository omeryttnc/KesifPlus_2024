package pages;

import org.openqa.selenium.support.PageFactory;

import static stepDefinitions.Hooks.driver;


public abstract class CommonPage {
    public CommonPage() {
        PageFactory.initElements(driver, this);
    }

    private HomePage homePage;
    private LoginPage loginPage;
    private AccountPage accountPage;
    private WeeklyOrderPage weeklyOrderPage;

    public WeeklyOrderPage getWeeklyOrderPage() {
        if (weeklyOrderPage == null) {
            weeklyOrderPage = new WeeklyOrderPage();
        }
        return weeklyOrderPage;
    }

    public AccountPage getAccountPage() {
        if (accountPage == null) {
            accountPage = new AccountPage();
        }
        return accountPage;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

}
