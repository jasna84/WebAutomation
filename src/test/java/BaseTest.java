package test.java;

import main.java.config.Driver;
import main.java.pageObject.LoginPage;

public class BaseTest {

    public void goToLoginPage() {
        LoginPage homePage = new LoginPage();
        homePage.navigateToLoginPage();
    }

    public void teardown() {
            Driver.getInstance().closeDriver();
        }

}

